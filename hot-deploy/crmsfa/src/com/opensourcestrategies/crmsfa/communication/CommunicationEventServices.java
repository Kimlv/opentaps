/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/

/* This file has been modified by Open Source Strategies, Inc. */

/*
 * Copyright (c) 2006 - 2009 Open Source Strategies, Inc.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the Honest Public License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Honest Public License for more details.
 *
 * You should have received a copy of the Honest Public License
 * along with this program; if not, write to Funambol,
 * 643 Bair Island Road, Suite 305 - Redwood City, CA 94063, USA
 */

package com.opensourcestrategies.crmsfa.communication;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.opensourcestrategies.crmsfa.party.PartyHelper;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilDateTime;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.condition.EntityConditionList;
import org.ofbiz.entity.condition.EntityExpr;
import org.ofbiz.entity.condition.EntityOperator;
import org.ofbiz.entity.util.EntityFindOptions;
import org.ofbiz.entity.util.EntityUtil;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.GenericServiceException;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ServiceUtil;
import org.opentaps.common.template.freemarker.FreemarkerUtil;

public class CommunicationEventServices {

    private static final String module = CommunicationEventServices.class.getName();

    /**
     * Send emails to members of a contact list, wrapping each email in its own transaction and tagging each member
     * that has been sent, so if the whole effort is aborted, it can start over from the middle.
     * The max-retry is important because if some emails cannot be sent, it will start again later and try again.
     * Overrides the Ofbiz service in order to perform template substitution for each recipient.
     * @param ctx a <code>DispatchContext</code> value
     * @param context a <code>Map</code> value
     * @return a <code>Map</code> value
     */
    public static Map sendEmailToContactList(DispatchContext ctx, Map context) {
        GenericDelegator delegator = ctx.getDelegator();
        LocalDispatcher dispatcher = ctx.getDispatcher();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        Locale locale = (Locale) context.get("locale");
        TimeZone timeZone = (TimeZone) context.get("timeZone");

        if (timeZone == null) {
            timeZone = UtilDateTime.getDefaultTimeZone();
        }

        List<String> errorMessages = new ArrayList<String>();
        final String resource = "PartyUiLabels";
        String errorCallingUpdateContactListPartyService = UtilProperties.getMessage(resource, "commeventservices.errorCallingUpdateContactListPartyService", locale);
        String errorCallingSendMailService = UtilProperties.getMessage(resource, "commeventservices.errorCallingSendMailService", locale);
        String errorInSendEmailToContactListService = UtilProperties.getMessage(resource, "commeventservices.errorForEmailAddress", locale);
        String skippingInvalidEmailAddress = UtilProperties.getMessage(resource, "commeventservices.skippingInvalidEmailAddress", locale);

        String contactListId = (String) context.get("contactListId");
        String communicationEventId = (String) context.get("communicationEventId");

        // Any exceptions thrown in this block will cause the service to return error
        try {

            GenericValue communicationEvent = delegator.findByPrimaryKey("CommunicationEvent", UtilMisc.toMap("communicationEventId", communicationEventId));
            GenericValue contactList = delegator.findByPrimaryKey("ContactList", UtilMisc.toMap("contactListId", contactListId));

            Map<String, Object> sendMailParams = new HashMap<String, Object>();
            sendMailParams.put("sendFrom", communicationEvent.getRelatedOne("FromContactMech").getString("infoString"));
            String subject = communicationEvent.getString("subject");
            String body = communicationEvent.getString("content");
            sendMailParams.put("contentType", communicationEvent.getString("contentMimeTypeId"));
            sendMailParams.put("userLogin", userLogin);

            // Find a list of distinct email addresses from active, ACCEPTED parties in the contact list
            //      using a list iterator (because there can be a large number)
            List conditionList = UtilMisc.toList(
                        new EntityExpr("contactListId", EntityOperator.EQUALS, contactList.get("contactListId")),
                        new EntityExpr("statusId", EntityOperator.EQUALS, "CLPT_ACCEPTED"),
                        new EntityExpr("preferredContactMechId", EntityOperator.NOT_EQUAL, null),
                        EntityUtil.getFilterByDateExpr(), EntityUtil.getFilterByDateExpr("contactFromDate", "contactThruDate")
                        );
            EntityConditionList conditions = new EntityConditionList(conditionList, EntityOperator.AND);
            List fieldsToSelect = UtilMisc.toList("infoString");

            List sendToEmails = delegator.findByCondition("ContactListPartyAndContactMech", conditions,  null, fieldsToSelect, null,
                    new EntityFindOptions(true, EntityFindOptions.TYPE_SCROLL_INSENSITIVE, EntityFindOptions.CONCUR_READ_ONLY, true));

            // Send an email to each contact list member
            // TODO: Contact lists for emails really should be written as an EntityListIterator for very large lists!
            List orderBy = UtilMisc.toList("-fromDate");
            Iterator sendToEmailsIt = sendToEmails.iterator();
            while (sendToEmailsIt.hasNext()) {

                GenericValue contactListPartyAndContactMech = (GenericValue) sendToEmailsIt.next();

                // Any exceptions thrown in this inner block will only relate to a single email of the list, so should
                //  only be logged and not cause the service to return an error
                try {

                    String emailAddress = contactListPartyAndContactMech.getString("infoString");
                    if (UtilValidate.isEmpty(emailAddress)) {
                        continue;
                    }
                    emailAddress = emailAddress.trim();

                    if (!UtilValidate.isEmail(emailAddress)) {

                        // If validation fails, just log and skip the email address
                        Debug.logError(skippingInvalidEmailAddress + ": " + emailAddress, module);
                        errorMessages.add(skippingInvalidEmailAddress + ": " + emailAddress);
                        continue;
                    }

                    // Because we're retrieving infoString only above (so as not to pollute the distinctness), we
                    //      need to retrieve the partyId it's related to. Since this could be multiple parties, get
                    //      only the most recent valid one via ContactListPartyAndContactMech.
                    List clpConditionList = new ArrayList(conditionList);
                    clpConditionList.add(new EntityExpr("infoString", EntityOperator.EQUALS, emailAddress));
                    EntityConditionList clpConditions = new EntityConditionList(clpConditionList, EntityOperator.AND);

                    List emailCLPaCMs = delegator.findByConditionCache("ContactListPartyAndContactMech", clpConditions, null, orderBy);
                    GenericValue lastContactListPartyACM = EntityUtil.getFirst(emailCLPaCMs);
                    if (lastContactListPartyACM == null) {
                        continue;
                    }

                    String partyId = lastContactListPartyACM.getString("partyId");

                    sendMailParams.put("sendTo", emailAddress);
                    sendMailParams.put("partyId", partyId);

                    // merge the template with the recipient context
                    Map mergeContext = PartyHelper.assembleCrmsfaFormMergeContext(delegator, locale, partyId, null, null, null, timeZone);
                    StringWriter wr = new StringWriter();
                    String bodyMerged = body;
                    String subjectMerged = subject;
                    try {
                        FreemarkerUtil.renderTemplateWithTags("MergeForm", body, mergeContext, wr, true, false);
                        bodyMerged = wr.toString();
                        if (UtilValidate.isNotEmpty(subject)) {
                            wr = new StringWriter();
                            FreemarkerUtil.renderTemplateWithTags("MergeForm", subject, mergeContext, wr, true, false);
                            subjectMerged = wr.toString();
                        }
                    } catch (Exception e) {
                        Debug.logError(e, module);
                    }
                    sendMailParams.put("body", bodyMerged);
                    sendMailParams.put("subject", subjectMerged);

                    // if it is a NEWSLETTER then we do not want the outgoing emails stored, so put a communicationEventId in the sendMail context to prevent storeEmailAsCommunicationEvent from running
                    if ("NEWSLETTER".equals(contactList.getString("contactListTypeId"))) {
                        sendMailParams.put("communicationEventId", communicationEventId);
                    }

                    // Retrieve a record for this contactMechId from ContactListCommStatus
                    Map contactListCommStatusRecordMap = UtilMisc.toMap("contactListId", contactListId, "communicationEventId", communicationEventId, "contactMechId", lastContactListPartyACM.getString("preferredContactMechId"));
                    GenericValue contactListCommStatusRecord = delegator.findByPrimaryKey("ContactListCommStatus", contactListCommStatusRecordMap);
                    if (contactListCommStatusRecord == null) {

                        // No attempt has been made previously to send to this address, so create a record to reflect
                        //  the beginning of the current attempt
                        Map newContactListCommStatusRecordMap = new HashMap(contactListCommStatusRecordMap);
                        newContactListCommStatusRecordMap.put("statusId", "COM_IN_PROGRESS");
                        contactListCommStatusRecord = delegator.create("ContactListCommStatus", newContactListCommStatusRecordMap);
                    } else if (contactListCommStatusRecord.get("statusId") != null && contactListCommStatusRecord.getString("statusId").equals("COM_COMPLETE")) {

                        // There was a successful earlier attempt, so skip this address
                        continue;
                    }

                    Map tmpResult = null;

                    // Make the attempt to send the email to the address
                    tmpResult = dispatcher.runSync("sendMail", sendMailParams);
                    if (tmpResult == null || ServiceUtil.isError(tmpResult)) {

                        // If the send attempt fails, just log and skip the email address
                        Debug.logError(errorCallingSendMailService + ": " + ServiceUtil.getErrorMessage(tmpResult), module);
                        errorMessages.add(errorCallingSendMailService + ": " + ServiceUtil.getErrorMessage(tmpResult));
                        continue;
                    }

                    if ("Y".equals(contactList.get("singleUse"))) {

                        // Expire the ContactListParty if the list is single use and sendEmail finishes successfully
                        tmpResult = dispatcher.runSync("updateContactListParty", UtilMisc.toMap("contactListId", lastContactListPartyACM.get("contactListId"),
                                                                                                "partyId", partyId, "fromDate", lastContactListPartyACM.get("fromDate"),
                                                                                                "thruDate", UtilDateTime.nowTimestamp(), "userLogin", userLogin));
                        if (ServiceUtil.isError(tmpResult)) {

                            // If the expiry fails, just log and skip the email address
                            Debug.logError(errorCallingUpdateContactListPartyService + ": " + ServiceUtil.getErrorMessage(tmpResult), module);
                            errorMessages.add(errorCallingUpdateContactListPartyService + ": " + ServiceUtil.getErrorMessage(tmpResult));
                            continue;
                        }
                    }

                    // All is successful, so update the ContactListCommStatus record
                    contactListCommStatusRecord.set("statusId", "COM_COMPLETE");
                    delegator.store(contactListCommStatusRecord);

                // Don't return a service error just because of failure for one address - just log the error and continue
                } catch (GenericEntityException nonFatalGEE) {
                    Debug.logError(nonFatalGEE, errorInSendEmailToContactListService, module);
                    errorMessages.add(errorInSendEmailToContactListService + ": " + nonFatalGEE.getMessage());
                } catch (GenericServiceException nonFatalGSE) {
                    Debug.logError(nonFatalGSE, errorInSendEmailToContactListService, module);
                    errorMessages.add(errorInSendEmailToContactListService + ": " + nonFatalGSE.getMessage());
                }
            }

        } catch (GenericEntityException fatalGEE) {
            ServiceUtil.returnError(fatalGEE.getMessage());
        }

        return errorMessages.size() == 0 ? ServiceUtil.returnSuccess() : ServiceUtil.returnError(errorMessages);
    }

}

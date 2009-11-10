package org.opentaps.domain.base.entities;

/*
 * Copyright (c) 2008 - 2009 Open Source Strategies, Inc.
 *
 * Opentaps is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Opentaps is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Opentaps.  If not, see <http://www.gnu.org/licenses/>.
 */

// DO NOT EDIT THIS FILE!  THIS IS AUTO GENERATED AND WILL GET WRITTEN OVER PERIODICALLY WHEN THE DATA MODEL CHANGES
// EXTEND THIS CLASS INSTEAD.

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javolution.util.FastMap;

import org.opentaps.foundation.entity.Entity;
import org.opentaps.foundation.entity.EntityFieldInterface;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.repository.RepositoryInterface;
import javax.persistence.*;
import org.hibernate.search.annotations.*;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Auto generated base entity SalesInvoiceItemFact.
 */
@javax.persistence.Entity
@Table(name="SALES_INVOICE_ITEM_FACT")
public class SalesInvoiceItemFact extends Entity {
static {
java.util.Map<String, String> fields = new java.util.HashMap<String, String>();
        fields.put("salesInvItemFactId", "SALES_INV_ITEM_FACT_ID");
        fields.put("dateDimId", "DATE_DIM_ID");
        fields.put("storeDimId", "STORE_DIM_ID");
        fields.put("currencyDimId", "CURRENCY_DIM_ID");
        fields.put("organizationDimId", "ORGANIZATION_DIM_ID");
        fields.put("invoiceId", "INVOICE_ID");
        fields.put("invoiceItemSeqId", "INVOICE_ITEM_SEQ_ID");
        fields.put("invoiceAdjustmentId", "INVOICE_ADJUSTMENT_ID");
        fields.put("grossAmount", "GROSS_AMOUNT");
        fields.put("discounts", "DISCOUNTS");
        fields.put("refunds", "REFUNDS");
        fields.put("netAmount", "NET_AMOUNT");
        fields.put("lastUpdatedStamp", "LAST_UPDATED_STAMP");
        fields.put("lastUpdatedTxStamp", "LAST_UPDATED_TX_STAMP");
        fields.put("createdStamp", "CREATED_STAMP");
        fields.put("createdTxStamp", "CREATED_TX_STAMP");
fieldMapColumns.put("SalesInvoiceItemFact", fields);
}
  public static enum Fields implements EntityFieldInterface<SalesInvoiceItemFact> {
    salesInvItemFactId("salesInvItemFactId"),
    dateDimId("dateDimId"),
    storeDimId("storeDimId"),
    currencyDimId("currencyDimId"),
    organizationDimId("organizationDimId"),
    invoiceId("invoiceId"),
    invoiceItemSeqId("invoiceItemSeqId"),
    invoiceAdjustmentId("invoiceAdjustmentId"),
    grossAmount("grossAmount"),
    discounts("discounts"),
    refunds("refunds"),
    netAmount("netAmount"),
    lastUpdatedStamp("lastUpdatedStamp"),
    lastUpdatedTxStamp("lastUpdatedTxStamp"),
    createdStamp("createdStamp"),
    createdTxStamp("createdTxStamp");
    private final String fieldName;
    private Fields(String name) { fieldName = name; }
    /** {@inheritDoc} */
    public String getName() { return fieldName; }
    /** {@inheritDoc} */
    public String asc() { return fieldName + " ASC"; }
    /** {@inheritDoc} */
    public String desc() { return fieldName + " DESC"; }
  }

   @org.hibernate.annotations.GenericGenerator(name="SalesInvoiceItemFact_GEN",  strategy="org.opentaps.foundation.entity.hibernate.OpentapsIdentifierGenerator")
   @GeneratedValue(generator="SalesInvoiceItemFact_GEN")
   @Id
   @Column(name="SALES_INV_ITEM_FACT_ID")
   private Long salesInvItemFactId;
   @Column(name="DATE_DIM_ID")
   private Long dateDimId;
   @Column(name="STORE_DIM_ID")
   private Long storeDimId;
   @Column(name="CURRENCY_DIM_ID")
   private Long currencyDimId;
   @Column(name="ORGANIZATION_DIM_ID")
   private Long organizationDimId;
   @Column(name="INVOICE_ID")
   private String invoiceId;
   @Column(name="INVOICE_ITEM_SEQ_ID")
   private String invoiceItemSeqId;
   @Column(name="INVOICE_ADJUSTMENT_ID")
   private String invoiceAdjustmentId;
   @Column(name="GROSS_AMOUNT")
   private BigDecimal grossAmount;
   @Column(name="DISCOUNTS")
   private BigDecimal discounts;
   @Column(name="REFUNDS")
   private BigDecimal refunds;
   @Column(name="NET_AMOUNT")
   private BigDecimal netAmount;
   @Column(name="LAST_UPDATED_STAMP")
   private Timestamp lastUpdatedStamp;
   @Column(name="LAST_UPDATED_TX_STAMP")
   private Timestamp lastUpdatedTxStamp;
   @Column(name="CREATED_STAMP")
   private Timestamp createdStamp;
   @Column(name="CREATED_TX_STAMP")
   private Timestamp createdTxStamp;

  /**
   * Default constructor.
   */
  public SalesInvoiceItemFact() {
      super();
      this.baseEntityName = "SalesInvoiceItemFact";
      this.isView = false;
      
      this.primaryKeyNames = new ArrayList<String>();
      this.primaryKeyNames.add("salesInvItemFactId");
      this.allFieldsNames = new ArrayList<String>();
      this.allFieldsNames.add("salesInvItemFactId");this.allFieldsNames.add("dateDimId");this.allFieldsNames.add("storeDimId");this.allFieldsNames.add("currencyDimId");this.allFieldsNames.add("organizationDimId");this.allFieldsNames.add("invoiceId");this.allFieldsNames.add("invoiceItemSeqId");this.allFieldsNames.add("invoiceAdjustmentId");this.allFieldsNames.add("grossAmount");this.allFieldsNames.add("discounts");this.allFieldsNames.add("refunds");this.allFieldsNames.add("netAmount");this.allFieldsNames.add("lastUpdatedStamp");this.allFieldsNames.add("lastUpdatedTxStamp");this.allFieldsNames.add("createdStamp");this.allFieldsNames.add("createdTxStamp");
      this.nonPrimaryKeyNames = new ArrayList<String>();
      this.nonPrimaryKeyNames.addAll(allFieldsNames);
      this.nonPrimaryKeyNames.removeAll(primaryKeyNames);
  }

  /**
   * Constructor with a repository.
   * @param repository a <code>RepositoryInterface</code> value
   */
  public SalesInvoiceItemFact(RepositoryInterface repository) {
      this();
      initRepository(repository);
  }

    /**
     * Auto generated value setter.
     * @param salesInvItemFactId the salesInvItemFactId to set
     */
    public void setSalesInvItemFactId(Long salesInvItemFactId) {
        this.salesInvItemFactId = salesInvItemFactId;
    }
    /**
     * Auto generated value setter.
     * @param dateDimId the dateDimId to set
     */
    public void setDateDimId(Long dateDimId) {
        this.dateDimId = dateDimId;
    }
    /**
     * Auto generated value setter.
     * @param storeDimId the storeDimId to set
     */
    public void setStoreDimId(Long storeDimId) {
        this.storeDimId = storeDimId;
    }
    /**
     * Auto generated value setter.
     * @param currencyDimId the currencyDimId to set
     */
    public void setCurrencyDimId(Long currencyDimId) {
        this.currencyDimId = currencyDimId;
    }
    /**
     * Auto generated value setter.
     * @param organizationDimId the organizationDimId to set
     */
    public void setOrganizationDimId(Long organizationDimId) {
        this.organizationDimId = organizationDimId;
    }
    /**
     * Auto generated value setter.
     * @param invoiceId the invoiceId to set
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    /**
     * Auto generated value setter.
     * @param invoiceItemSeqId the invoiceItemSeqId to set
     */
    public void setInvoiceItemSeqId(String invoiceItemSeqId) {
        this.invoiceItemSeqId = invoiceItemSeqId;
    }
    /**
     * Auto generated value setter.
     * @param invoiceAdjustmentId the invoiceAdjustmentId to set
     */
    public void setInvoiceAdjustmentId(String invoiceAdjustmentId) {
        this.invoiceAdjustmentId = invoiceAdjustmentId;
    }
    /**
     * Auto generated value setter.
     * @param grossAmount the grossAmount to set
     */
    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }
    /**
     * Auto generated value setter.
     * @param discounts the discounts to set
     */
    public void setDiscounts(BigDecimal discounts) {
        this.discounts = discounts;
    }
    /**
     * Auto generated value setter.
     * @param refunds the refunds to set
     */
    public void setRefunds(BigDecimal refunds) {
        this.refunds = refunds;
    }
    /**
     * Auto generated value setter.
     * @param netAmount the netAmount to set
     */
    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }
    /**
     * Auto generated value setter.
     * @param lastUpdatedStamp the lastUpdatedStamp to set
     */
    public void setLastUpdatedStamp(Timestamp lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }
    /**
     * Auto generated value setter.
     * @param lastUpdatedTxStamp the lastUpdatedTxStamp to set
     */
    public void setLastUpdatedTxStamp(Timestamp lastUpdatedTxStamp) {
        this.lastUpdatedTxStamp = lastUpdatedTxStamp;
    }
    /**
     * Auto generated value setter.
     * @param createdStamp the createdStamp to set
     */
    public void setCreatedStamp(Timestamp createdStamp) {
        this.createdStamp = createdStamp;
    }
    /**
     * Auto generated value setter.
     * @param createdTxStamp the createdTxStamp to set
     */
    public void setCreatedTxStamp(Timestamp createdTxStamp) {
        this.createdTxStamp = createdTxStamp;
    }

    /**
     * Auto generated value accessor.
     * @return <code>Long</code>
     */
    public Long getSalesInvItemFactId() {
        return this.salesInvItemFactId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Long</code>
     */
    public Long getDateDimId() {
        return this.dateDimId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Long</code>
     */
    public Long getStoreDimId() {
        return this.storeDimId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Long</code>
     */
    public Long getCurrencyDimId() {
        return this.currencyDimId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Long</code>
     */
    public Long getOrganizationDimId() {
        return this.organizationDimId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>String</code>
     */
    public String getInvoiceId() {
        return this.invoiceId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>String</code>
     */
    public String getInvoiceItemSeqId() {
        return this.invoiceItemSeqId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>String</code>
     */
    public String getInvoiceAdjustmentId() {
        return this.invoiceAdjustmentId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>BigDecimal</code>
     */
    public BigDecimal getGrossAmount() {
        return this.grossAmount;
    }
    /**
     * Auto generated value accessor.
     * @return <code>BigDecimal</code>
     */
    public BigDecimal getDiscounts() {
        return this.discounts;
    }
    /**
     * Auto generated value accessor.
     * @return <code>BigDecimal</code>
     */
    public BigDecimal getRefunds() {
        return this.refunds;
    }
    /**
     * Auto generated value accessor.
     * @return <code>BigDecimal</code>
     */
    public BigDecimal getNetAmount() {
        return this.netAmount;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Timestamp</code>
     */
    public Timestamp getLastUpdatedStamp() {
        return this.lastUpdatedStamp;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Timestamp</code>
     */
    public Timestamp getLastUpdatedTxStamp() {
        return this.lastUpdatedTxStamp;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Timestamp</code>
     */
    public Timestamp getCreatedStamp() {
        return this.createdStamp;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Timestamp</code>
     */
    public Timestamp getCreatedTxStamp() {
        return this.createdTxStamp;
    }




    /** {@inheritDoc} */
    @Override
    public void fromMap(Map<String, Object> mapValue) {
        preInit();
        setSalesInvItemFactId((Long) mapValue.get("salesInvItemFactId"));
        setDateDimId((Long) mapValue.get("dateDimId"));
        setStoreDimId((Long) mapValue.get("storeDimId"));
        setCurrencyDimId((Long) mapValue.get("currencyDimId"));
        setOrganizationDimId((Long) mapValue.get("organizationDimId"));
        setInvoiceId((String) mapValue.get("invoiceId"));
        setInvoiceItemSeqId((String) mapValue.get("invoiceItemSeqId"));
        setInvoiceAdjustmentId((String) mapValue.get("invoiceAdjustmentId"));
        setGrossAmount(convertToBigDecimal(mapValue.get("grossAmount")));
        setDiscounts(convertToBigDecimal(mapValue.get("discounts")));
        setRefunds(convertToBigDecimal(mapValue.get("refunds")));
        setNetAmount(convertToBigDecimal(mapValue.get("netAmount")));
        setLastUpdatedStamp((Timestamp) mapValue.get("lastUpdatedStamp"));
        setLastUpdatedTxStamp((Timestamp) mapValue.get("lastUpdatedTxStamp"));
        setCreatedStamp((Timestamp) mapValue.get("createdStamp"));
        setCreatedTxStamp((Timestamp) mapValue.get("createdTxStamp"));
        postInit();
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> mapValue = new FastMap<String, Object>();
        mapValue.put("salesInvItemFactId", getSalesInvItemFactId());
        mapValue.put("dateDimId", getDateDimId());
        mapValue.put("storeDimId", getStoreDimId());
        mapValue.put("currencyDimId", getCurrencyDimId());
        mapValue.put("organizationDimId", getOrganizationDimId());
        mapValue.put("invoiceId", getInvoiceId());
        mapValue.put("invoiceItemSeqId", getInvoiceItemSeqId());
        mapValue.put("invoiceAdjustmentId", getInvoiceAdjustmentId());
        mapValue.put("grossAmount", getGrossAmount());
        mapValue.put("discounts", getDiscounts());
        mapValue.put("refunds", getRefunds());
        mapValue.put("netAmount", getNetAmount());
        mapValue.put("lastUpdatedStamp", getLastUpdatedStamp());
        mapValue.put("lastUpdatedTxStamp", getLastUpdatedTxStamp());
        mapValue.put("createdStamp", getCreatedStamp());
        mapValue.put("createdTxStamp", getCreatedTxStamp());
        return mapValue;
    }


}

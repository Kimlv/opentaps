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
import java.sql.Timestamp;

/**
 * Auto generated base entity AmazonProductImage.
 */
@javax.persistence.Entity
@Table(name="AMAZON_PRODUCT_IMAGE")
public class AmazonProductImage extends Entity {
static {
java.util.Map<String, String> fields = new java.util.HashMap<String, String>();
        fields.put("productId", "PRODUCT_ID");
        fields.put("statusId", "STATUS_ID");
        fields.put("processingDocumentId", "PROCESSING_DOCUMENT_ID");
        fields.put("postTimestamp", "POST_TIMESTAMP");
        fields.put("postErrorMessage", "POST_ERROR_MESSAGE");
        fields.put("postFailures", "POST_FAILURES");
        fields.put("lastUpdatedStamp", "LAST_UPDATED_STAMP");
        fields.put("lastUpdatedTxStamp", "LAST_UPDATED_TX_STAMP");
        fields.put("createdStamp", "CREATED_STAMP");
        fields.put("createdTxStamp", "CREATED_TX_STAMP");
fieldMapColumns.put("AmazonProductImage", fields);
}
  public static enum Fields implements EntityFieldInterface<AmazonProductImage> {
    productId("productId"),
    statusId("statusId"),
    processingDocumentId("processingDocumentId"),
    postTimestamp("postTimestamp"),
    postErrorMessage("postErrorMessage"),
    postFailures("postFailures"),
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

   @org.hibernate.annotations.GenericGenerator(name="AmazonProductImage_GEN",  strategy="org.opentaps.foundation.entity.hibernate.OpentapsIdentifierGenerator")
   @GeneratedValue(generator="AmazonProductImage_GEN")
   @Id
   @Column(name="PRODUCT_ID")
   private String productId;
   @Column(name="STATUS_ID")
   private String statusId;
   @Column(name="PROCESSING_DOCUMENT_ID")
   private Long processingDocumentId;
   @Column(name="POST_TIMESTAMP")
   private Timestamp postTimestamp;
   @Column(name="POST_ERROR_MESSAGE")
   private String postErrorMessage;
   @Column(name="POST_FAILURES")
   private Long postFailures;
   @Column(name="LAST_UPDATED_STAMP")
   private Timestamp lastUpdatedStamp;
   @Column(name="LAST_UPDATED_TX_STAMP")
   private Timestamp lastUpdatedTxStamp;
   @Column(name="CREATED_STAMP")
   private Timestamp createdStamp;
   @Column(name="CREATED_TX_STAMP")
   private Timestamp createdTxStamp;
   @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
   @JoinColumn(name="PRODUCT_ID", insertable=false, updatable=false)
   @org.hibernate.annotations.Generated(
      org.hibernate.annotations.GenerationTime.ALWAYS
   )
   
   private AmazonProduct amazonProduct = null;
   @OneToMany(fetch=FetchType.LAZY, mappedBy="amazonProductImage", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
   @JoinColumn(name="PRODUCT_ID")
   
   private List<AmazonProductImageAck> amazonProductImageAcks = null;

  /**
   * Default constructor.
   */
  public AmazonProductImage() {
      super();
      this.baseEntityName = "AmazonProductImage";
      this.isView = false;
      
      this.primaryKeyNames = new ArrayList<String>();
      this.primaryKeyNames.add("productId");
      this.allFieldsNames = new ArrayList<String>();
      this.allFieldsNames.add("productId");this.allFieldsNames.add("statusId");this.allFieldsNames.add("processingDocumentId");this.allFieldsNames.add("postTimestamp");this.allFieldsNames.add("postErrorMessage");this.allFieldsNames.add("postFailures");this.allFieldsNames.add("lastUpdatedStamp");this.allFieldsNames.add("lastUpdatedTxStamp");this.allFieldsNames.add("createdStamp");this.allFieldsNames.add("createdTxStamp");
      this.nonPrimaryKeyNames = new ArrayList<String>();
      this.nonPrimaryKeyNames.addAll(allFieldsNames);
      this.nonPrimaryKeyNames.removeAll(primaryKeyNames);
  }

  /**
   * Constructor with a repository.
   * @param repository a <code>RepositoryInterface</code> value
   */
  public AmazonProductImage(RepositoryInterface repository) {
      this();
      initRepository(repository);
  }

    /**
     * Auto generated value setter.
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     * Auto generated value setter.
     * @param statusId the statusId to set
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    /**
     * Auto generated value setter.
     * @param processingDocumentId the processingDocumentId to set
     */
    public void setProcessingDocumentId(Long processingDocumentId) {
        this.processingDocumentId = processingDocumentId;
    }
    /**
     * Auto generated value setter.
     * @param postTimestamp the postTimestamp to set
     */
    public void setPostTimestamp(Timestamp postTimestamp) {
        this.postTimestamp = postTimestamp;
    }
    /**
     * Auto generated value setter.
     * @param postErrorMessage the postErrorMessage to set
     */
    public void setPostErrorMessage(String postErrorMessage) {
        this.postErrorMessage = postErrorMessage;
    }
    /**
     * Auto generated value setter.
     * @param postFailures the postFailures to set
     */
    public void setPostFailures(Long postFailures) {
        this.postFailures = postFailures;
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
     * @return <code>String</code>
     */
    public String getProductId() {
        return this.productId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>String</code>
     */
    public String getStatusId() {
        return this.statusId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Long</code>
     */
    public Long getProcessingDocumentId() {
        return this.processingDocumentId;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Timestamp</code>
     */
    public Timestamp getPostTimestamp() {
        return this.postTimestamp;
    }
    /**
     * Auto generated value accessor.
     * @return <code>String</code>
     */
    public String getPostErrorMessage() {
        return this.postErrorMessage;
    }
    /**
     * Auto generated value accessor.
     * @return <code>Long</code>
     */
    public Long getPostFailures() {
        return this.postFailures;
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

    /**
     * Auto generated method that gets the related <code>AmazonProduct</code> by the relation named <code>AmazonProduct</code>.
     * @return the <code>AmazonProduct</code>
     * @throws RepositoryException if an error occurs
     */
    public AmazonProduct getAmazonProduct() throws RepositoryException {
        if (this.amazonProduct == null) {
            this.amazonProduct = getRelatedOne(AmazonProduct.class, "AmazonProduct");
        }
        return this.amazonProduct;
    }
    /**
     * Auto generated method that gets the related <code>AmazonProductImageAck</code> by the relation named <code>AmazonProductImageAck</code>.
     * @return the list of <code>AmazonProductImageAck</code>
     * @throws RepositoryException if an error occurs
     */
    public List<? extends AmazonProductImageAck> getAmazonProductImageAcks() throws RepositoryException {
        if (this.amazonProductImageAcks == null) {
            this.amazonProductImageAcks = getRelated(AmazonProductImageAck.class, "AmazonProductImageAck");
        }
        return this.amazonProductImageAcks;
    }

    /**
     * Auto generated value setter.
     * @param amazonProduct the amazonProduct to set
    */
    public void setAmazonProduct(AmazonProduct amazonProduct) {
        this.amazonProduct = amazonProduct;
    }
    /**
     * Auto generated value setter.
     * @param amazonProductImageAcks the amazonProductImageAcks to set
    */
    public void setAmazonProductImageAcks(List<AmazonProductImageAck> amazonProductImageAcks) {
        this.amazonProductImageAcks = amazonProductImageAcks;
    }

    /**
     * Auto generated method that add item to collection.
     */
    public void addAmazonProductImageAck(AmazonProductImageAck amazonProductImageAck) {
        if (this.amazonProductImageAcks == null) {
            this.amazonProductImageAcks = new ArrayList<AmazonProductImageAck>();
        }
        this.amazonProductImageAcks.add(amazonProductImageAck);
    }
    /**
     * Auto generated method that remove item from collection.
     */
    public void removeAmazonProductImageAck(AmazonProductImageAck amazonProductImageAck) {
        if (this.amazonProductImageAcks == null) {
            return;
        }
        this.amazonProductImageAcks.remove(amazonProductImageAck);
    }
    /**
     * Auto generated method that clear items from collection.
     */
    public void clearAmazonProductImageAck() {
        if (this.amazonProductImageAcks == null) {
            return;
        }
        this.amazonProductImageAcks.clear();
    }

    /** {@inheritDoc} */
    @Override
    public void fromMap(Map<String, Object> mapValue) {
        preInit();
        setProductId((String) mapValue.get("productId"));
        setStatusId((String) mapValue.get("statusId"));
        setProcessingDocumentId((Long) mapValue.get("processingDocumentId"));
        setPostTimestamp((Timestamp) mapValue.get("postTimestamp"));
        setPostErrorMessage((String) mapValue.get("postErrorMessage"));
        setPostFailures((Long) mapValue.get("postFailures"));
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
        mapValue.put("productId", getProductId());
        mapValue.put("statusId", getStatusId());
        mapValue.put("processingDocumentId", getProcessingDocumentId());
        mapValue.put("postTimestamp", getPostTimestamp());
        mapValue.put("postErrorMessage", getPostErrorMessage());
        mapValue.put("postFailures", getPostFailures());
        mapValue.put("lastUpdatedStamp", getLastUpdatedStamp());
        mapValue.put("lastUpdatedTxStamp", getLastUpdatedTxStamp());
        mapValue.put("createdStamp", getCreatedStamp());
        mapValue.put("createdTxStamp", getCreatedTxStamp());
        return mapValue;
    }


}
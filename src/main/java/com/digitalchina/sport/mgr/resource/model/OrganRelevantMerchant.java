package com.digitalchina.sport.mgr.resource.model;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: organ_relevant_merchant
@mbggenerated do_not_delete_during_merge 2017-06-28 13:43:37
 */
public class OrganRelevantMerchant implements Serializable {
    /**
     * Column: organ_relevant_merchant.id
    @mbggenerated 2017-06-28 13:43:37
     */
    private String id;

    /**
     *   类型（1、主场馆 2、培训机构）
     * Column: organ_relevant_merchant.type
    @mbggenerated 2017-06-28 13:43:37
     */
    private String type;

    /**
     *   机构ID
     * Column: organ_relevant_merchant.organ_id
    @mbggenerated 2017-06-28 13:43:37
     */
    private String organ_id;

    /**
     *   合作商户ID
     * Column: organ_relevant_merchant.merchant_id
    @mbggenerated 2017-06-28 13:43:37
     */
    private String merchant_id;

    /**
     *   创建时间
     * Column: organ_relevant_merchant.create_time
    @mbggenerated 2017-06-28 13:43:37
     */
    private Date create_time;

    /**
     *   修改时间
     * Column: organ_relevant_merchant.update_time
    @mbggenerated 2017-06-28 13:43:37
     */
    private Date update_time;

    /**
     * Table: organ_relevant_merchant
    @mbggenerated 2017-06-28 13:43:37
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOrgan_id() {
        return organ_id;
    }

    public void setOrgan_id(String organ_id) {
        this.organ_id = organ_id == null ? null : organ_id.trim();
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id == null ? null : merchant_id.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
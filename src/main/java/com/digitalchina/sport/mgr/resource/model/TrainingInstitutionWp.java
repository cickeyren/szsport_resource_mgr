package com.digitalchina.sport.mgr.resource.model;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: training_institution_wp
@mbggenerated do_not_delete_during_merge 2017-06-13 13:41:22
 */
public class TrainingInstitutionWp implements Serializable {
    /**
     * Column: training_institution_wp.id
    @mbggenerated 2017-06-13 13:41:22
     */
    private String id;

    /**
     *   机构id
     * Column: training_institution_wp.institution_id
    @mbggenerated 2017-06-13 13:41:22
     */
    private String institution_id;

    /**
     *   商户id
     * Column: training_institution_wp.merchant_id
    @mbggenerated 2017-06-13 13:41:22
     */
    private String merchant_id;

    /**
     *   支付方式id
     * Column: training_institution_wp.payment_id
    @mbggenerated 2017-06-13 13:41:22
     */
    private String payment_id;

    /**
     *   创建时间
     * Column: training_institution_wp.create_time
    @mbggenerated 2017-06-13 13:41:22
     */
    private Date create_time;

    /**
     *   修改时间
     * Column: training_institution_wp.update_time
    @mbggenerated 2017-06-13 13:41:22
     */
    private Date update_time;

    /**
     *   备注
     * Column: training_institution_wp.remark
    @mbggenerated 2017-06-13 13:41:22
     */
    private String remark;

    /**
     * Table: training_institution_wp
    @mbggenerated 2017-06-13 13:41:22
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(String institution_id) {
        this.institution_id = institution_id == null ? null : institution_id.trim();
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id == null ? null : merchant_id.trim();
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id == null ? null : payment_id.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
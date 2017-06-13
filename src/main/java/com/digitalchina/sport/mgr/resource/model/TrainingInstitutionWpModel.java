package com.digitalchina.sport.mgr.resource.model;

/**
 * （一句话描述）
 * 作用：
 *
 * @author xujin
 * @date 2017/6/8 17:25
 * @see
 */
public class TrainingInstitutionWpModel {

    private String id;

    private String institution_id;

    private String merchant_id;

    private String payment_id;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(String institution_id) {
        this.institution_id = institution_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

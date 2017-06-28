package com.digitalchina.sport.mgr.resource.model;

/**
 * （一句话描述）
 * 作用：
 *
 * @author xujin
 * @date 2017/6/8 17:25
 * @see
 */
public class OrganRelevantMerchantModel {

    private String id;

    private String organ_id;

    private String merchant_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgan_id() {
        return organ_id;
    }

    public void setOrgan_id(String organ_id) {
        this.organ_id = organ_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }
}

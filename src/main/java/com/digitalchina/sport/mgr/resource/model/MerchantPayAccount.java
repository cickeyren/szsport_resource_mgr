package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "merchant_pay_account")
public class MerchantPayAccount {
    /**
     * 表ID
     */
    @Id
    private String id;

    /**
     * 商户ID
     */
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 支付方式 0：支付宝 1：微信 
     */
    @Column(name = "pay_way")
    private String payWay;

    /**
     * 账号
     */
    @Column(name = "seller_id")
    private String sellerId;

    /**
     * 合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    @Column(name = "partner_id")
    private String partnerId;

    /**
     * 应用ID
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 签名方式 RSA,RSA2
     */
    @Column(name = "sign_type")
    private String signType;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 第三方支付key
     */
    @Column(name = "pay_key")
    private String payKey;

    /**
     * 获取表ID
     *
     * @return id - 表ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置表ID
     *
     * @param id 表ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取商户ID
     *
     * @return merchant_id - 商户ID
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户ID
     *
     * @param merchantId 商户ID
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    /**
     * 获取支付方式 0：支付宝 1：微信 
     *
     * @return pay_way - 支付方式 0：支付宝 1：微信 
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * 设置支付方式 0：支付宝 1：微信 
     *
     * @param payWay 支付方式 0：支付宝 1：微信 
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    /**
     * 获取账号
     *
     * @return seller_id - 账号
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * 设置账号
     *
     * @param sellerId 账号
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    /**
     * 获取合作身份者ID，以2088开头由16位纯数字组成的字符串
     *
     * @return partner_id - 合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * 设置合作身份者ID，以2088开头由16位纯数字组成的字符串
     *
     * @param partnerId 合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    /**
     * 获取应用ID
     *
     * @return app_id - 应用ID
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置应用ID
     *
     * @param appId 应用ID
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 获取签名方式 RSA,RSA2
     *
     * @return sign_type - 签名方式 RSA,RSA2
     */
    public String getSignType() {
        return signType;
    }

    /**
     * 设置签名方式 RSA,RSA2
     *
     * @param signType 签名方式 RSA,RSA2
     */
    public void setSignType(String signType) {
        this.signType = signType == null ? null : signType.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取第三方支付key
     *
     * @return pay_key - 第三方支付key
     */
    public String getPayKey() {
        return payKey;
    }

    /**
     * 设置第三方支付key
     *
     * @param payKey 第三方支付key
     */
    public void setPayKey(String payKey) {
        this.payKey = payKey == null ? null : payKey.trim();
    }
}
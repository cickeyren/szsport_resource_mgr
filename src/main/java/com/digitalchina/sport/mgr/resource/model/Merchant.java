package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

public class Merchant {
    /**
     * 商户编号
     */
    @Id
    private String id;

    /**
     * 商家名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 省级
     */
    @Column(name = "provincial_level")
    private String provincialLevel;

    /**
     * 市级
     */
    @Column(name = "city_level")
    private String cityLevel;

    /**
     * 区级
     */
    @Column(name = "district_level")
    private String districtLevel;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 合作方式
     */
    @Column(name = "cooperation_way")
    private String cooperationWay;

    /**
     * 支付方式(废弃)
     */
    @Column(name = "pay_way")
    private String payWay;

    /**
     * 支付方式账户(废弃)
     */
    @Column(name = "pay_way_account")
    private String payWayAccount;

    /**
     * 支付方式key(废弃)
     */
    @Column(name = "pay_way_key")
    private String payWayKey;

    /**
     * 结算方式
     */
    @Column(name = "settlement_way")
    private String settlementWay;

    /**
     * 状态(0:营业中1:关闭)
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取商户编号
     *
     * @return id - 商户编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商户编号
     *
     * @param id 商户编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取商家名称
     *
     * @return merchant_name - 商家名称
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置商家名称
     *
     * @param merchantName 商家名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 获取省级
     *
     * @return provincial_level - 省级
     */
    public String getProvincialLevel() {
        return provincialLevel;
    }

    /**
     * 设置省级
     *
     * @param provincialLevel 省级
     */
    public void setProvincialLevel(String provincialLevel) {
        this.provincialLevel = provincialLevel == null ? null : provincialLevel.trim();
    }

    /**
     * 获取市级
     *
     * @return city_level - 市级
     */
    public String getCityLevel() {
        return cityLevel;
    }

    /**
     * 设置市级
     *
     * @param cityLevel 市级
     */
    public void setCityLevel(String cityLevel) {
        this.cityLevel = cityLevel == null ? null : cityLevel.trim();
    }

    /**
     * 获取区级
     *
     * @return district_level - 区级
     */
    public String getDistrictLevel() {
        return districtLevel;
    }

    /**
     * 设置区级
     *
     * @param districtLevel 区级
     */
    public void setDistrictLevel(String districtLevel) {
        this.districtLevel = districtLevel == null ? null : districtLevel.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取联系人
     *
     * @return contacts - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     * 获取电话
     *
     * @return telephone - 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 获取合作方式
     *
     * @return cooperation_way - 合作方式
     */
    public String getCooperationWay() {
        return cooperationWay;
    }

    /**
     * 设置合作方式
     *
     * @param cooperationWay 合作方式
     */
    public void setCooperationWay(String cooperationWay) {
        this.cooperationWay = cooperationWay == null ? null : cooperationWay.trim();
    }

    /**
     * 获取支付方式(废弃)
     *
     * @return pay_way - 支付方式(废弃)
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * 设置支付方式(废弃)
     *
     * @param payWay 支付方式(废弃)
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    /**
     * 获取支付方式账户(废弃)
     *
     * @return pay_way_account - 支付方式账户(废弃)
     */
    public String getPayWayAccount() {
        return payWayAccount;
    }

    /**
     * 设置支付方式账户(废弃)
     *
     * @param payWayAccount 支付方式账户(废弃)
     */
    public void setPayWayAccount(String payWayAccount) {
        this.payWayAccount = payWayAccount == null ? null : payWayAccount.trim();
    }

    /**
     * 获取支付方式key(废弃)
     *
     * @return pay_way_key - 支付方式key(废弃)
     */
    public String getPayWayKey() {
        return payWayKey;
    }

    /**
     * 设置支付方式key(废弃)
     *
     * @param payWayKey 支付方式key(废弃)
     */
    public void setPayWayKey(String payWayKey) {
        this.payWayKey = payWayKey == null ? null : payWayKey.trim();
    }

    /**
     * 获取结算方式
     *
     * @return settlement_way - 结算方式
     */
    public String getSettlementWay() {
        return settlementWay;
    }

    /**
     * 设置结算方式
     *
     * @param settlementWay 结算方式
     */
    public void setSettlementWay(String settlementWay) {
        this.settlementWay = settlementWay == null ? null : settlementWay.trim();
    }

    /**
     * 获取状态(0:营业中1:关闭)
     *
     * @return status - 状态(0:营业中1:关闭)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态(0:营业中1:关闭)
     *
     * @param status 状态(0:营业中1:关闭)
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
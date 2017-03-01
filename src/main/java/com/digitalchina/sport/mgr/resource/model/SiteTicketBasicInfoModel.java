package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;

/**
 * @Author:wangw
 * @Description:场地票基本信息
 * @Date:Created in 2017/2/27.
 */
public class SiteTicketBasicInfoModel {
    /**
     * 场地票标识
     */
    private String id;
    /**
     * 套票类型
     */
    private String ticketType;
    /**
     * 主场馆ID
     */
    private String mainStadium;
    /**
     * 子场馆ID
     */
    private String subStadium;
    /**
     * 门票名称
     */
    private String ticketName;
    /**
     * 合作商户ID
     */
    private String merchant;
    /**
     * 停止预订
     * 0：开场前停止预订 1：开场后停止预订 2：闭场前停止预订
     */
    private String stopOrderType;
    /**
     * 开场前多少分钟停止预订
     */
    private String beforeOpenTime;
    /**
     * 开场后多少分钟停止预订
     */
    private String afterOpenTime;
    /**
     * 闭场前多少分钟停止预订
     */
    private String beforeCloseTime;
    /**
     * 可预订多少日内门票
     */
    private String reserveTime;
    /**
     * 生效起始时间
     */
    private String availableStartTime;
    /**
     * 生效终止时间
     */
    private String availableEndTime;
    /**
     * 退款规则
     * 0：不可退 1：随时退 2：条件退
     */
    private String orderRefundRule;
    /**
     * 开场前多少小时不可退
     */
    private String noRefundTime;
    /**
     * 售卖渠道
     * 0:线上售卖 1：窗口售卖 2:自助售票机
     */
    private String sellWay;
    /**
     * 起订时限
     */
    private String minimumTimeLimit;
    /**
     * 限订场次数
     */
    private String siteNumLimit;
    /**
     * 验票凭证
     * 0:验证码 1：身份证 2：市民卡
     */
    private String checkTicketType;
    /**
     * 验票渠道
     * 0:支持闸机验票1:支持窗口验票 2:支持手持终端验票
     */
    private String checkTicketWay;
    /**
     * 进场时间
     */
    private String approachTime;
    /**
     * 离场时间
     */
    private String departureTime;
    /**
     * 限用人数
     */
    private String peopleNumLimit;
    /**
     * 预订说明
     */
    private String orderDescription;
    /**
     * 退票说明
     */
    private String refundDescription;
    /**
     * 场地票状态
     */
    private String ticketState;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getMainStadium() {
        return mainStadium;
    }

    public void setMainStadium(String mainStadium) {
        this.mainStadium = mainStadium;
    }

    public String getSubStadium() {
        return subStadium;
    }

    public void setSubStadium(String subStadium) {
        this.subStadium = subStadium;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getStopOrderType() {
        return stopOrderType;
    }

    public void setStopOrderType(String stopOrderType) {
        this.stopOrderType = stopOrderType;
    }

    public String getBeforeOpenTime() {
        return beforeOpenTime;
    }

    public void setBeforeOpenTime(String beforeOpenTime) {
        this.beforeOpenTime = beforeOpenTime;
    }

    public String getAfterOpenTime() {
        return afterOpenTime;
    }

    public void setAfterOpenTime(String afterOpenTime) {
        this.afterOpenTime = afterOpenTime;
    }

    public String getBeforeCloseTime() {
        return beforeCloseTime;
    }

    public void setBeforeCloseTime(String beforeCloseTime) {
        this.beforeCloseTime = beforeCloseTime;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getAvailableStartTime() {
        return availableStartTime;
    }

    public void setAvailableStartTime(String availableStartTime) {
        this.availableStartTime = availableStartTime;
    }

    public String getAvailableEndTime() {
        return availableEndTime;
    }

    public void setAvailableEndTime(String availableEndTime) {
        this.availableEndTime = availableEndTime;
    }

    public String getOrderRefundRule() {
        return orderRefundRule;
    }

    public void setOrderRefundRule(String orderRefundRule) {
        this.orderRefundRule = orderRefundRule;
    }

    public String getNoRefundTime() {
        return noRefundTime;
    }

    public void setNoRefundTime(String noRefundTime) {
        this.noRefundTime = noRefundTime;
    }

    public String getSellWay() {
        return sellWay;
    }

    public void setSellWay(String sellWay) {
        this.sellWay = sellWay;
    }

    public String getMinimumTimeLimit() {
        return minimumTimeLimit;
    }

    public void setMinimumTimeLimit(String minimumTimeLimit) {
        this.minimumTimeLimit = minimumTimeLimit;
    }

    public String getSiteNumLimit() {
        return siteNumLimit;
    }

    public void setSiteNumLimit(String siteNumLimit) {
        this.siteNumLimit = siteNumLimit;
    }

    public String getCheckTicketType() {
        return checkTicketType;
    }

    public void setCheckTicketType(String checkTicketType) {
        this.checkTicketType = checkTicketType;
    }

    public String getCheckTicketWay() {
        return checkTicketWay;
    }

    public void setCheckTicketWay(String checkTicketWay) {
        this.checkTicketWay = checkTicketWay;
    }

    public String getApproachTime() {
        return approachTime;
    }

    public void setApproachTime(String approachTime) {
        this.approachTime = approachTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getPeopleNumLimit() {
        return peopleNumLimit;
    }

    public void setPeopleNumLimit(String peopleNumLimit) {
        this.peopleNumLimit = peopleNumLimit;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getRefundDescription() {
        return refundDescription;
    }

    public void setRefundDescription(String refundDescription) {
        this.refundDescription = refundDescription;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

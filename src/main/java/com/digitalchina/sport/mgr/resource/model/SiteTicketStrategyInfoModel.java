package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;

/**
 * @Author:wangw
 * @Description:场地票策略信息
 * @Date:Created in 2017/3/7.
 */
public class SiteTicketStrategyInfoModel {
    /**
     * 策略标识
     */
    private String id;
    /**
     * 场地票编号
     */
    private String ticketId;
    /**
     * 策略名称
     */
    private String strategyName;
    /**
     * 子场馆编号
     */
    private String subStadium;
    /**
     * 选择场地
     */
    private String site;
    /**
     * 日期类型
     */
    private String dateType;
    /**
     * 指定周
     */
    private String weekDetails;
    /**
     * 指定月
     */
    private String monthDetails;
    /**
     * 指定日
     */
    private String specificDate;
    /**
     * 子场馆时段ID
     */
    private String timeCode;
    /**
     * 时段详细
     */
    private String timeInterval;
    /**
     * 销售价
     */
    private String sellPrice;
    /**
     * 成本价
     */
    private String costPrice;
    /**
     * 门市价
     */
    private String storePrice;
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

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getSubStadium() {
        return subStadium;
    }

    public void setSubStadium(String subStadium) {
        this.subStadium = subStadium;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getWeekDetails() {
        return weekDetails;
    }

    public void setWeekDetails(String weekDetails) {
        this.weekDetails = weekDetails;
    }

    public String getMonthDetails() {
        return monthDetails;
    }

    public void setMonthDetails(String monthDetails) {
        this.monthDetails = monthDetails;
    }

    public String getSpecificDate() {
        return specificDate;
    }

    public void setSpecificDate(String specificDate) {
        this.specificDate = specificDate;
    }

    public String getTimeCode() {
        return timeCode;
    }

    public void setTimeCode(String timeCode) {
        this.timeCode = timeCode;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(String storePrice) {
        this.storePrice = storePrice;
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

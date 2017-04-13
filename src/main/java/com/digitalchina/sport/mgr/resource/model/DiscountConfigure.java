package com.digitalchina.sport.mgr.resource.model;


import java.sql.Timestamp;

/**
 * 优惠
 */
public class DiscountConfigure {
    private String id;
    private String discountType;
    private String mainStadiumId;
    private String subStadiumId;
    private String discountChannel;
    private String payType;
    private String discountLimit;
    private String startTime;
    private String endTime;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getMainStadiumId() {
        return mainStadiumId;
    }

    public void setMainStadiumId(String mainStadiumId) {
        this.mainStadiumId = mainStadiumId;
    }

    public String getSubStadiumId() {
        return subStadiumId;
    }

    public void setSubStadiumId(String subStadiumId) {
        this.subStadiumId = subStadiumId;
    }

    public String getDiscountChannel() {
        return discountChannel;
    }

    public void setDiscountChannel(String discountChannel) {
        this.discountChannel = discountChannel;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDiscountLimit() {
        return discountLimit;
    }

    public void setDiscountLimit(String discountLimit) {
        this.discountLimit = discountLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
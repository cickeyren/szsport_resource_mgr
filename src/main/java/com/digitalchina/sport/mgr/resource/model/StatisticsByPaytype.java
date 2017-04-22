package com.digitalchina.sport.mgr.resource.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "statistics_by_paytype")
public class StatisticsByPaytype {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 主场馆
     */
    @Column(name = "stadium_name")
    private String stadiumName;
    /**
     * 主场馆id
     */
    @Column(name = "stadium_id")
    private String stadiumId;
    /**
     * 主场馆id
     */
    @Column(name = "ticket_type")
    private String ticketType;
    /**
     * 是否日结 0 日结，1临时统计
     */
    @Column(name = "if_daily")
    private String ifDaily;

    /**
     * 子场馆id
     */
    @Column(name = "sub_stadium_id")
    private String subStadiumId;
    @Column(name = "sub_stadium_name")
    private String subStadiumName;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private String createDate;

    /**
     * 支付方式（现金）
     */
    private BigDecimal cash;

    /**
     * 支付方式（支付宝）
     */
    private BigDecimal alipay;

    /**
     * 支付方式（微信）
     */
    private BigDecimal wechat;

    /**
     * 苏州银行
     */
    @Column(name = "suzhou_bank")
    private BigDecimal suzhouBank;

    /**
     * 苏州专用
     */
    @Column(name = "suzhou_special")
    private BigDecimal suzhouSpecial;
    /**
     * 一卡通
     */
    @Column(name = "one_card_solution")
    private BigDecimal oneCardSolution;

    /**
     * 医保卡
     */
    @Column(name = "medicare_card")
    private BigDecimal medicareCard;

    /**
     * 转账
     */
    @Column(name = "transfer_accounts")
    private BigDecimal transferAccounts;

    /**
     * 渤海银行
     */
    @Column(name = "bh_bank")
    private BigDecimal bhBank;

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public String getSubStadiumId() {
        return subStadiumId;
    }

    public String getSubStadiumName() {
        return subStadiumName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public BigDecimal getAlipay() {
        return alipay;
    }

    public BigDecimal getWechat() {
        return wechat;
    }

    public BigDecimal getSuzhouBank() {
        return suzhouBank;
    }

    public BigDecimal getSuzhouSpecial() {
        return suzhouSpecial;
    }

    public BigDecimal getOneCardSolution() {
        return oneCardSolution;
    }

    public BigDecimal getMedicareCard() {
        return medicareCard;
    }

    public BigDecimal getTransferAccounts() {
        return transferAccounts;
    }

    public BigDecimal getBhBank() {
        return bhBank;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public void setSubStadiumId(String subStadiumId) {
        this.subStadiumId = subStadiumId;
    }

    public void setSubStadiumName(String subStadiumName) {
        this.subStadiumName = subStadiumName;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public void setAlipay(BigDecimal alipay) {
        this.alipay = alipay;
    }

    public void setWechat(BigDecimal wechat) {
        this.wechat = wechat;
    }

    public void setSuzhouBank(BigDecimal suzhouBank) {
        this.suzhouBank = suzhouBank;
    }

    public void setSuzhouSpecial(BigDecimal suzhouSpecial) {
        this.suzhouSpecial = suzhouSpecial;
    }

    public void setOneCardSolution(BigDecimal oneCardSolution) {
        this.oneCardSolution = oneCardSolution;
    }

    public void setMedicareCard(BigDecimal medicareCard) {
        this.medicareCard = medicareCard;
    }

    public void setTransferAccounts(BigDecimal transferAccounts) {
        this.transferAccounts = transferAccounts;
    }

    public void setBhBank(BigDecimal bhBank) {
        this.bhBank = bhBank;
    }

    public String getStadiumId() {
        return stadiumId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setStadiumId(String stadiumId) {
        this.stadiumId = stadiumId;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getIfDaily() {
        return ifDaily;
    }

    public void setIfDaily(String ifDaily) {
        this.ifDaily = ifDaily;
    }
}
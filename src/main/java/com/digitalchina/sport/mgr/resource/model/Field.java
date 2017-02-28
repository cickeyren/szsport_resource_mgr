package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

public class Field {
    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 场地名称
     */
    @Column(name = "field_name")
    private String fieldName;
    /**
     * 场地名称
     */
    @Column(name = "lock_stauts")
    private String lockStauts;

    /**
     * 展示名称
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * 场地状态(1正常，0闭馆，2作废)
     */
    private String status;

    /**
     * 所属场馆编号
     */
    @Column(name = "stadium_id")
    private String stadiumId;

    /**
     * 场地简介
     */
    private String introduction;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取场地名称
     *
     * @return field_name - 场地名称
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 设置场地名称
     *
     * @param fieldName 场地名称
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**
     * 获取展示名称
     *
     * @return display_name - 展示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置展示名称
     *
     * @param displayName 展示名称
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * 获取场地状态(1正常，0闭馆，2作废)
     *
     * @return status - 场地状态(1正常，0闭馆，2作废)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置场地状态(1正常，0闭馆，2作废)
     *
     * @param status 场地状态(1正常，0闭馆，2作废)
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取所属场馆编号
     *
     * @return stadium_id - 所属场馆编号
     */
    public String getStadiumId() {
        return stadiumId;
    }

    /**
     * 设置所属场馆编号
     *
     * @param stadiumId 所属场馆编号
     */
    public void setStadiumId(String stadiumId) {
        this.stadiumId = stadiumId == null ? null : stadiumId.trim();
    }

    /**
     * 获取场地简介
     *
     * @return introduction - 场地简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置场地简介
     *
     * @param introduction 场地简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getLockStauts() {
        return lockStauts;
    }

    public void setLockStauts(String lockStauts) {
        this.lockStauts = lockStauts;
    }
}
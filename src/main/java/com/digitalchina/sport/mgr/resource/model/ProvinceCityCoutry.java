package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

@Table(name = "province_city_coutry")
public class ProvinceCityCoutry {
    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父ID,0标识跟省份
     */
    @Column(name = "parentID")
    private String parentid;

    /**
     * 0-无效，1-有效
     */
    private String enabled;

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
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父ID,0标识跟省份
     *
     * @return parentID - 父ID,0标识跟省份
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 设置父ID,0标识跟省份
     *
     * @param parentid 父ID,0标识跟省份
     */
    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    /**
     * 获取0-无效，1-有效
     *
     * @return enabled - 0-无效，1-有效
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * 设置0-无效，1-有效
     *
     * @param enabled 0-无效，1-有效
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }
}
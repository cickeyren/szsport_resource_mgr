package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sub_stadium")
public class SubStadium {
    /**
     * 编号
     */
    @Id
    private String id;

    /**
     * 子场馆名称
     */
    private String name;

    /**
     * 所属项目分类
     */
    private String classify;

    /**
     * 父级场馆编号
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 状态（1正常，0闭馆，2作废）
     */
    private String status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取子场馆名称
     *
     * @return name - 子场馆名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置子场馆名称
     *
     * @param name 子场馆名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取所属项目分类
     *
     * @return classify - 所属项目分类
     */
    public String getClassify() {
        return classify;
    }

    /**
     * 设置所属项目分类
     *
     * @param classify 所属项目分类
     */
    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    /**
     * 获取父级场馆编号
     *
     * @return parent_id - 父级场馆编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级场馆编号
     *
     * @param parentId 父级场馆编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取状态（1正常，0闭馆，2作废）
     *
     * @return status - 状态（1正常，0闭馆，2作废）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（1正常，0闭馆，2作废）
     *
     * @param status 状态（1正常，0闭馆，2作废）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 获取简介
     *
     * @return introduction - 简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置简介
     *
     * @param introduction 简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}
package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

public class Classify {
    /**
     * 分类id
     */
    @Id
    private Integer cid;

    /**
     * 级别
     */
    @Column(name = "level_id")
    private Integer levelId;

    /**
     * 父类id
     */
    private Integer pid;

    /**
     * 分类类目名称
     */
    @Column(name = "categoryName")
    private String categoryname;

    /**
     * 分类排序
     */
    private Integer sort;

    /**
     * 分类图片
     */
    private String picture;

    /**
     * 是否含有子类1是0否
     */
    private String ishavesub;

    /**
     * 获取分类id
     *
     * @return cid - 分类id
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置分类id
     *
     * @param cid 分类id
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取级别
     *
     * @return level_id - 级别
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * 设置级别
     *
     * @param levelId 级别
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取父类id
     *
     * @return pid - 父类id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父类id
     *
     * @param pid 父类id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取分类类目名称
     *
     * @return categoryName - 分类类目名称
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * 设置分类类目名称
     *
     * @param categoryname 分类类目名称
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    /**
     * 获取分类排序
     *
     * @return sort - 分类排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置分类排序
     *
     * @param
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取分类图片
     *
     * @return picture - 分类图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置分类图片
     *
     * @param picture 分类图片
     */
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    /**
     * 获取是否含有子类1是0否
     *
     * @return ishavesub - 是否含有子类1是0否
     */
    public String getIshavesub() {
        return ishavesub;
    }

    /**
     * 设置是否含有子类1是0否
     *
     * @param ishavesub 是否含有子类1是0否
     */
    public void setIshavesub(String ishavesub) {
        this.ishavesub = ishavesub == null ? null : ishavesub.trim();
    }
}
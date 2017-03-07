package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "time_interval")
public class TimeInterval {
    /**
     * 时间间隔编号
     */
    private String id;

    /**
     * 时段编号(与时段表id进行关联)
     */
    private String time_code;

    /**
     * 子场馆ID
     */
    private String substadium_id;

    /**
     * 时段间隔
     */
    private String time_inter;

    /**
     * 时段排序号
     */
    private Integer time_sort;

    /**
     * 创建时间
     */
    private Date creat_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 获取时间间隔编号
     *
     * @return id - 时间间隔编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置时间间隔编号
     *
     * @param id 时间间隔编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取时段编号(与时段表id进行关联)
     *
     * @return time_code - 时段编号(与时段表id进行关联)
     */
    public String getTime_code() {
        return time_code;
    }

    /**
     * 设置时段编号(与时段表id进行关联)
     *
     * @param time_code 时段编号(与时段表id进行关联)
     */
    public void setTime_code(String time_code) {
        this.time_code = time_code == null ? null : time_code.trim();
    }

    /**
     * 获取子场馆ID
     *
     * @return substadium_id - 子场馆ID
     */
    public String getSubstadium_id() {
        return substadium_id;
    }

    /**
     * 设置子场馆ID
     *
     * @param substadium_id 子场馆ID
     */
    public void setSubstadium_id(String substadium_id) {
        this.substadium_id = substadium_id == null ? null : substadium_id.trim();
    }

    /**
     * 获取时段间隔
     *
     * @return time_inter - 时段间隔
     */
    public String getTime_inter() {
        return time_inter;
    }

    /**
     * 设置时段间隔
     *
     * @param time_inter 时段间隔
     */
    public void setTime_inter(String time_inter) {
        this.time_inter = time_inter == null ? null : time_inter.trim();
    }

    /**
     * 获取时段排序号
     *
     * @return time_sort - 时段排序号
     */
    public Integer getTime_sort() {
        return time_sort;
    }

    /**
     * 设置时段排序号
     *
     * @param time_sort 时段排序号
     */
    public void setTime_sort(Integer time_sort) {
        this.time_sort = time_sort;
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Date getCreat_time() {
        return creat_time;
    }

    /**
     * 设置创建时间
     *
     * @param creat_time 创建时间
     */
    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置更新时间
     *
     * @param update_time 更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
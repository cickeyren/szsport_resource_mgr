package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "time_frame")
public class TimeFrame {
    /**
     * 时段编号
     */
    @Id
    private String id;

    /**
     * 时段名称
     */
    @Column(name = "time_name")
    private String timeName;

    /**
     * 时长
     */
    @Column(name = "time_length")
    private String timeLength;

    /**
     * 开始时间
     */
    @Column(name = "time_start")
    private String timeStart;

    /**
     * 场数
     */
    private Integer num;

    /**
     * 时间间隔
     */
    @Column(name = "time_lag")
    private String timeLag;

    /**
     * 状态：1正常，0作废
     */
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取时段编号
     *
     * @return id - 时段编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置时段编号
     *
     * @param id 时段编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取时段名称
     *
     * @return time_name - 时段名称
     */
    public String getTimeName() {
        return timeName;
    }

    /**
     * 设置时段名称
     *
     * @param timeName 时段名称
     */
    public void setTimeName(String timeName) {
        this.timeName = timeName == null ? null : timeName.trim();
    }

    /**
     * 获取时长
     *
     * @return time_length - 时长
     */
    public String getTimeLength() {
        return timeLength;
    }

    /**
     * 设置时长
     *
     * @param timeLength 时长
     */
    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength == null ? null : timeLength.trim();
    }

    /**
     * 获取开始时间
     *
     * @return time_start - 开始时间
     */
    public String getTimeStart() {
        return timeStart;
    }

    /**
     * 设置开始时间
     *
     * @param timeStart 开始时间
     */
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart == null ? null : timeStart.trim();
    }

    /**
     * 获取场数
     *
     * @return num - 场数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置场数
     *
     * @param num 场数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取时间间隔
     *
     * @return time_lag - 时间间隔
     */
    public String getTimeLag() {
        return timeLag;
    }

    /**
     * 设置时间间隔
     *
     * @param timeLag 时间间隔
     */
    public void setTimeLag(String timeLag) {
        this.timeLag = timeLag == null ? null : timeLag.trim();
    }

    /**
     * 获取状态：1正常，0作废
     *
     * @return status - 状态：1正常，0作废
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：1正常，0作废
     *
     * @param status 状态：1正常，0作废
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置创建时间
     *
     * @param creatTime 创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
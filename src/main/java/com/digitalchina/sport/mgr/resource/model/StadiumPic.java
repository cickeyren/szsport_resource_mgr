package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "stadium_pic")
public class StadiumPic {
    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 图片地址
     */
    @Column(name = "pic_address")
    private String picAddress;

    /**
     * 是否是首图（0不是，1是）
     */
    @Column(name = "is_first")
    private String isFirst;

    /**
     * 对应场馆id
     */
    @Column(name = "stadium_id")
    private String stadiumId;

    /**
     * 上传时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取图片地址
     *
     * @return pic_address - 图片地址
     */
    public String getPicAddress() {
        return picAddress;
    }

    /**
     * 设置图片地址
     *
     * @param picAddress 图片地址
     */
    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress == null ? null : picAddress.trim();
    }

    /**
     * 获取是否是首图（0不是，1是）
     *
     * @return is_first - 是否是首图（0不是，1是）
     */
    public String getIsFirst() {
        return isFirst;
    }

    /**
     * 设置是否是首图（0不是，1是）
     *
     * @param isFirst 是否是首图（0不是，1是）
     */
    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst == null ? null : isFirst.trim();
    }

    /**
     * 获取对应场馆id
     *
     * @return stadium_id - 对应场馆id
     */
    public String getStadiumId() {
        return stadiumId;
    }

    /**
     * 设置对应场馆id
     *
     * @param stadiumId 对应场馆id
     */
    public void setStadiumId(String stadiumId) {
        this.stadiumId = stadiumId == null ? null : stadiumId.trim();
    }

    /**
     * 获取上传时间
     *
     * @return create_time - 上传时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置上传时间
     *
     * @param createTime 上传时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
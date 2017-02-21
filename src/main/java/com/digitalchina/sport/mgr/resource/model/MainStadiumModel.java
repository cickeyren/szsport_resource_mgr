package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/20 16:25
 * @see
 */
public class MainStadiumModel {
    /**
     * ID
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 省级
     */
    private String provincial_level;
    /**
     * 市级
     */
    private String city_level ;
    /**
     * 区级
     */
    private String district_level;
    /**
     * 商圈
     */
    private String trading_area;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 开放时间
     */
    private String open_time;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 状态(1正常0闭馆2下线)
     */
    private String status;
    /**
     * 所属项目分类
     */
    private String classify;
    /**
     * 是否精选0：非精选1:精选
     */
    private String is_special;
    /**
     * 创建时间
     */
    private Date create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvincial_level() {
        return provincial_level;
    }

    public void setProvincial_level(String provincial_level) {
        this.provincial_level = provincial_level;
    }

    public String getCity_level() {
        return city_level;
    }

    public void setCity_level(String city_level) {
        this.city_level = city_level;
    }

    public String getDistrict_level() {
        return district_level;
    }

    public void setDistrict_level(String district_level) {
        this.district_level = district_level;
    }

    public String getTrading_area() {
        return trading_area;
    }

    public void setTrading_area(String trading_area) {
        this.trading_area = trading_area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getIs_special() {
        return is_special;
    }

    public void setIs_special(String is_special) {
        this.is_special = is_special;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }


}

package com.digitalchina.sport.mgr.resource.model;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: training_institution
@mbggenerated do_not_delete_during_merge 2017-06-09 13:15:57
 */
public class TrainingInstitution implements Serializable {
    /**
     *   培训结构id
     * Column: training_institution.id
    @mbggenerated 2017-06-09 13:15:57
     */
    private String id;

    /**
     *   培训机构名称
     * Column: training_institution.org_name
    @mbggenerated 2017-06-09 13:15:57
     */
    private String org_name;

    /**
     *   机构所在区域
     * Column: training_institution.area
    @mbggenerated 2017-06-09 13:15:57
     */
    private String area;

    /**
     *   省级
     * Column: training_institution.provincial_level
    @mbggenerated 2017-06-09 13:15:57
     */
    private String provincial_level;

    /**
     *   市级
     * Column: training_institution.city_level
    @mbggenerated 2017-06-09 13:15:57
     */
    private String city_level;

    /**
     *   区级
     * Column: training_institution.district_level
    @mbggenerated 2017-06-09 13:15:57
     */
    private String district_level;

    /**
     *   商圈
     * Column: training_institution.trading_area
    @mbggenerated 2017-06-09 13:15:57
     */
    private String trading_area;

    /**
     *   具体地址
     * Column: training_institution.address
    @mbggenerated 2017-06-09 13:15:57
     */
    private String address;

    /**
     *   联系电话
     * Column: training_institution.telephone
    @mbggenerated 2017-06-09 13:15:57
     */
    private String telephone;

    /**
     *   logo地址url
     * Column: training_institution.logo_url
    @mbggenerated 2017-06-09 13:15:57
     */
    private String logo_url;

    /**
     *   机构简介
     * Column: training_institution.introduction
    @mbggenerated 2017-06-09 13:15:57
     */
    private String introduction;

    /**
     *   机构图片地址url
     * Column: training_institution.img
    @mbggenerated 2017-06-09 13:15:57
     */
    private String img;

    /**
     *   状态 0 下线 1正常 2 闭馆
     * Column: training_institution.status
    @mbggenerated 2017-06-09 13:15:57
     */
    private String status;

    /**
     *   创建时间
     * Column: training_institution.create_time
    @mbggenerated 2017-06-09 13:15:57
     */
    private Date create_time;

    /**
     *   更新时间
     * Column: training_institution.update_time
    @mbggenerated 2017-06-09 13:15:57
     */
    private Date update_time;

    /**
     *   经度
     * Column: training_institution.lng
    @mbggenerated 2017-06-09 13:15:57
     */
    private String lng;

    /**
     *   纬度
     * Column: training_institution.lat
    @mbggenerated 2017-06-09 13:15:57
     */
    private String lat;

    /**
     * Table: training_institution
    @mbggenerated 2017-06-09 13:15:57
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name == null ? null : org_name.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getProvincial_level() {
        return provincial_level;
    }

    public void setProvincial_level(String provincial_level) {
        this.provincial_level = provincial_level == null ? null : provincial_level.trim();
    }

    public String getCity_level() {
        return city_level;
    }

    public void setCity_level(String city_level) {
        this.city_level = city_level == null ? null : city_level.trim();
    }

    public String getDistrict_level() {
        return district_level;
    }

    public void setDistrict_level(String district_level) {
        this.district_level = district_level == null ? null : district_level.trim();
    }

    public String getTrading_area() {
        return trading_area;
    }

    public void setTrading_area(String trading_area) {
        this.trading_area = trading_area == null ? null : trading_area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url == null ? null : logo_url.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }
}
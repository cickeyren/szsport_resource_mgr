package com.digitalchina.sport.mgr.resource.model;

import java.util.Date;
import javax.persistence.*;

public class Curriculum {
    @Id
    private Integer id;

    /**
     * 培训机构id
     */
    private String training_institutions_id;

    /**
     * 合作商家id
     */
    private String cooperative_merchant_id;

    /**
     * 图片地址
     */
    private String img_url;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 教师
     */
    private String teachers;

    /**
     * 培训类型
     */
    private String train_type;

    /**
     * 课程介绍
     */
    private String content;

    /**
     * 地址
     */
    private String address;

    /**
     * 报名必填
     */
    private String enrollment_required;

    /**
     * 电话
     */
    private String phone;

    /**
     * 报名须知
     */
    private String enrollment_read;

    private Date create_date;

    private Date recommend_time;

    private Integer recommend_level;
    private Integer status;
    private Integer recommend;
    private String org_name;

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    private String merchant_name;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取培训机构id
     *
     * @return training_institutions_id - 培训机构id
     */
    public String getTraining_institutions_id() {
        return training_institutions_id;
    }

    /**
     * 设置培训机构id
     *
     * @param training_institutions_id 培训机构id
     */
    public void setTraining_institutions_id(String training_institutions_id) {
        this.training_institutions_id = training_institutions_id == null ? null : training_institutions_id.trim();
    }

    /**
     * 获取合作商家id
     *
     * @return cooperative_merchant_id - 合作商家id
     */
    public String getCooperative_merchant_id() {
        return cooperative_merchant_id;
    }

    /**
     * 设置合作商家id
     *
     * @param cooperative_merchant_id 合作商家id
     */
    public void setCooperative_merchant_id(String cooperative_merchant_id) {
        this.cooperative_merchant_id = cooperative_merchant_id == null ? null : cooperative_merchant_id.trim();
    }

    /**
     * 获取图片地址
     *
     * @return img_url - 图片地址
     */
    public String getImg_url() {
        return img_url;
    }

    /**
     * 设置图片地址
     *
     * @param img_url 图片地址
     */
    public void setImg_url(String img_url) {
        this.img_url = img_url == null ? null : img_url.trim();
    }

    /**
     * 获取课程名称
     *
     * @return name - 课程名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置课程名称
     *
     * @param name 课程名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取教师
     *
     * @return teachers - 教师
     */
    public String getTeachers() {
        return teachers;
    }

    /**
     * 设置教师
     *
     * @param teachers 教师
     */
    public void setTeachers(String teachers) {
        this.teachers = teachers == null ? null : teachers.trim();
    }

    /**
     * 获取培训类型
     *
     * @return train_type - 培训类型
     */
    public String getTrain_type() {
        return train_type;
    }

    /**
     * 设置培训类型
     *
     * @param train_type 培训类型
     */
    public void setTrain_type(String train_type) {
        this.train_type = train_type == null ? null : train_type.trim();
    }

    /**
     * 获取课程介绍
     *
     * @return content - 课程介绍
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置课程介绍
     *
     * @param content 课程介绍
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取报名必填
     *
     * @return enrollment_required - 报名必填
     */
    public String getEnrollment_required() {
        return enrollment_required;
    }

    /**
     * 设置报名必填
     *
     * @param enrollment_required 报名必填
     */
    public void setEnrollment_required(String enrollment_required) {
        this.enrollment_required = enrollment_required == null ? null : enrollment_required.trim();
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取报名须知
     *
     * @return enrollment_read - 报名须知
     */
    public String getEnrollment_read() {
        return enrollment_read;
    }

    /**
     * 设置报名须知
     *
     * @param enrollment_read 报名须知
     */
    public void setEnrollment_read(String enrollment_read) {
        this.enrollment_read = enrollment_read == null ? null : enrollment_read.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * @param create_date
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * @return recommend_time
     */
    public Date getRecommend_time() {
        return recommend_time;
    }

    /**
     * @param recommend_time
     */
    public void setRecommend_time(Date recommend_time) {
        this.recommend_time = recommend_time;
    }

    /**
     * @return recommend_level
     */
    public Integer getRecommend_level() {
        return recommend_level;
    }

    /**
     * @param recommend_level
     */
    public void setRecommend_level(Integer recommend_level) {
        this.recommend_level = recommend_level;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }
}
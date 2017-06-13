package com.digitalchina.sport.mgr.resource.model;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: training_institution_pic
@mbggenerated do_not_delete_during_merge 2017-06-12 20:43:39
 */
public class TrainingInstitutionPic implements Serializable {
    /**
     *   ID
     * Column: training_institution_pic.id
    @mbggenerated 2017-06-12 20:43:39
     */
    private String id;

    /**
     *   图片地址
     * Column: training_institution_pic.pic_url
    @mbggenerated 2017-06-12 20:43:39
     */
    private String pic_url;

    /**
     *   是否是首图（0不是，1是）
     * Column: training_institution_pic.is_first
    @mbggenerated 2017-06-12 20:43:39
     */
    private String is_first;

    /**
     *   培训机构id
     * Column: training_institution_pic.institution_id
    @mbggenerated 2017-06-12 20:43:39
     */
    private String institution_id;

    /**
     *   上传时间
     * Column: training_institution_pic.create_time
    @mbggenerated 2017-06-12 20:43:39
     */
    private Date create_time;

    /**
     *   修改时间
     * Column: training_institution_pic.update_time
    @mbggenerated 2017-06-12 20:43:39
     */
    private Date update_time;

    /**
     * Table: training_institution_pic
    @mbggenerated 2017-06-12 20:43:39
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url == null ? null : pic_url.trim();
    }

    public String getIs_first() {
        return is_first;
    }

    public void setIs_first(String is_first) {
        this.is_first = is_first == null ? null : is_first.trim();
    }

    public String getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(String institution_id) {
        this.institution_id = institution_id == null ? null : institution_id.trim();
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
}
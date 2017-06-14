package com.digitalchina.sport.mgr.resource.model;

import java.io.Serializable;

/**
 * [STRATO MyBatis Generator]
 * Table: curriculum_discount
@mbggenerated do_not_delete_during_merge 2017-06-14 16:30:59
 */
public class CurriculumDiscount implements Serializable {
    /**
     * Column: curriculum_discount.id
    @mbggenerated 2017-06-14 16:30:59
     */
    private String id;

    /**
     *   课程培训id
     * Column: curriculum_discount.curriculum_id
    @mbggenerated 2017-06-14 16:30:59
     */
    private Integer curriculum_id;

    /**
     *   折扣面值id
     * Column: curriculum_discount.discount_value_id
    @mbggenerated 2017-06-14 16:30:59
     */
    private String discount_value_id;

    /**
     * Table: curriculum_discount
    @mbggenerated 2017-06-14 16:30:59
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(Integer curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getDiscount_value_id() {
        return discount_value_id;
    }

    public void setDiscount_value_id(String discount_value_id) {
        this.discount_value_id = discount_value_id == null ? null : discount_value_id.trim();
    }
}
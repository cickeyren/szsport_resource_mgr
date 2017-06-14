package com.digitalchina.sport.mgr.resource.model;

/**
 * （一句话描述）
 * 作用：
 *
 * @author xujin
 * @date 2017/6/8 17:25
 * @see
 */
public class CurriculumDiscountModel {

    private String id;

    private Integer curriculum_id;

    private Integer curriculum_id_new;

    private String discount_value_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(Integer curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public Integer getCurriculum_id_new() {
        return curriculum_id_new;
    }

    public void setCurriculum_id_new(Integer curriculum_id_new) {
        this.curriculum_id_new = curriculum_id_new;
    }

    public String getDiscount_value_id() {
        return discount_value_id;
    }

    public void setDiscount_value_id(String discount_value_id) {
        this.discount_value_id = discount_value_id;
    }
}

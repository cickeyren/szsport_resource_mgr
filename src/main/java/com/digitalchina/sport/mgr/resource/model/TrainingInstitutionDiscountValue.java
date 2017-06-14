package com.digitalchina.sport.mgr.resource.model;

import java.io.Serializable;

/**
 * [STRATO MyBatis Generator]
 * Table: training_institution_discount_value
@mbggenerated do_not_delete_during_merge 2017-06-14 16:04:41
 */
public class TrainingInstitutionDiscountValue implements Serializable {
    /**
     *   折扣ID
     * Column: training_institution_discount_value.id
    @mbggenerated 2017-06-14 16:04:41
     */
    private String id;

    /**
     *   折扣值
     * Column: training_institution_discount_value.value
    @mbggenerated 2017-06-14 16:04:41
     */
    private String value;

    /**
     *   折扣名称
     * Column: training_institution_discount_value.name
    @mbggenerated 2017-06-14 16:04:41
     */
    private String name;

    /**
     * Column: training_institution_discount_value.sort
    @mbggenerated 2017-06-14 16:04:41
     */
    private Integer sort;

    /**
     * Table: training_institution_discount_value
    @mbggenerated 2017-06-14 16:04:41
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
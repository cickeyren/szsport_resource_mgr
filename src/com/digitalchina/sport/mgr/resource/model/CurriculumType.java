package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

@Table(name = "curriculum_type")
public class CurriculumType {
    @Id
    private Integer id;

    /**
     * 类型code
     */
    private String name;

    /**
     * 类型code
     */
    private String code;

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
     * 获取类型code
     *
     * @return name - 类型code
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型code
     *
     * @param name 类型code
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取类型code
     *
     * @return code - 类型code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置类型code
     *
     * @param code 类型code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}
package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


public class CurriculumClassTimes extends CurriculumClass {


    /**
     * 班次id
     */
    private String class_id;

    /**
     * 班次上课时段
     */
    private String time;

    public String getClass_id() {
        return class_id;
    }

    public String getTime() {
        return time;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
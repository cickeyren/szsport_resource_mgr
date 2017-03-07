package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

public class Province {
    @Id
    private Integer id;

    private String provinceID;

    private String province;

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
     * @return provinceID
     */
    public String getProvinceID() {
        return provinceID;
    }

    /**
     * @param provinceID
     */
    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID == null ? null : provinceID.trim();
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }
}
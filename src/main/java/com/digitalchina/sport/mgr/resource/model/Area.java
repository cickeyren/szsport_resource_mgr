package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

public class Area {
    @Id
    private Integer id;

    private String areaID;

    private String area;

    private String father;

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
     * @return areaID
     */
    public String getAreaID() {
        return areaID;
    }

    /**
     * @param areaID
     */
    public void setAreaID(String areaID) {
        this.areaID = areaID == null ? null : areaID.trim();
    }

    /**
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * @return father
     */
    public String getFather() {
        return father;
    }

    /**
     * @param father
     */
    public void setFather(String father) {
        this.father = father == null ? null : father.trim();
    }
}
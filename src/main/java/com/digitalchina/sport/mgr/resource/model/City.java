package com.digitalchina.sport.mgr.resource.model;

import javax.persistence.*;

public class City {
    @Id
    private Integer id;

    private String cityID;

    private String city;

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
     * @return cityID
     */
    public String getCityID() {
        return cityID;
    }

    /**
     * @param cityID
     */
    public void setCityID(String cityID) {
        this.cityID = cityID == null ? null : cityID.trim();
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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
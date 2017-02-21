package com.digitalchina.sport.mgr.resource.model;

/**
 * @Author:wangw
 * @Description:设备信息
 * @Date:Created in 2017/2/20.
 */
public class EquipmentModel {
    /**
     * 设备标识id
     */
    private String id;
    /**
     * 设备编号
     */
    private String equipment_id;
    /**
     * 设备类型
     */
    private String equipment_name;
    /**
     * 子场馆编号
     */
    private String stadium_id;
    /**
     * 主场馆编号
     */
    private String parent_id;
    /**
     * 状态
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getStadium_id() {
        return stadium_id;
    }

    public void setStadium_id(String stadium_id) {
        this.stadium_id = stadium_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

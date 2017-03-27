package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.EquipmentModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:设备管理
 * @Date:Created in 2017/2/20.
 */
@Mapper
public interface EquipmentDao {
    /**
     * 模糊查询出所有的子场馆ID列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<String> getSubStadiumIdList(Map<String, Object> map) throws Exception;

    /**
     * 根据参数查询设备列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getEquipmentList(Map<String, Object> map) throws Exception;

    /**
     * 获取模糊查询的设备对应的子场馆信息
     * @param map
     * @return
     * @throws Exception
     */
    public List<String> getSubStadiumNameList(Map<String, Object> map) throws Exception;

    /**
     * 根据参数查询设备列表的总数量
     */
    public int getEquipmentTotalCount(Map<String, Object> map) throws Exception;

    /**
     * 添加设备信息
     * @param equipmentModel
     * @return
     * @throws Exception
     */
    public int addEquipment(EquipmentModel equipmentModel) throws Exception;

    /**
     * 查询设备信息
     * @param map
     * @return
     * @throws Exception
     */
    public EquipmentModel getEquipmentInfo(Map<String, Object> map) throws Exception;

    /**
     * 编辑设备信息
     * @param equipmentModel
     * @return
     * @throws Exception
     */
    public int updateEquipment(EquipmentModel equipmentModel) throws Exception;

    /**
     * 删除设备信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delEquipment(Map<String, Object> map) throws Exception;
}

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
     * 根据参数查询设备列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getEquipmentList(Map<String, Object> map) throws Exception;

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

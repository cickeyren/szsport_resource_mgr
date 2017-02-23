package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.EquipmentDao;
import com.digitalchina.sport.mgr.resource.dao.SubStadiumMapper;
import com.digitalchina.sport.mgr.resource.model.EquipmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:设备管理
 * @Date:Created in 2017/2/20.
 */
@Service
public class EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    //子场馆信息
    @Autowired
    private SubStadiumMapper subStadiumMapper;

    /**
     * 根据参数查询设备列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getEquipmentList(Map<String, Object> map) throws Exception{
        return equipmentDao.getEquipmentList(map);
    }

    /**
     * 根据参数查询设备列表的总数量
     * @param map
     * @return
     * @throws Exception
     */
    public int getEquipmentTotalCount(Map<String, Object> map){
        int count = 0;
        try {
            count = equipmentDao.getEquipmentTotalCount(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 添加设备信息
     * @param equipmentModel
     * @return
     * @throws Exception
     */
    public int addEquipment(EquipmentModel equipmentModel) throws Exception{
        return equipmentDao.addEquipment(equipmentModel);
    }

    /**
     * 根据主场馆ID查询子场馆
     * @param map
     * @return
     */
    public List<Map<String, Object>> getSubStadiumByParentId(Map<String, Object> map){
        return subStadiumMapper.getAllSubStadiumByParentId(map);
    }

    /**
     * 查询设备信息
     * @param map
     * @return
     * @throws Exception
     */
    public EquipmentModel getEquipmentInfo(Map<String, Object> map) throws Exception{
        return  equipmentDao.getEquipmentInfo(map);
    }

    /**
     * 编辑设备信息
     * @param equipmentModel
     * @return
     * @throws Exception
     */
    public int updateEquipment(EquipmentModel equipmentModel) throws Exception{
        return equipmentDao.updateEquipment(equipmentModel);
    }

    /**
     * 删除设备信息
     * @param map
     * @return
     * @throws Exception
     */
    public int delEquipment(Map<String, Object> map) throws Exception{
        return equipmentDao.delEquipment(map);
    }
}

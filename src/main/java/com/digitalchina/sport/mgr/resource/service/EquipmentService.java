package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.EquipmentDao;
import com.digitalchina.sport.mgr.resource.model.EquipmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 添加设备信息
     * @param equipmentModel
     * @return
     * @throws Exception
     */
    public int addEquipment(EquipmentModel equipmentModel) throws Exception{
        return equipmentDao.addEquipment(equipmentModel);
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

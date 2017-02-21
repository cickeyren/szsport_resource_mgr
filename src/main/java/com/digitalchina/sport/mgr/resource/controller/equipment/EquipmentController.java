package com.digitalchina.sport.mgr.resource.controller.equipment;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.model.EquipmentModel;
import com.digitalchina.sport.mgr.resource.service.EquipmentService;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:设备管理
 * @Date:Created in 2017/2/20.
 */
@Controller
@RequestMapping(value = "/equipment")
public class EquipmentController {
    public static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    private EquipmentService equipmentService;

    /**
     * 进入设备列表页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/equipmentList.html")
    public String equipmentList(HttpServletRequest request, HttpServletResponse response, ModelMap map){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        paramMap.put("equipmentId", request.getParameter("equipmentId"));
        paramMap.put("equipmentType", request.getParameter("equipmentType"));
        paramMap.put("mainStadium", request.getParameter("mainStadium"));
        paramMap.put("subStadium", request.getParameter("subStadium"));
        try {
            list = equipmentService.getEquipmentList(paramMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========查询设备信息失败=========",e);
        }
        map.put("equipmentList", list);
        map.put("equipmentId", request.getParameter("equipmentId"));
        map.put("equipmentType", request.getParameter("equipmentType"));
        map.put("mainStadium", request.getParameter("mainStadium"));
        map.put("subStadium", request.getParameter("subStadium"));
        return "equipment/equipment_list";
    }

    /**
     * 按条件查询设备列表
     * @param equipmentId
     * @param equipmentType
     * @param mainStadium
     * @param subStadium
     * @return
     */
    @RequestMapping(value = "/searchEquipmentList.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData searchEquipmentList(String equipmentId, String equipmentType, String mainStadium, String subStadium){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("equipmentId",equipmentId);
        paramMap.put("equipmentType",equipmentType);
        paramMap.put("mainStadium",mainStadium);
        paramMap.put("subStadium",subStadium);
        try {
            List<Map<String,Object>> list = equipmentService.getEquipmentList(paramMap);
            return RtnData.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========查询设备信息失败=========",e);
        }
        return RtnData.fail("查询设备信息失败");
    }

    /**
     * 进入设备新增页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/addEquipment.html")
    public String addEquipment(ModelMap map){
        return "equipment/add_equipment";
    }

    /**
     * 添加设备信息
     * @param equipmentId
     * @param equipmentType
     * @param mainStadium
     * @param subStadium
     * @param status
     * @return
     */
    @RequestMapping(value = "/addEquipment.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addEquipment(String equipmentId, String equipmentType, String mainStadium, String subStadium, String status){
        EquipmentModel equipmentModel = new EquipmentModel();
        String id = UUIDUtil.generateUUID();
        equipmentModel.setId(id);
        equipmentModel.setEquipment_id(equipmentId);
        equipmentModel.setEquipment_name(equipmentType);
        equipmentModel.setParent_id(mainStadium);
        equipmentModel.setStadium_id(subStadium);
        equipmentModel.setStatus(status);
        try {
            equipmentService.addEquipment(equipmentModel);
            return RtnData.ok("添加设备成功");
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========添加设备失败=========",e);
        }
        return RtnData.fail("添加设备失败");
    }

    /**
     * 进入设备编辑页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/editEquipment.html")
    public String editEquipment(ModelMap map, String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id",id);
        try {
            map.put("equipment", equipmentService.getEquipmentInfo(paramMap));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========查询设备失败=========",e);
        }
        return "equipment/edit_equipment";
    }

    /**
     * 编辑设备信息
     * @param equipmentId
     * @param equipmentType
     * @param mainStadium
     * @param subStadium
     * @param status
     * @return
     */
    @RequestMapping(value = "/updateEquipment.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData updateEquipment(String id, String equipmentId, String equipmentType, String mainStadium, String subStadium, String status){
        EquipmentModel equipmentModel = new EquipmentModel();
        equipmentModel.setId(id);
        equipmentModel.setEquipment_id(equipmentId);
        equipmentModel.setEquipment_name(equipmentType);
        equipmentModel.setParent_id(mainStadium);
        equipmentModel.setStadium_id(subStadium);
        equipmentModel.setStatus(status);
        try {
            equipmentService.updateEquipment(equipmentModel);
            return RtnData.ok("编辑设备成功");
        } catch (Exception e){
            e.printStackTrace();
            logger.error("========编辑设备失败=========",e);
        }
        return RtnData.fail("编辑设备失败");
    }

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delEquipment.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delEquipment(String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        try {
            equipmentService.delEquipment(paramMap);
            return RtnData.ok("删除设备成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========删除设备失败=========",e);
        }
        return RtnData.fail("删除设备失败");
    }
}

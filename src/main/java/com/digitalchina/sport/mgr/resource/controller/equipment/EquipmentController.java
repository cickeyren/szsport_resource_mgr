package com.digitalchina.sport.mgr.resource.controller.equipment;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.dao.SubStadiumMapper;
import com.digitalchina.sport.mgr.resource.model.EquipmentModel;
import com.digitalchina.sport.mgr.resource.service.EquipmentService;
import com.digitalchina.sport.mgr.resource.service.MainStadiumService;
import com.digitalchina.sport.mgr.resource.service.StadiumService;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    //设备信息
    @Autowired
    private EquipmentService equipmentService;
    //主场馆信息
    @Autowired
    private MainStadiumService mainStadiumService;

    /**
     * 进入设备列表页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/equipmentList.html")
    public String equipmentList(@RequestParam(required = false, defaultValue = "5") long pageSize,
                                @RequestParam(required = false, defaultValue = "1") long page,
                                HttpServletRequest request, ModelMap map){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        paramMap.put("equipmentId", request.getParameter("equipmentId"));
        paramMap.put("equipmentType", request.getParameter("equipmentType"));
        paramMap.put("mainStadium", request.getParameter("mainStadium"));
        paramMap.put("subStadium", request.getParameter("subStadium"));
        int totalSize = equipmentService.getEquipmentTotalCount(paramMap);
        Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
        pagination.setUrl(request.getRequestURI());
        paramMap.put("pageIndex", pagination.getStartIndex());
        paramMap.put("pageSize", pageSize);
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
        map.put("pageModel", pagination);
        map.put("pageSize",String.valueOf(pageSize));
        map.put("page",String.valueOf(page));
        return "equipment/equipment_list";
    }

    /**
     * 进入设备新增页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/addEquipment.html")
    public String addEquipment(ModelMap map){
        List<Map<String, Object>> mainStadiumList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> subStadiumList = new ArrayList<Map<String, Object>>();
        try {
            mainStadiumList = mainStadiumService.findStadiumModel();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("parent_id", mainStadiumList.get(0).get("id"));
            subStadiumList = equipmentService.getSubStadiumByParentId(paramMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========进入设备新增页面失败=========",e);
        }
        map.put("mainStadiumList", mainStadiumList);
        map.put("subStadiumList", subStadiumList);
        return "equipment/add_equipment";
    }

    /**
     * 根据主场馆ID查询子场馆
     * @param mainStadiumId
     * @return
     */
    @RequestMapping(value = "/getSubStadiumByParentId.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData getSubStadiumByParentId(String mainStadiumId){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parent_id", mainStadiumId);
        try {
            return RtnData.ok(equipmentService.getSubStadiumByParentId(paramMap));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========根据主场馆ID查询子场馆失败=========",e);
        }
        return RtnData.fail("根据主场馆ID查询子场馆失败");
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
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("equipment_id",equipmentId);
        try {
            EquipmentModel equipment  = equipmentService.getEquipmentInfo(paramMap);
            if(equipment != null){
                return RtnData.fail("设备编号已存在，请重新输入");
            }
            EquipmentModel equipmentModel = new EquipmentModel();
            String id = UUIDUtil.generateUUID();
            equipmentModel.setId(id);
            equipmentModel.setEquipment_id(equipmentId);
            equipmentModel.setEquipment_name(equipmentType);
            equipmentModel.setParent_id(mainStadium);
            equipmentModel.setStadium_id(subStadium);
            equipmentModel.setStatus(status);
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
        EquipmentModel equipmentModel = new EquipmentModel();
        List<Map<String, Object>> mainStadiumList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> subStadiumList = new ArrayList<Map<String, Object>>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id",id);
        try {
            equipmentModel = equipmentService.getEquipmentInfo(paramMap);
            Map<String, Object> stadiumMap = new HashMap<String, Object>();
            stadiumMap.put("parent_id", equipmentModel.getParent_id());
            subStadiumList = equipmentService.getSubStadiumByParentId(stadiumMap);
            mainStadiumList = mainStadiumService.findStadiumModel();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========查询设备失败=========",e);
        }
        map.put("mainStadiumList", mainStadiumList);
        map.put("subStadiumList", subStadiumList);
        map.put("equipment", equipmentModel);
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

package com.digitalchina.sport.mgr.resource.controller.field;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.controller.mainstadium.MainStadiumController;
import com.digitalchina.sport.mgr.resource.model.Field;
import com.digitalchina.sport.mgr.resource.service.FieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 子场地 -- 场地模块controller
 * <p>
 * create by wanggw 2017-02-25
 */
@Controller
@RequestMapping(value = "/FieldController")
public class FieldController {

    @Autowired
    private FieldService fieldService;
    //LOG
    public static final Logger LOGGER = LoggerFactory.getLogger(MainStadiumController.class);

    /**
     * 进入主馆场
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/subField.html")
    public String getAllSubFieldList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                     @RequestParam(required = false, defaultValue = "1") long page,
                                     ModelMap map, HttpServletRequest request) {

        String subStadiumid = request.getParameter("subStadiumid");
//        subStadiumid = "11002";
        Map<String, Object> param = new HashMap<>();
        param.put("stadium_id", subStadiumid);

        try {

            int totalSize = fieldService.getTotalCount(param);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            param.put("startIndex", pagination.getStartIndex());
            param.put("endIndex", pageSize);
            List<Map<String, Object>> subFieldList = fieldService.getAllSubFiled(param);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));


            map.put("subStadiumid", subStadiumid);
            map.put("subFieldList", subFieldList);

            return "field/subFiled";

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询子场馆场地数据失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 跳转到新增页面
     * <p>
     * 注意：需要携带子场馆的ID
     *
     * @param subStadiumId 子场地ID
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String add(HttpServletRequest request, ModelMap modelMap) {

        modelMap.put("subStadiumId", request.getParameter("subStadiumid"));
        return "field/subFieldAdd";

    }

    /**
     * 跳转到新增页面
     * <p>
     * 注意：需要携带子场馆的ID
     *
     * @param subStadiumId 子场地ID
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap) {

        //先取出用户选择要编辑的场地ID
        Field field = new Field();
        field.setId(request.getParameter("id"));
        field = fieldService.getFieldById(field);
        //然后查询出该条信息
        modelMap.put("fieldEntity", field);

        return "field/subFieldEdit";
    }

    /**
     * 子场馆场地新增信息方法
     *
     * @param field    子场馆场地实体对象
     * @param modelMap 视图
     * @return
     */
    @RequestMapping(value = "/addSubField.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addSubField(Field field, ModelMap modelMap) {

        try {
            String maxId = fieldService.getMaxId();
            field.setId((maxId == null || maxId.equals("")) ? "1000001" : String.valueOf(Long.parseLong(maxId) + 1));
            int rowCount = fieldService.insertField(field);
            if (rowCount > 0) {
                return RtnData.ok("新增子场馆场地成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增子场馆场地失败=========", e);
        }
        return RtnData.fail("新增子场馆场地失败");
    }


    /**
     * 子场馆场地编辑信息方法
     *
     * @param field    子场馆场地实体对象
     * @param modelMap 视图
     * @return
     */
    @RequestMapping(value = "/editSubField.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData editSubField(Field field, ModelMap modelMap) {

        try {
            int rowCount = fieldService.updateField(field);
            if (rowCount > 0) {
                return RtnData.ok("子场馆场地更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========子场馆场地更新失败=========", e);
        }
        return RtnData.fail("子场馆场地失败");
    }

    /**
     * 删除选中的数据
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/deleteSubField.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData deleteField(Field field, ModelMap modelMap) {

        try {
            if (fieldService.deleteField(field) > 0) {
                return RtnData.ok("子场地删除成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========子场地删除失败=========", e);
        }

        return RtnData.fail("子场地删除失败！");
    }


}

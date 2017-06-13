package com.digitalchina.sport.mgr.resource.controller.trainInstitution;

import com.digitalchina.common.data.Constants;
import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.model.MainStadiumModel;
import com.digitalchina.sport.mgr.resource.model.TrainingInstitution;
import com.digitalchina.sport.mgr.resource.model.TrainingInstitutionModel;
import com.digitalchina.sport.mgr.resource.model.TrainingInstitutionPicModel;
import com.digitalchina.sport.mgr.resource.service.CommonService;
import com.digitalchina.sport.mgr.resource.service.TrainInstitutionService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xujin on 2017/6/8.
 */

//用于机构模块的控制层
@Controller
@RequestMapping(value = "/trainInstitutionController")
public class TrainInstitutionController {

    public static final Logger LOGGER = LoggerFactory.getLogger(TrainInstitutionController.class);

    @Autowired
    private TrainInstitutionService service;

    @Autowired
    private CommonService commonService;


    /**
     * 列表页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/list.html")
    public String getAllStadiumList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page, ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String name = request.getParameter("name");
        params.put("name", name);//获取查询条件
        try {
            int totalSize = service.getTrainInstitutionListCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());

            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));
            map.put("name", name);//回到页面,保留搜索关键字

            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> list = service.getTrainInstitutionList(params);
            map.put("list", list);
            return "trainInstitution/list";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询培训机构数据失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 进入新增页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String add(ModelMap map) {
        //获取所有省份列表
        List<Map<String, Object>> provinceList = commonService.getAllProvince();

        //获取所有市区数据(北京市区列表)
        Map<String, Object> params = new HashMap<>();
        params.put("provinceID", "110000");
        List<Map<String, Object>> cityList = commonService.getAllCity(params);

        //获取所有县区数据（北京市辖区列表）
        params.put("cityID", "110100");
        List<Map<String, Object>> areaList = commonService.getAllArea(params);

        map.put("provinceList", provinceList);
        map.put("cityList", cityList);
        map.put("areaList", areaList);

        return "trainInstitution/add";//进入对应的页面
    }


    /**
     * 新增培训机构
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addTrainInstitution.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(TrainingInstitutionModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.addTrainInstitution(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("新增培训机构成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增培训机构失败=========", e);
        }
        return RtnData.fail("新增培训机构失败");
    }


    /**
     * 进入编辑页面
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String edit(@RequestParam String id, ModelMap map) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        try {
            TrainingInstitution trainInstitution = service.selectById(param);

            if(trainInstitution!=null){
                //获取所有省份列表
                List<Map<String, Object>> provinceList = commonService.getAllProvince();
                //获取所有市区数据
                param.put("provinceID",trainInstitution.getProvincial_level());
                List<Map<String, Object>> cityList = commonService.getAllCity(param);
                //获取所有县区数据
                param.put("cityID", trainInstitution.getCity_level());
                List<Map<String, Object>> areaList = commonService.getAllArea(param);
                map.put("provinceList", provinceList);
                map.put("cityList", cityList);
                map.put("areaList", areaList);

                map.put("trainInstitution", trainInstitution);
            }

            return "trainInstitution/edit";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========进入编辑页面失败=========", e);
            return "error";
        }

    }

    /**
     * 更新
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/updateTrainInstitution.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(TrainingInstitutionModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.updateTrainInstitution(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("修改培训机构成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========修改培训机构失败=========", e);
        }
        return RtnData.fail("修改培训机构失败");
    }

    /**
     * 进入机构图片
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/pic.html")
    public String pic(ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        String id = request.getParameter("id");
        params.put("id", id);
        map.put("id", id);
        List<Map<String, Object>> picList = service.getPicList(params);
        map.put("picList", picList);
        return "trainInstitution/pic";
    }

    /**
     * 新增机构图片
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addInstitutionPic.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addInstitutionPic(TrainingInstitutionPicModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.addInstitutionPic(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("保存机构图片成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========保存机构图片失败=========", e);
        }
        return RtnData.fail("保存机构图片失败");
    }

    /**
     * 机构图片设为首图
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/setPicIsFirst.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData setPicIsFirst(TrainingInstitutionPicModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.setPicIsFirst(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("设置机构图片为首图成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========设置机构图片为首图失败=========", e);
        }
        return RtnData.fail("设置机构图片为首图失败");
    }



    /**
     * 删除机构图片
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/delInstitutionPic.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delInstitutionPic(TrainingInstitutionPicModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.delInstitutionPic(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("删除机构图片成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========删除机构图片失败=========", e);
        }
        return RtnData.fail("删除机构图片失败");
    }
}

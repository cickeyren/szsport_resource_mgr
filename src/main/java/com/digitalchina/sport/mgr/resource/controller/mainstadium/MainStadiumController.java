package com.digitalchina.sport.mgr.resource.controller.mainstadium;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.model.MainStadiumModel;
import com.digitalchina.sport.mgr.resource.model.Province;
import com.digitalchina.sport.mgr.resource.service.MainStadiumService;
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
import java.util.*;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/20 16:05
 * @see
 */
@Controller
@RequestMapping(value = "/mainStadiumController")
public class MainStadiumController {
    public static final Logger LOGGER = LoggerFactory.getLogger(MainStadiumController.class);
    @Autowired
    private MainStadiumService mainStadiumService;


    /**
     * 进入主馆场
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/mainstadium.html")
    public String getAllStadiumList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page,
                                    ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("name"));//获取查询条件
        try {
            int totalSize = mainStadiumService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> mainStadiumServiceAllStadiumList = mainStadiumService.getAllStadiumList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));
            map.put("name", request.getParameter("name"));//回到页面,保留搜索关键字
            map.put("mainstadiumlist", mainStadiumServiceAllStadiumList);
            return "mainstadium/mainstadium";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询主场馆数据失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }
    /**
     * 进入主馆场
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/mainstadiumforyearticket.html")
    public String getAllStadiumListByTicket(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page,
                                    ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", request.getParameter("name"));//获取查询条件
        try {
            int totalSize = mainStadiumService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> mainStadiumServiceAllStadiumList = mainStadiumService.getAllStadiumList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));
            map.put("name", request.getParameter("name"));//回到页面,保留搜索关键字
            map.put("mainstadiumlist", mainStadiumServiceAllStadiumList);
            return "mainstadium/mainstadiumforyearticket";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========进入门票失败=========", e);
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
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>> mainStadiumModels = mainStadiumService.findStadiumModel();
        //获取所有省份列表
        List<Map<String, Object>> provinceLise = mainStadiumService.getAllProvince();
        //获取所有市区数据(北京市区列表)
        params.put("provinceID", "110000");
        List<Map<String, Object>> cityList = mainStadiumService.getAllCity(params);
        //获取所有县区数据（北京市辖区列表）
        params.put("cityID", "110100");
        List<Map<String, Object>> areaList = mainStadiumService.getAllArea(params);
        map.put("mainStadiumModels", mainStadiumModels);
        map.put("provinceLise", provinceLise);
        map.put("cityList", cityList);
        map.put("areaList", areaList);
        return "mainstadium/add_main_stadium";//进入对应的页面
    }

    /**
     * 获取城市列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCityByID.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData getCityByID(ModelMap map, HttpServletRequest request) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("provinceID", request.getParameter("provinceID"));
            List<Map<String, Object>> cityList = mainStadiumService.getAllCity(params);
            return RtnData.ok(cityList);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询城市列表失败=========", e);
        }
        return RtnData.fail("查询城市列表失败");
    }

    /**
     * 获取县区列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAreaByID.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData getAreaByID(ModelMap map, HttpServletRequest request) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("cityID", request.getParameter("cityID"));
            List<Map<String, Object>> areaList = mainStadiumService.getAllArea(params);
            return RtnData.ok(areaList);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询县区列表失败=========", e);
        }
        return RtnData.fail("查询县区列表失败");
    }


    /**
     * 新增
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addmainStadiumModel.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(MainStadiumModel mainStadiumModel, ModelMap map) {
        try {
            mainStadiumModel.setId(UUID.randomUUID().toString());
            mainStadiumModel.setCreate_time(new Date());
            mainStadiumModel.setIs_special("0");//默认为非精选场馆
            if (mainStadiumService.insertmainStadium(mainStadiumModel) > 0) {
                return RtnData.ok("新增场馆成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增场馆失败=========", e);
        }
        return RtnData.fail("新增场馆失败");
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
    public String edit(@RequestParam String mainstadiumid, ModelMap map) {
        Map<String, Object> param = new HashMap<>();
        param.put("mainstadiumid", mainstadiumid);
        try {
            MainStadiumModel mainStadiumModel = mainStadiumService.selectmainStadiumId(param);
            List<Map<String, Object>> mainstadiums = mainStadiumService.findStadiumModel();
            //获取所有省份列表
            List<Map<String, Object>> provinceLise = mainStadiumService.getAllProvince();
            //获取所有市区数据(北京市区列表)
            param.put("provinceID",mainStadiumModel.getProvincial_level());
            List<Map<String, Object>> cityList = mainStadiumService.getAllCity(param);
            //获取所有县区数据（北京市辖区列表）
            param.put("cityID", mainStadiumModel.getCity_level());
            List<Map<String, Object>> areaList = mainStadiumService.getAllArea(param);
            map.put("provinceLise", provinceLise);
            map.put("cityList", cityList);
            map.put("areaList", areaList);
            map.put("mainstadiums", mainstadiums);
            map.put("mainStadiumModel", mainStadiumModel);
            return "mainstadium/edit_main_stadium";
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
    @RequestMapping(value = "/updatemainstadium.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(MainStadiumModel mainStadiumModel, ModelMap map) {
        try {
            if (mainStadiumService.updateMainStadium(mainStadiumModel) > 0) {
                return RtnData.ok("修改场馆成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========修改场馆失败=========", e);
        }
        return RtnData.fail("修改场馆失败");
    }

    /**
     * 删除
     *
     * @param
     *
     * @return
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.GET)
    @ResponseBody
    public RtnData delete(@RequestParam String mainStadiumid) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", mainStadiumid);
        try {
            if (mainStadiumService.deleteMainStadium(param) > 0) {
                return RtnData.ok("删除场馆成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========删除场馆失败=========", e);
        }
        return RtnData.fail("删除场馆失败");
    }

    /**
     * 设为精选
     *
     * @param mainStadiumid
     *
     * @return
     */
    @RequestMapping(value = "/updataSelectFirst.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData updataSelectFirst(@RequestParam String mainStadiumid, @RequestParam String is_special) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", mainStadiumid);
        param.put("is_special", is_special);
        try {
            if (mainStadiumService.updataSelectFirst(param) > 0) {
                return RtnData.ok("设为精选成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========设为精选失败=========", e);
        }
        return RtnData.fail("设为精选失败");
    }
}

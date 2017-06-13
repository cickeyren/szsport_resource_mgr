package com.digitalchina.sport.mgr.resource.controller.common;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.sport.mgr.resource.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：公共的控制层
 *
 * @author xujin
 * @date 2017/6/8 16:05
 * @see
 */
@Controller
@RequestMapping(value = "/commonController")
public class CommonController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService service;

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
            List<Map<String, Object>> cityList = service.getAllCity(params);
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
            List<Map<String, Object>> areaList = service.getAllArea(params);
            return RtnData.ok(areaList);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询县区列表失败=========", e);
        }
        return RtnData.fail("查询县区列表失败");
    }

}

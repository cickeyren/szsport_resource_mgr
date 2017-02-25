package com.digitalchina.sport.mgr.resource.controller.substadium;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.model.SubStadium;
import com.digitalchina.sport.mgr.resource.service.SubStadiumService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/24 9:43
 * @see
 */
@Controller
@RequestMapping(value = "/subStadiumController")
public class SubStadiumController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SubStadiumController.class);

    @Autowired
    private SubStadiumService subStadiumService;


    /**
     * 进入子馆场列表页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/substadium.html")
    public String getAllStadiumList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page,
                                     ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            int totalSize = subStadiumService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> subStadiumList = subStadiumService.getAllsubStadiumList(params);
            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
            map.put("subStadiumList", subStadiumList);
            return "substadium/substadium";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询子场馆数据失败=========", e);
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
        List<Map<String, Object>> subStadiumList = subStadiumService.findsubStadium();
        map.put("subStadiumList", subStadiumList);
        return "substadium/add_substadium";//进入对应的页面
    }

    /**
     * 新增
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addsubStadium.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(SubStadium subStadium, ModelMap map) {
        try {
            if (subStadiumService.insertsubStadium(subStadium)>0){
                return RtnData.ok("新增子场馆成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增子场馆失败=========", e);
        }
        return RtnData.fail("新增子场馆失败");
    }

    /**
     * 进入编辑页面
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/edit.html")
    public String edit(@RequestParam String subStadiumid, ModelMap map) {
        Map<String,Object> param = new HashMap<>();
        param.put("subStadiumid",subStadiumid);
        try {

            SubStadium subStadium = subStadiumService.selectsubStadiumId(param);
            List<Map<String, Object>> subStadiumList = subStadiumService.findsubStadium();
            map.put("subStadiumControllers", subStadiumList);
            map.put("subStadiumControllerModel", subStadium);
            return "substadium/edit_main_stadium";
        }catch (Exception e){
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
    @RequestMapping(value = "/updatesubStadium.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(SubStadium subStadium, ModelMap map) {
        try {
            if (subStadiumService.updatesubStadium(subStadium)>0){
                return RtnData.ok("修改子场馆成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========修改子场馆失败=========", e);
        }
        return RtnData.fail("修改子场馆失败");
    }

    /**
     * 删除
     *
     * @param
     *
     * @return
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delete(@RequestParam String subStadiumid) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",subStadiumid);
        try {
            if (subStadiumService.deletesubStadium(param)>0){
                RtnData rtnData = new RtnData();
                rtnData.setMessage("删除子场馆成功");
                rtnData.ok("删除子场馆成功");
                return rtnData;
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========删除子场馆失败=========", e);
        }
        return RtnData.fail("删除子场馆失败");
    }



}

package com.digitalchina.sport.mgr.resource.controller.mainstadium;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.model.MainStadiumModel;
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
     * 进入主页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/home.html")
    public String home(ModelMap map) {
        return "mainstadium/main_stadium";
    }

    /**
     * 进入主馆场
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/mainstadium.do")
    public String add(@RequestParam(required = false, defaultValue = "10") long pageSize,
                      @RequestParam(required = false, defaultValue = "1") long page,
                      @RequestParam(required = false) String name, ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        try {
            int totalSize = mainStadiumService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pagination.getEndIndex());
            List<Map<String, Object>> mainStadiumServiceAllStadiumList = mainStadiumService.getAllStadiumList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("page", pagination);
            map.put("name", name);//回到页面,保留搜索关键字
            map.put("mainstadiumlist", mainStadiumServiceAllStadiumList);
            return "mainstadium/stadiumList::dataList";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询主场馆数据失败=========", e);
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
        List<Map<String, Object>> mainStadiumModels = mainStadiumService.findStadiumModel();
        map.put("mainStadiumModels", mainStadiumModels);
        return "mainstadium/add_main_stadium";//进入对应的页面
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
            if (mainStadiumService.insertmainStadium(mainStadiumModel)>0){
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
    @RequestMapping(value = "/edit.html")
    public String edit(@RequestParam String mainstadiumid, ModelMap map) {
        Map<String,Object> param = new HashMap<>();
        param.put("mainstadiumid",mainstadiumid);
        try {

            MainStadiumModel mainStadiumModel = mainStadiumService.selectmainStadiumId(param);
            List<Map<String, Object>> mainstadiums = mainStadiumService.findStadiumModel();
            map.put("mainstadiums", mainstadiums);
            map.put("mainStadiumModel", mainStadiumModel);
            return "mainstadium/edit_main_stadium";
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
    @RequestMapping(value = "/updatemainstadium.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(MainStadiumModel mainStadiumModel, ModelMap map) {
        try {
            if (mainStadiumService.updateMainStadium(mainStadiumModel)>0){
                return RtnData.ok("修改场馆成功");
            }
        }catch (Exception e){
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
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delete(@RequestParam String mainStadiumid) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",mainStadiumid);
        try {
            if (mainStadiumService.deleteMainStadium(param)>0){
                return RtnData.ok("删除场馆成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========删除场馆失败=========", e);
        }
        return RtnData.fail("删除场馆失败");
    }

    /**
     * 设为精选
     * @param mainStadiumid
     * @return
     */
    @RequestMapping(value = "/updataSelectFirst.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData updataSelectFirst(@RequestParam String mainStadiumid,@RequestParam String is_special) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",mainStadiumid);
        param.put("is_special",is_special);
        try {
            if (mainStadiumService.updataSelectFirst(param)>0){
                return RtnData.ok("设为精选成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========设为精选失败=========", e);
        }
        return RtnData.fail("设为精选失败");
    }
}

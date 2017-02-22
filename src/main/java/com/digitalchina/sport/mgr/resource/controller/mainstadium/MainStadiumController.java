package com.digitalchina.sport.mgr.resource.controller.mainstadium;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.model.Book;
import com.digitalchina.sport.mgr.resource.model.Category;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }


    /**
     * 进入新增页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String add(ModelMap map) {
        List<Map<String, Object>> mainStadiumModels = mainStadiumService.findStadiumModel();
        map.put("mainStadiumModels", mainStadiumModels);
        return "mainstadium/addInfo";
    }

    /**
     * 新增
     *
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value = "/addmainStadiumModel.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(MainStadiumModel mainStadiumModel, ModelMap map) {
        mainStadiumService.insertmainStadium(mainStadiumModel);
        return RtnData.ok("新增场馆成功");
    }

    /**
     * 进入编辑页面
     *
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value = "/edit.html")
    public String edit(@RequestParam String mainstadiumid, ModelMap map) {
        Long id = Long.parseLong(mainstadiumid);
        MainStadiumModel mainStadiumModel = mainStadiumService.selectmainStadiumId(id);
        List<Map<String, Object>> mainstadiums = mainStadiumService.findStadiumModel();
        map.put("mainstadiums", mainstadiums);
        map.put("mainStadiumModel", mainStadiumModel);
        return "mainstadium/edit";
    }

    /**
     * 更新
     *
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value = "/updatemainstadium.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(MainStadiumModel mainStadiumModel, ModelMap map) {
        mainStadiumService.updateMainStadium(mainStadiumModel);
        return RtnData.ok("修改场馆成功");
    }

    /**
     * 删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public RtnData delete(@RequestParam String mainStadiumid) {
        Long id = Long.parseLong(mainStadiumid);
        mainStadiumService.deleteMainStadium(id);
        return RtnData.ok("删除场馆成功");
    }
}

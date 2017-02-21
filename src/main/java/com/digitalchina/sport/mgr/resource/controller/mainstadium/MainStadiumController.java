package com.digitalchina.sport.mgr.resource.controller.mainstadium;

import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.service.MainStadiumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 进入主馆场
     * @param map
     * @return
     */
    @RequestMapping(value = "/mainstadium.html")
    public String add(@RequestParam(required = false, defaultValue = "10") long pageSize,
                      @RequestParam(required = false, defaultValue = "1") long page,
                      @RequestParam(required = false) String name, ModelMap map,HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        int totalSize = mainStadiumService.findTotalCount(params);
        Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
        params.put("startIndex", pagination.getStartIndex());
        params.put("endIndex", pagination.getEndIndex());
        List<Map<String,Object>> mainStadiumServiceAllStadiumList = mainStadiumService.getAllStadiumList(params);

        pagination.setUrl(request.getRequestURI());
        map.put("page", pagination);
        map.put("name", name);//回到页面,保留搜索关键字
        map.put("mainstadiumlist",mainStadiumServiceAllStadiumList);
        return "mainstadium/main_stadium";
    }
}

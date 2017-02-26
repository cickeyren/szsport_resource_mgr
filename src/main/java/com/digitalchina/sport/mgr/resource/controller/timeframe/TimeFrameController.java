package com.digitalchina.sport.mgr.resource.controller.timeframe;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.dao.TimeFrameMapper;
import com.digitalchina.sport.mgr.resource.model.SubStadium;
import com.digitalchina.sport.mgr.resource.model.TimeFrame;
import com.digitalchina.sport.mgr.resource.service.TimeFrameService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/2/24 15:32
 * @see
 */
@Controller
@RequestMapping(value = "/TimeFrameController")
public class TimeFrameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeFrameController.class);
    @Autowired
    private TimeFrameService timeFrameService;

    /**
     * 进入时段管理
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/timeFrame.html")
    public String getAllTimeFrameList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                      @RequestParam(required = false, defaultValue = "1") long page,
                                      ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String stadium_id = request.getParameter("stadium_id");//子场馆ID
        stadium_id = "11012";

        //取出相关的查询参数
        String time_length_search = request.getParameter("time_length_search");
        String time_start_search = request.getParameter("time_start_search");
        String time_num_search = request.getParameter("time_num_search");

        try {

            int totalSize = timeFrameService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pagination.getEndIndex());
            params.put("stadium_id", stadium_id);
            params.put("time_length", time_length_search);
            params.put("time_start", time_start_search);
            params.put("num", time_num_search);
            List<Map<String, Object>> timeFrameList = timeFrameService.getAllTimeFrameList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));

            map.put("stadium_id", stadium_id);
            map.put("timeFrameList", timeFrameList);
            return "timeframe/timeFrame";

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询時段数据失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 进入新增页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/addTimeFrame.html", method = RequestMethod.GET)
    public String add(HttpServletRequest request, ModelMap map) {

        //子场馆id
        map.put("stadium_id", request.getParameter("stadium_id"));

        return "timeframe/timeFrameAdd";//进入对应的页面
    }

    /**
     * 新增保存方法
     *
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value = "/addTimeFrame.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(TimeFrame timeFrame, ModelMap map) {
        try {
            String maxId = timeFrameService.getMaxId();
            timeFrame.setStatus("1");//状态默认为正常
            timeFrame.setCreatTime(new Date());
            timeFrame.setUpdateTime(new Date());
            timeFrame.setId((maxId == null || maxId.equals("")) ? "1000001" : String.valueOf(Long.parseLong(maxId) + 1));
            if (timeFrameService.inserttimeFrame(timeFrame) > 0) {
                return RtnData.ok("新增時段成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增時段失败=========", e);
        }
        return RtnData.fail("新增時段失败");
    }


    /**
     * 进入编辑页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/editTimeFrame.html", method = RequestMethod.GET)
    public String editTimeFrame(HttpServletRequest request, ModelMap map) {

        //子场馆时段实体
        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setId(request.getParameter("id"));
        timeFrame = timeFrameService.selectTimeFrameById(timeFrame);

        map.put("timeFrame", timeFrame);
        return "timeframe/timeFrameEdit";//进入对应的页面
    }


    /**
     * 修改保存方法
     *
     * @param timeFrame
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateTimeFrame.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(TimeFrame timeFrame, ModelMap map) {
        try {

            TimeFrame timeFrame_full = timeFrameService.selectTimeFrameById(timeFrame);
            timeFrame_full.setUpdateTime(new Date());
            timeFrame_full.setNum(timeFrame.getNum());
            timeFrame_full.setTimeLag(timeFrame.getTimeLag());
            timeFrame_full.setTimeLength(timeFrame.getTimeLength());
            timeFrame_full.setTimeName(timeFrame.getTimeName());
            timeFrame_full.setTimeStart(timeFrame.getTimeStart());

            if (timeFrameService.updatetimeFrame(timeFrame_full) > 0) {
                return RtnData.ok("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========保存失败=========", e);
        }
        return RtnData.fail("保存失败");
    }

    /**
     * 新增保存方法
     *
     * @param timeFrame
     * @param map
     * @return
     */
    @RequestMapping(value = "/invalid.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData invalid(TimeFrame timeFrame, ModelMap map) {
        try {

            TimeFrame timeFrame_full = timeFrameService.selectTimeFrameById(timeFrame);
            timeFrame_full.setStatus("0");
            timeFrame_full.setUpdateTime(new Date());
            if (timeFrameService.updatetimeFrame(timeFrame_full) > 0) {
                return RtnData.ok("作废成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========作废失败=========", e);
        }
        return RtnData.fail("作废失败");
    }


}

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
@RequestMapping(value = "/timeFrameController")
public class TimeFrameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeFrameController.class);
    @Autowired
    private TimeFrameService timeFrameService;


    /**
     * 进入主页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/timeframe.html")
    public String home(ModelMap map) {
        return "timeFrameController/timeframe";
    }

    /**
     * 进入主馆场
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/timeframe.do")
    public String getAllTimeframeList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page,
                                    ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            int totalSize = timeFrameService.findTotalCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pagination.getEndIndex());
            List<TimeFrame> timrFrameList = timeFrameService.getAllTimeFrameList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("page", pagination);
            map.put("timrFrameList", timrFrameList);
            return "timeFrameController/timerameList::dataList";
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
     *
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String add(ModelMap map) {
        List<TimeFrame> timrFrameList = timeFrameService.findtimeFrame();
        map.put("timrFrameList", timrFrameList);
        return "timeFrameController/add_timeframe";//进入对应的页面
    }

    /**
     * 新增
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addtimeFrame.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(TimeFrame timeFrame, ModelMap map) {
        try {
            if (timeFrameService.inserttimeFrame(timeFrame)>0){
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
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/edit.html")
    public String edit(@RequestParam String id, ModelMap map) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        try {

            TimeFrame timeFrame = timeFrameService.selectTimeFrameId(param);
            List<TimeFrame> timrFrameList = timeFrameService.findtimeFrame();
            map.put("timrFrameList", timrFrameList);
            map.put("timeFrame", timeFrame);
            return "timeFrameController/edit_timeFrame";
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
    @RequestMapping(value = "/updatetimeFrame.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(TimeFrame timeFrame, ModelMap map) {
        try {
            if (timeFrameService.updatetimeFrame(timeFrame)>0){
                return RtnData.ok("修改時段成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========修改時段失败=========", e);
        }
        return RtnData.fail("修改時段失败");
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
    public RtnData delete(@RequestParam String id) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        try {
            if (timeFrameService.deletetimeFrame(param)>0){
                RtnData rtnData = new RtnData();
                rtnData.setMessage("删除時段成功");
                rtnData.ok("删除時段成功");
                return rtnData;
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("========删除時段失败=========", e);
        }
        return RtnData.fail("删除時段失败");
    }










}

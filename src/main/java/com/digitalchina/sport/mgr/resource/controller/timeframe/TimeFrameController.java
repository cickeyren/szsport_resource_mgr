package com.digitalchina.sport.mgr.resource.controller.timeframe;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.dao.TimeIntervalMapper;
import com.digitalchina.sport.mgr.resource.model.TimeFrame;
import com.digitalchina.sport.mgr.resource.model.TimeInterval;
import com.digitalchina.sport.mgr.resource.service.TimeFrameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
 * @date 2017/2/24 15:32
 * @see
 */
@Controller
@RequestMapping(value = "/TimeFrameController")
public class TimeFrameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeFrameController.class);
    @Autowired
    private TimeFrameService timeFrameService;
    @Autowired
    private TimeIntervalMapper timeIntervalMapper;


    /**
     * 进入时段管理
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/timeFrame.html")
    public String getAllTimeFrameList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                      @RequestParam(required = false, defaultValue = "1") long page,
                                      ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String stadium_id = request.getParameter("stadium_id");//子场馆ID
        String mainStadiumId = request.getParameter("mainStadiumId");//子场馆ID
//        stadium_id = "11012";
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
            map.put("mainStadiumId", mainStadiumId);
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
     * 查询所以时段规则
     *
     * @param pageSize
     * @param page
     * @param map
     * @param request
     *
     * @return
     */
    @RequestMapping(value = "/selectAlltimeintreal.html")
    public String getAllTimeIntrealList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                        @RequestParam(required = false, defaultValue = "1") long page,
                                        ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String time_code = request.getParameter("id");
        params.put("time_code", time_code);

        try {

            int totalSize = timeFrameService.findTotalCountByInterval(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pagination.getEndIndex());
            params.put("time_code", time_code);
            //分页查询时段规则
            List<Map<String, Object>> timeFrameList = timeFrameService.getAllTimeIntervalList(params);

            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));
            map.put("time_code", time_code);
            map.put("timeFrameList", timeFrameList);
            return "timeframe/timeInterval";

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询時段规则失败=========", e);
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
     *
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
     *
     * @return
     */
    @Transactional//添加事务保证同时保存成功
    @RequestMapping(value = "/addTimeFrame.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(TimeFrame timeFrame, ModelMap map) {
        String stadium_id = timeFrame.getStadiumId();
        Map<String, Object> param = new HashMap<>();
        param.put("stadium_id", stadium_id);
        int numTotal = timeFrameService.getAllTimeFramebyID(param);
        if (numTotal >=1) {
            return RtnData.fail("一个场馆只能有一个时段！");
        } else {
            String time_length = timeFrame.getTimeLength();//每场时间
            String time_start = timeFrame.getTimeStart();//开始时间
            String[] time_start1 = time_start.split(":");
            double timearr;
            if (time_start1[1].equals("00")) {//开始时间是半个小时为时间间隔
                timearr = Double.parseDouble(time_start1[0]);
            } else {
                timearr = Double.parseDouble(time_start1[0]) + 0.5;
            }
            Integer num = timeFrame.getNum();//场次
            String time_lag = timeFrame.getTimeLag();//间隔时间（时间间隔不固定，可能15分钟，可能30分钟。任何情况都有）

            double overTime = 24 - timearr - ((Double.parseDouble(time_length) + (Double.parseDouble(time_lag) / 60)) * num);
            if (overTime > 0) {
                try {
                    String maxId = timeFrameService.getMaxId();
                    timeFrame.setStatus("1");//状态默认为正常
                    timeFrame.setCreatTime(new Date());
                    timeFrame.setUpdateTime(new Date());
                    timeFrame.setId((maxId == null || maxId.equals("")) ? "1000001" : String.valueOf(Long.parseLong(maxId) + 1));

                    TimeInterval timeInterval = new TimeInterval();
                    int time_end = getTime_guize(time_start);//获取了总分钟  开始种分钟（开始总分钟）
                    int time_lags = Integer.parseInt(time_lag);//间隔时间
                    int time_lengths = getTime_length(time_length); //获取每场时间总分钟
                    int time_StartA = 0;
                    for (int i = 0; i < num; i++) {
                        //与时段进行关联
                        timeInterval.setId(UUID.randomUUID().toString());
                        timeInterval.setTime_code((maxId == null || maxId.equals("")) ? "1000001" : String.valueOf(Long.parseLong(maxId) + 1));
                        timeInterval.setSubstadium_id(timeFrame.getStadiumId());//子场馆id
                        timeInterval.setTime_sort(i + 1);
                        if (time_lags == 0) {  //当无间隔时间时候
                            time_StartA = time_end;  //1.开始时间等于开始  循环之后开始时间等于结束时间
                        } else {
                            if (i == 0) {
                                time_StartA = time_end;
                            } else {
                                time_StartA = time_end + time_lags;
                            }
                        }
                        time_end = time_StartA + time_lengths;  //结束时间等于开始时间+每场时间
                        timeInterval.setTime_inter(getTimeByint(time_StartA) + "-" + getTimeByint(time_end));
                        timeInterval.setCreat_time(new Date());
                        timeFrameService.insertTimeInterval(timeInterval);
                    }

                    if (timeFrameService.inserttimeFrame(timeFrame) > 0) {
                        return RtnData.ok("新增時段成功");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error("========新增時段失败=========", e);
                }
            } else {
                return RtnData.fail("场次超过当日时间，请修改场次重新添加!");
            }
        }
        return RtnData.fail("新增時段失败");
    }


    /**
     * 进入编辑页面
     *
     * @param map
     * @param request
     *
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
     *
     * @return
     */
    @RequestMapping(value = "/updateTimeFrame.do", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public RtnData update(TimeFrame timeFrame, ModelMap map) {
        Map<String, Object> params = new HashMap<>();
        params.put("time_code", timeFrame.getId());

        int numtotal = timeFrameService.selectticketById(params);
        if (numtotal >= 1) {
            return RtnData.fail("该时段正在被场地票使用，无法编辑！");
        } else {
            try {
                TimeFrame timeFrame_full = timeFrameService.selectTimeFrameById(timeFrame);
                timeFrame_full.setUpdateTime(new Date());
                timeFrame_full.setNum(timeFrame.getNum());
                timeFrame_full.setTimeLag(timeFrame.getTimeLag());
                timeFrame_full.setTimeLength(timeFrame.getTimeLength());
                timeFrame_full.setTimeName(timeFrame.getTimeName());
                timeFrame_full.setTimeStart(timeFrame.getTimeStart());

                if (timeFrameService.updatetimeFrame(timeFrame_full) > 0) {
                    TimeInterval timeInterval = new TimeInterval();
                    //直接删除数据
                    timeInterval.setTime_code(timeFrame.getId());
                    timeIntervalMapper.delete(timeInterval);

                    String time_length = timeFrame.getTimeLength();//每场时间
                    String time_start = timeFrame.getTimeStart();//开始时间
                    String[] time_start1 = time_start.split(":");
                    double timearr;
                    if (time_start1[1].equals("00")) {//开始时间是半个小时为时间间隔
                        timearr = Double.parseDouble(time_start1[0]);
                    } else {
                        timearr = Double.parseDouble(time_start1[0]) + 0.5;
                    }
                    Integer num = timeFrame.getNum();//场次
                    String time_lag = timeFrame.getTimeLag();//间隔时间（时间间隔不固定，可能15分钟，可能30分钟。任何情况都有）

                    double overTime = 24 - timearr - ((Double.parseDouble(time_length) + (Double.parseDouble(time_lag) / 60)) * num);

                    if (overTime > 0) {
                        //重新添加数据
                        int time_end = getTime_guize(timeFrame.getTimeStart());//获取了总分钟  开始种分钟（开始总分钟）
                        int time_lags = Integer.parseInt(timeFrame.getTimeLag());//间隔时间
                        int time_lengths = getTime_length(timeFrame.getTimeLength()); //获取每场时间总分钟
                        int time_StartA = 0;
                        for (int i = 0; i < timeFrame.getNum(); i++) {
                            //与时段进行关联
                            timeInterval.setId(UUID.randomUUID().toString());
                            timeInterval.setTime_code(timeFrame.getId());
                            timeInterval.setSubstadium_id(timeFrame_full.getStadiumId());//子场馆id
                            timeInterval.setTime_sort(i + 1);
                            if (time_lags == 0) {  //当无间隔时间时候
                                time_StartA = time_end;  //1.开始时间等于开始  循环之后开始时间等于结束时间
                            } else {
                                if (i == 0) {
                                    time_StartA = time_end;
                                } else {
                                    time_StartA = time_end + time_lags;
                                }
                            }
                            time_end = time_StartA + time_lengths;  //结束时间等于开始时间+每场时间
                            timeInterval.setTime_inter(getTimeByint(time_StartA) + "-" + getTimeByint(time_end));
                            timeInterval.setUpdate_time(new Date());
                            timeFrameService.insertTimeInterval(timeInterval);
                        }

                    } else {
                        return RtnData.fail("场次超过当日时间，请修改场次重新添加!");
                    }
                    return RtnData.ok("保存成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("========保存失败=========", e);
            }
        }
        return RtnData.fail("保存失败");
    }

    /**
     * 新增保存方法
     *
     * @param timeFrame
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/invalid.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData invalid(TimeFrame timeFrame, ModelMap map) {
        Map<String, Object> params = new HashMap<>();
        params.put("time_code", timeFrame.getId());

        int numtotal = timeFrameService.selectticketById(params);
        if (numtotal >= 1) {
            return RtnData.fail("该时段正在被场地票使用，无法作废！");
        } else {
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
        }
        return RtnData.fail("作废失败");
    }


    /**
     * 7:00转换成分钟
     *
     * @param time
     *
     * @return
     */

    public Integer getTime_guize(String time) {
        Integer timeALL = 0;
        String[] timeARR = time.split(":");
        if (timeARR[1].equals("00")) {
            timeALL = Integer.parseInt(timeARR[0]) * 60;//获取总分钟
        } else {
            timeALL = (Integer.parseInt(timeARR[0]) * 60) + 30;//额外加30分钟
        }
        return timeALL;
    }

    /**
     * 转换成分钟(1.5)
     */
    public Integer getTime_length(String time) {
        Integer timeAll = 0;
        if (time.indexOf(".") >= 0) {
            Double times = Double.parseDouble(time) * 60;
            timeAll = times.intValue();
        } else {
            timeAll = Integer.parseInt(time) * 60;
        }
        return timeAll;
    }

    /**
     * 将分钟转换成9:00这样的格式
     */
    public String getTimeByint(Integer time) {
        Integer timeHore = time / 60;
        Integer timeScend = time % 60;
        String timeSeendA = "";
        if (timeScend < 10) {
            timeSeendA = "0" + timeScend;
        } else {
            timeSeendA = timeScend.toString();
        }
        String timeString = "";
        if (timeScend == 0) {
            timeString = timeHore.toString() + ":00";
        } else {
            timeString = timeHore.toString() + ":" + timeSeendA;
        }

        return timeString;
    }


}



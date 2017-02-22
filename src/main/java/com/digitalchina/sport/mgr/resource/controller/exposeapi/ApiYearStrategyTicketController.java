package com.digitalchina.sport.mgr.resource.controller.exposeapi;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.YearStrategyDao;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketModel;
import com.digitalchina.sport.mgr.resource.service.YearStrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面跳转测试
 * Created by xingding on 2016/8/17.
 */
@Controller
@RequestMapping(value = "/yearstrategyticket/api")
public class ApiYearStrategyTicketController {

    public static final Logger logger = LoggerFactory.getLogger(ApiYearStrategyTicketController.class);

    @Autowired
    private PropertyConfig config;
    @Autowired
    private YearStrategyService yearStrategyService;
    @Autowired
    private YearStrategyDao yearStrategyDao;

    /**
     * 根据根据票策略ID获取票策略详情及子场馆列表及相关验票规则
     * @param yearStrategyId
     * @return
     */
    @RequestMapping(value = "/getYearStrategyTicketModelInfo.json")
    @ResponseBody
    public RtnData getYearStrategyTicketModelInfo(@RequestParam(required = true) String yearStrategyId) {
        try {
            Map<String,Object> resultMap = new HashMap<String,Object>();
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("id",yearStrategyId);
            paramMap.put("strategyState","1");
            YearStrategyTicketModel ticketModel = yearStrategyDao.getYearStrategyTicketModelById(paramMap);
            resultMap.put("yearStrategyDetail",ticketModel);
            if(null !=ticketModel) {
            resultMap.put("studStadiumList",yearStrategyDao.getYearStrategyStadiumRelationsModelByYearStrategyId(yearStrategyId));
            resultMap.put("checkShieldTimeList",yearStrategyDao.getTicketStrategyCommonCheckShieldTimeModelList(yearStrategyId));
            resultMap.put("checkUseableTimeList",yearStrategyDao.getYearStrategyTicketCheckUseableTimeModelList(yearStrategyId));
            }
            return RtnData.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========根据票策略ID获取票策略详情及子场馆列表失败=========",e);
        }
        return RtnData.fail("根据票策略ID获取票策略详情及子场馆列表失败");
    }

    /**
     * 根据体育馆主主场馆ID，及分类获取年卡列表
     * @param pageIndex
     * @param pageSize
     * @param classify
     * @param mainStadiumId
     * @return
     */
    @RequestMapping(value = "/getYearStrategyTicketModelInfoList.json")
    @ResponseBody
    public RtnData getYearStrategyTicketModelInfoList(@RequestParam(required = false) String pageIndex,
                                                      @RequestParam(required =  false)  String pageSize,
                                                      @RequestParam(required =  false)  String classify,
                                                      @RequestParam(required =  true)  String mainStadiumId) {
        try {
            Map<String,Object> paramMap = new HashMap<String,Object>();
            if (StringUtils.isEmpty(pageIndex)) {
                pageIndex = "0";
            }

            if(StringUtils.isEmpty(pageSize)) {
                pageSize = config.pageSize;
            }
            paramMap.put("pageIndex",Integer.valueOf(pageIndex) * Integer.valueOf(pageSize));
            paramMap.put("pageSize",Integer.valueOf(pageSize));
            paramMap.put("mainStadiumId",mainStadiumId);
            paramMap.put("classify",classify);
            paramMap.put("strategyState","1");
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("yearStrategyList",yearStrategyDao.getYearStrategyTicketModelInfoList(paramMap));
            return RtnData.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========根分页参数获取年票策略列表失败=========",e);
        }
        return RtnData.fail("根分页参数获取年票策略列表失败");
    }
}

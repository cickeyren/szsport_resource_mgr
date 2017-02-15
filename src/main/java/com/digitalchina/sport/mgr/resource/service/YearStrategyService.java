package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.data.Constants;
import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.utils.DateUtil;
import com.digitalchina.common.utils.HttpClientUtil;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.config.PropertyConfig;
import com.digitalchina.sport.mgr.resource.dao.YearStrategyDao;
import com.digitalchina.sport.mgr.resource.model.*;
import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 * Created by yangyt
 */
@Service
public class YearStrategyService {
    @Autowired
    private PropertyConfig proConfig;
    @Autowired
    private YearStrategyDao yearStrategyDao;
    private static Logger logger = Logger.getLogger(YearStrategyService.class);

    /**
     * 添加年卡策略票务
     * @param yearStrategyTicketModel
     * @return
     */
    public int insertYearStrategyTicket(YearStrategyTicketModel yearStrategyTicketModel) throws Exception{
        return yearStrategyDao.insertYearStrategyTicket(yearStrategyTicketModel);
    }


    /**
     * 批量添加年票生成策略与子场馆对应关系
     * @param list
     * @return
     */
    public int insertYearStrategyStadiumRelationsList(List<YearStrategyStadiumRelationsModel> list) throws Exception{
        return yearStrategyDao.insertYearStrategyStadiumRelationsList(list);
    }

    /**
     * 批量添加年票策略可用时间段
     * @param list
     * @return
     */
    public int insertYearStrategyTicketCheckUseableTimeList(List<YearStrategyTicketCheckUseableTimeModel> list) throws Exception{
        return  yearStrategyDao.insertYearStrategyTicketCheckUseableTimeList(list);
    }

    public int insertTicketStrategyCommonCheckShieldTimeModel(List<TicketStrategyCommonCheckShieldTimeModel> list) throws Exception{
        return  yearStrategyDao.insertTicketStrategyCommonCheckShieldTimeModel(list);
    }

    /**
     * 添加年卡相关信息
     * @param yearStrategyTicketModel
     * @param subStadiumID
     * @param checkUseableTime
     * @param shieldDate
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean doInserYearStrategyTicketAndRelateInfo(YearStrategyTicketModel yearStrategyTicketModel,HttpServletRequest request) throws Exception {
        //1、添加年卡策略票务
        String strategyId = UUIDUtil.generateUUID();
        yearStrategyTicketModel.setId(strategyId);

        String checkUseableTime = request.getParameter("checkUseableTime");//可用时间段
        String checkLimitedDateType =  yearStrategyTicketModel.getCheckLimitedDateType();
        if(!StringUtils.isNullOrEmpty(checkUseableTime)) {
          List<YearStrategyTicketCheckUseableTimeModel> useableTimeModelList = new ArrayList<YearStrategyTicketCheckUseableTimeModel>() ;
         String []tempArray = checkUseableTime.split(","); //19:00$20:00,19:30$21:00
            // 添加年票策略可用时间段
            for (String time:tempArray) {
                String[]tempStartAndEndArr = time.split("\\$");
                YearStrategyTicketCheckUseableTimeModel tempTimeModel = new YearStrategyTicketCheckUseableTimeModel();
                tempTimeModel.setId(UUIDUtil.generateUUID());
                tempTimeModel.setTicketStrategyId(strategyId);
                tempTimeModel.setCheckLimitedDateType(checkLimitedDateType);
                tempTimeModel.setUseableStartTime(tempStartAndEndArr[0]);
                tempTimeModel.setUseableEndTime(tempStartAndEndArr[1]);
                useableTimeModelList.add(tempTimeModel);
            }
            // 添加年票策略可用时间段
            insertYearStrategyTicketCheckUseableTimeList(useableTimeModelList);
        }

        String orderEffectiveType = request.getParameter("orderEffectiveType");//使用有效期类型 0：预订后x天有效 1：固定有效期
        String fixDay = request.getParameter("fixDay");//不可用日期
        if("0".equals(orderEffectiveType)) {
            String orderEffectiveStartTime = DateUtil.formatDate(new Date(System.currentTimeMillis()));
            String orderEffectiveEndTime = DateUtil.formatDate(DateUtil.offsiteDate(new Date(System.currentTimeMillis()), Calendar.DAY_OF_MONTH,Integer.valueOf(fixDay)));
            yearStrategyTicketModel.setOrderEffectiveStartTime(orderEffectiveStartTime);
            yearStrategyTicketModel.setOrderEffectiveEndTime(orderEffectiveEndTime);
        }

        String shieldDate = request.getParameter("shieldDate");//不可用日期 2017-02-13$2017-02-14,2017-02-08$2017-02-14

        if(!StringUtils.isNullOrEmpty(shieldDate)) {
            List<TicketStrategyCommonCheckShieldTimeModel > shieldTimeModelList = new ArrayList<TicketStrategyCommonCheckShieldTimeModel>() ;
            String []tempArray = shieldDate.split(","); //2017-02-13$2017-02-14,2017-02-08$2017-02-14
            // 添加年票策略可用时间段
            for (String time:tempArray) {
                String[]tempStartAndEndArr = time.split("\\$");
                TicketStrategyCommonCheckShieldTimeModel tempShieldTimeModel = new TicketStrategyCommonCheckShieldTimeModel();
                tempShieldTimeModel.setId(UUIDUtil.generateUUID());
                tempShieldTimeModel.setTicketType("2");
                tempShieldTimeModel.setTicketStrategyId(strategyId);
                tempShieldTimeModel.setShieldStartTime(tempStartAndEndArr[0]);
                tempShieldTimeModel.setShieldEndTime(tempStartAndEndArr[1]);
                shieldTimeModelList.add(tempShieldTimeModel);
            }
            // 添加年票策略不可用日期
            insertTicketStrategyCommonCheckShieldTimeModel(shieldTimeModelList);
        }

        insertYearStrategyTicket(yearStrategyTicketModel);
        //2、添加策略与子场馆对应关系
        List<YearStrategyStadiumRelationsModel> yearStdaiumList = new ArrayList<YearStrategyStadiumRelationsModel>();
        String mainStadiumID = request.getParameter("mainStadiumID");//主场馆列表
        String subStadiumID = request.getParameter("subStadiumID");//主场馆列表
        String classify = request.getParameter("classify");//主场馆列表
        //添加年票与子场馆关联
        YearStrategyStadiumRelationsModel stadiumModel = new YearStrategyStadiumRelationsModel();
        stadiumModel.setId(UUIDUtil.generateUUID());
        stadiumModel.setClassify(classify);
        stadiumModel.setMainStadiumId(mainStadiumID);
        stadiumModel.setSubStadiumId(subStadiumID);
        stadiumModel.setTicketStrategyId(yearStrategyTicketModel.getId());
        yearStdaiumList.add(stadiumModel);
        insertYearStrategyStadiumRelationsList(yearStdaiumList);
        //3、添加年票策略可用时间段
;        return true;
    }

    /**
     * 获取所有主场馆
     * @return
     */
    public List<Map<String,Object>> getAllMainStadium() {
//        List<Map<String,Object>> resultList = null;
//        String result = null;
//        try {
//            result = HttpClientUtil.doGet(proConfig.SPORT_RESOURCE_URL + "/api/stadium/getAllMainStadium.json", 2000, null, null);
//            Gson gson = new Gson();
//            Map<String,Object> gsonMap =  gson.fromJson(result,Map.class);
//            if(null != gsonMap && gsonMap.containsKey("code")) {
//                if(Constants.RTN_CODE_SUCCESS.equals((String)gsonMap.get("code"))) {
//                    Map<String,Object> resultMap = (Map<String,Object>)gsonMap.get("result");
//                    resultList = (List<Map<String, Object>>) resultMap.get("getAllMerchantList");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("=========调用远程接口时发生错误===========",e);
//        }
//        return resultList != null? resultList:new ArrayList<Map<String,Object>>();
        return HttpClientUtil.getListResultByURLAndKey(proConfig.SPORT_RESOURCE_URL + "/api/stadium/getAllMainStadium.json","获取所有主场馆列表","getAllMainStadiumList");

    }

    /**
     * 根据主场场馆获取子场馆list
     * @param mainStadiumId
     * @return
     */
    public List<Map<String,Object>> getSubStadiumListByMainId(String mainStadiumId) {
        return HttpClientUtil.getListResultByURLAndKey(proConfig.SPORT_RESOURCE_URL + "/api/stadium/getSubStadiumByMainId.json?mainStadiumId="+mainStadiumId,"根据主场馆ID获取子场馆列表","subStadiumList");
    }

    /**
     * 调用接口获取所有商户的信息
     * @return
     */
    public List<Map<String,Object>> getAllMerchant() {
        return HttpClientUtil.getListResultByURLAndKey(proConfig.SPORT_RESOURCE_URL + "/api/stadium/getAllMerchant.json","获取所有接口列表","getAllMerchantList");
    }
}

package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.dao.SiteTicketDao;
import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import com.digitalchina.sport.mgr.resource.model.SiteTicketStrategyInfoModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyStadiumRelationsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:场地票管理
 * @Date:Created in 2017/2/23.
 */
@Service
public class SiteTicketService {
    @Autowired
    private SiteTicketDao siteTicketDao;
    @Autowired
    private YearStrategyService yearStrategyService;

    /**
     * 添加场地票基本信息
     * @param siteTicketBasicInfoModel
     * @return
     */
    @Transactional
    public boolean addSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel, HttpServletRequest request) throws Exception{
        //添加场地票基本信息
        String id = UUIDUtil.generateUUID();
        siteTicketBasicInfoModel.setId(id);
        siteTicketDao.addSiteTicketBasicInfo(siteTicketBasicInfoModel);
        //添加场地票与场馆关联
        List<YearStrategyStadiumRelationsModel> yearStdaiumList = new ArrayList<YearStrategyStadiumRelationsModel>();
        String mainStadium = request.getParameter("mainStadium");//主场馆列表
        String subStadium = request.getParameter("subStadium");//子场馆列表
        String classify = request.getParameter("classify");//子场馆分类
        YearStrategyStadiumRelationsModel stadiumModel = new YearStrategyStadiumRelationsModel();
        stadiumModel.setId(UUIDUtil.generateUUID());
        stadiumModel.setClassify(classify);
        stadiumModel.setMainStadiumId(mainStadium);
        stadiumModel.setSubStadiumId(subStadium);
        stadiumModel.setTicketStrategyId(siteTicketBasicInfoModel.getId());
        yearStdaiumList.add(stadiumModel);
        yearStrategyService.insertYearStrategyStadiumRelationsList(yearStdaiumList);
        return true;
    }

    /**
     * 根据分页状态、分类，子场馆等信息参数获取场地票信息总数(后台用)
     * @param map
     * @return
     */
    public int getSiteTicketTotalCount(Map<String, Object> map){
        return siteTicketDao.getSiteTicketTotalCount(map);
    }

    /**
     * 根据分页状态、分类，子场馆等信息参数获取场地票信息列表(后台用)
     * @param map
     * @return
     */
    public List<Map<String, Object>> getSiteTicketInfoList4Mgr(Map<String, Object> map){
        return siteTicketDao.getSiteTicketInfoList4Mgr(map);
    }

    /**
     * 修改场地票基本信息
     * @param siteTicketBasicInfoModel
     * @return
     */
    public int updateSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel){
        return siteTicketDao.updateSiteTicketBasicInfo(siteTicketBasicInfoModel);
    }

    /**
     * 查询场地票基本信息
     * @param map
     * @return
     */
    public SiteTicketBasicInfoModel getSiteTicketInfoByParam(Map<String, Object> map){
        return siteTicketDao.getSiteTicketInfoByParam(map);
    }

    /**
     * 编辑场地票基本信息
     * @param siteTicketBasicInfoModel
     * @return
     */
    @Transactional
    public boolean editSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel, HttpServletRequest request) throws Exception{
        //编辑场地票基本信息
        siteTicketDao.updateSiteTicketBasicInfo(siteTicketBasicInfoModel);
        //编辑场地票与场馆关联
        String mainStadium = request.getParameter("mainStadium");//主场馆列表
        String subStadium = request.getParameter("subStadium");//子场馆列表
        String classify = request.getParameter("classify");//子场馆分类
        YearStrategyStadiumRelationsModel stadiumModel = new YearStrategyStadiumRelationsModel();
        stadiumModel.setClassify(classify);
        stadiumModel.setMainStadiumId(mainStadium);
        stadiumModel.setSubStadiumId(subStadium);
        stadiumModel.setTicketStrategyId(siteTicketBasicInfoModel.getId());
        siteTicketDao.updateTicketStadiumRelation(stadiumModel);
        return true;
    }

    /**
     * 新增场地票价格策略
     * @param siteTicketStrategyInfoModel
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addStrategyInfo(SiteTicketStrategyInfoModel siteTicketStrategyInfoModel, HttpServletRequest request) throws Exception{
        //添加场地票策略信息
        String id = UUIDUtil.generateUUID();
        siteTicketStrategyInfoModel.setId(id);
        siteTicketDao.addSiteTicketStrategyInfo(siteTicketStrategyInfoModel);
        //添加场地票策略与指定日之间的关联
        /**/
        return true;
    }

    /**
     * 获取场地票价格策略列表数量
     * @param map
     * @return
     */
    public int getSiteTicketStrategyCount(Map<String, Object> map) throws Exception{
        return siteTicketDao.getSiteTicketStrategyCount(map);
    }

    /**
     * 获取场地票价格策略列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getSiteTicketStrategyInfoList(Map<String, Object> map) throws Exception{
        List<SiteTicketStrategyInfoModel> strategyInfoList = siteTicketDao.getSiteTicketStrategyInfoList(map);
        List<Map<String, Object>> strategyList = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < strategyInfoList.size(); i++){
            Map<String, Object> itemMap = new HashMap<String, Object>();
            itemMap.put("id", strategyInfoList.get(i).getId());
            //策略名称
            itemMap.put("strategyName", strategyInfoList.get(i).getStrategyName());
            //单价
            itemMap.put("sellPrice", strategyInfoList.get(i).getSellPrice());
            //日期类型
            String dateType = strategyInfoList.get(i).getDateType();
            String dateTypeValue = "";
            if ("1".equals(dateType)){
                dateTypeValue += "每周(";
                String weekDetails = strategyInfoList.get(i).getWeekDetails();
                if(weekDetails.indexOf("1") >= 0){
                    dateTypeValue += "周一、";
                }
                if(weekDetails.indexOf("2") >= 0){
                    dateTypeValue += "周二、";
                }
                if(weekDetails.indexOf("3") >= 0){
                    dateTypeValue += "周三、";
                }
                if(weekDetails.indexOf("4") >= 0){
                    dateTypeValue += "周四、";
                }
                if(weekDetails.indexOf("5") >= 0){
                    dateTypeValue += "周五、";
                }
                if(weekDetails.indexOf("6") >= 0){
                    dateTypeValue += "周六、";
                }
                if(weekDetails.indexOf("7") >= 0){
                    dateTypeValue += "周日、";
                }
                dateTypeValue = dateTypeValue.substring(0, dateTypeValue.length() - 1) + ")";
            } else if("3".equals(dateType)){
                dateTypeValue += "指定日(";
                String specificDate = strategyInfoList.get(i).getSpecificDate();
                dateTypeValue += specificDate + ")";
            }
            itemMap.put("dateTypeValue", dateTypeValue);
            //时段信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String[] timeInterval = strategyInfoList.get(i).getTimeInterval().split(",");
            String intervalStr = "";
            paramMap.put("timeInterval", timeInterval);
            List<Map<String, Object>> timeIntervalList = siteTicketDao.getTimeIntervalList(paramMap);
            for (int j = 0; j < timeIntervalList.size(); j++){
                intervalStr += timeIntervalList.get(j).get("timeInter") + "、";
            }
            itemMap.put("timeInterval", intervalStr.substring(0, intervalStr.length() - 1));
            //场地信息
            String[] site = strategyInfoList.get(i).getSite().split(",");
            String fields = "";
            paramMap.put("site", site);
            List<Map<String, Object>> filedList = siteTicketDao.getFieldList(paramMap);
            for (int j = 0; j < filedList.size(); j++){
                fields += filedList.get(j).get("fieldName") + "、";
            }
            itemMap.put("fields", fields.substring(0, fields.length() - 1));
            strategyList.add(itemMap);
        }
        return strategyList;
    }

    /**
     * 删除场地票价格策略
     * @param map
     * @return
     * @throws Exception
     */
    public int delStrategyInfo(Map<String, Object> map) throws Exception{
        return siteTicketDao.delStrategyInfo(map);
    }

    /**
     * 获取场地票价格策略
     * @param map
     * @return
     * @throws Exception
     */
    public SiteTicketStrategyInfoModel getStrategyInfoByParam(Map<String, Object> map) throws Exception{
        return siteTicketDao.getStrategyInfoByParam(map);
    }

    /**
     * 编辑场地票价格策略
     * @param siteTicketStrategyInfoModel
     * @return
     * @throws Exception
     */
    public int updateStrategyInfo(SiteTicketStrategyInfoModel siteTicketStrategyInfoModel) throws Exception{
        return siteTicketDao.updateStrategyInfo(siteTicketStrategyInfoModel);
    }
}

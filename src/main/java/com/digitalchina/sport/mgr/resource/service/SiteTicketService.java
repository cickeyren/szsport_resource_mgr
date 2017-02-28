package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.dao.SiteTicketDao;
import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyStadiumRelationsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
}

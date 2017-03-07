package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.SiteTicketBasicInfoModel;
import com.digitalchina.sport.mgr.resource.model.SiteTicketStrategyInfoModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyStadiumRelationsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:wangw
 * @Description:场地票管理
 * @Date:Created in 2017/2/23.
 */
@Mapper
public interface SiteTicketDao {
    /**
     * 添加场地票基本信息
     * @param siteTicketBasicInfoModel
     * @return
     */
    public int addSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel);

    /**
     * 根据分页状态、分类，子场馆等信息参数获取场地票信息总数(后台用)
     * @param map
     * @return
     */
    public int getSiteTicketTotalCount(Map<String, Object> map);

    /**
     * 根据分页状态、分类，子场馆等信息参数获取场地票信息列表(后台用)
     * @param map
     * @return
     */
    public List<Map<String, Object>> getSiteTicketInfoList4Mgr(Map<String, Object> map);

    /**
     * 修改场地票基本信息
     * @param siteTicketBasicInfoModel
     * @return
     */
    public int updateSiteTicketBasicInfo(SiteTicketBasicInfoModel siteTicketBasicInfoModel);

    /**
     * 编辑场地票与场馆关联
     * @param yearStrategyStadiumRelationsModel
     * @return
     */
    public int updateTicketStadiumRelation(YearStrategyStadiumRelationsModel yearStrategyStadiumRelationsModel);

    /**
     * 查询场地票基本信息
     * @param map
     * @return
     */
    public SiteTicketBasicInfoModel getSiteTicketInfoByParam(Map<String, Object> map);

    /**
     * 新增场地票策略信息
     * @param siteTicketStrategyInfoModel
     * @return
     */
    public int addSiteTicketStrategyInfo(SiteTicketStrategyInfoModel siteTicketStrategyInfoModel);
}

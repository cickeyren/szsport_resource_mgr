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

    /**
     * 获取场地票价格策略列表数量
     * @param map
     * @return
     */
    public int getSiteTicketStrategyCount(Map<String, Object> map);

    /**
     * 获取场地票价格策略列表
     * @param map
     * @return
     */
    public List<SiteTicketStrategyInfoModel> getSiteTicketStrategyInfoList(Map<String, Object> map);

    /**
     * 获取场地票价格策略列表(排除当前策略,用于编辑价格策略时)
     * @param map
     * @return
     */
    public List<SiteTicketStrategyInfoModel> getSiteTicketStrategyInfoListOutSelf(Map<String, Object> map);

    /**
     * 获取场地列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getFieldList(Map<String, Object> map);

    /**
     * 获取时段列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getTimeIntervalList(Map<String, Object> map);

    /**
     * 删除场地票价格策略
     * @param map
     * @return
     */
    public int delStrategyInfo(Map<String, Object> map);

    /**
     * 获取场地票价格策略
     * @param map
     * @return
     */
    public SiteTicketStrategyInfoModel getStrategyInfoByParam(Map<String, Object> map);

    /**
     * 编辑场地票价格策略
     * @param siteTicketStrategyInfoModel
     * @return
     */
    public int updateStrategyInfo(SiteTicketStrategyInfoModel siteTicketStrategyInfoModel);

    /**
     * 获取下单需要的场地票信息
     * @param map
     * @return
     */
    public Map<String, Object> getSiteTicketInfoToOrder(Map<String, Object> map);

    /**
     * 根据场馆获取生效的场地票列表信息
     * @param map
     * @return
     */
    public List<Map<String, Object>> getValidSiteTicketList(Map<String, Object> map);
}

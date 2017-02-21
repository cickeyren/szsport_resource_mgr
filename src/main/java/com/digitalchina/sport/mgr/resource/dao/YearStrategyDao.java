package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.TicketStrategyCommonCheckShieldTimeModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyStadiumRelationsModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketCheckUseableTimeModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:yangyt
 * @Description
 * @Date:Created in 13:48 2017/2/10
 */
@Mapper
public interface YearStrategyDao {
    /**
     * 添加年卡策略票务
     * @param yearStrategyTicketModel
     * @return
     */
    public int insertYearStrategyTicket(YearStrategyTicketModel yearStrategyTicketModel) throws Exception;
    /*
    * 更新年卡策略
    * */
    public int updateYearStrategyTicket(YearStrategyTicketModel yearStrategyTicketModel) throws Exception;



    /**
     * 批量添加年票生成策略与子场馆对应关系
     * @param list
     * @return
     */
    public int insertYearStrategyStadiumRelationsList(List<YearStrategyStadiumRelationsModel> list) throws Exception;

    /**
     * 批量添加年票策略可用时间段
     * @param list
     * @return
     */
    public int insertYearStrategyTicketCheckUseableTimeList(List<YearStrategyTicketCheckUseableTimeModel> list) throws Exception;
    /**
     * 批量添加票务策略通用验票屏蔽时间
     * @param list
     * @return
     */
    public int insertTicketStrategyCommonCheckShieldTimeModel(List<TicketStrategyCommonCheckShieldTimeModel> list) throws Exception;

    /**
     * 根据年票策略ID查询年票策略
     * @param id
     * @return
     * @throws Exception
     */
    public YearStrategyTicketModel getYearStrategyTicketModelById(String id) throws  Exception;

    /**
     * 根据年票策略ID去获取年票中关联的子场馆信息
     * @param id
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getYearStrategyStadiumRelationsModelByYearStrategyId(String id) throws  Exception;

    /**
     * 根据分页参数查询策略列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getYearStrategyTicketModelInfoList(Map<String,Object> map) throws  Exception;
    /*
    *根据策略ID获取屏蔽时间列表
     */
    public List<TicketStrategyCommonCheckShieldTimeModel> getTicketStrategyCommonCheckShieldTimeModelList(String yearStrategyId) throws  Exception;

    /**
     * 根据策略ID获取可用时间列表
     * @param yearStrategyId
     * @return
     * @throws Exception
     */

    public List<YearStrategyTicketCheckUseableTimeModel> getYearStrategyTicketCheckUseableTimeModelList(String yearStrategyId) throws  Exception;

    /**
     * 根据策略ID删除子场馆对应关系
     * @param yearStrategyId
     * @return
     * @throws Exception
     */
    public int deleteYearStrategyStadiumRelationsListByYearStrategyId(String yearStrategyId) throws Exception;
    /**
     * 根据策略ID删除屏蔽时间列表
     * @param yearStrategyId
     * @return
     */
    public int deleteTicketStrategyCommonCheckShieldTimeModelByYearStrategyId(String yearStrategyId) throws Exception;

    /**
     * 根据策略ID删除可用时间列表
     * @param yearStrategyId
     * @return
     * @throws Exception
     */
    public int deleteYearStrategyTicketCheckUseableTimeListByYearStrategyId(String yearStrategyId) throws Exception;
}

package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.TicketStrategyCommonCheckShieldTimeModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyStadiumRelationsModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketCheckUseableTimeModel;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

}

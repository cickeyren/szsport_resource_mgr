package com.digitalchina.sport.mgr.resource.dao;

import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketModel;
import org.apache.ibatis.annotations.Mapper;

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
    int insertYearStrategyTicket(YearStrategyTicketModel yearStrategyTicketModel);
}

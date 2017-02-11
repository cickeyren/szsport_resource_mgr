package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.sport.mgr.resource.dao.BookDao;
import com.digitalchina.sport.mgr.resource.dao.YearStrategyDao;
import com.digitalchina.sport.mgr.resource.model.Book;
import com.digitalchina.sport.mgr.resource.model.Category;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by yangyt
 */
@Service
public class YearStrategyService {
    @Autowired
    private YearStrategyDao yearStrategyDao;

    /**
     * 添加年卡策略票务
     * @param yearStrategyTicketModel
     * @return
     */
    public int insertYearStrategyTicket(YearStrategyTicketModel yearStrategyTicketModel){
        return yearStrategyDao.insertYearStrategyTicket(yearStrategyTicketModel);
    }
}

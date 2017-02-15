package com.digitalchina.sport.mgr.resource.controller.yearstrategyticket;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.dao.YearStrategyDao;
import com.digitalchina.sport.mgr.resource.model.Book;
import com.digitalchina.sport.mgr.resource.model.Category;
import com.digitalchina.sport.mgr.resource.model.YearStrategyTicketModel;
import com.digitalchina.sport.mgr.resource.service.BookService;
import com.digitalchina.sport.mgr.resource.service.YearStrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转测试
 * Created by xingding on 2016/8/17.
 */
@Controller
@RequestMapping(value = "/yearstrategyticket")
public class YearStrategyTicketController {

    public static final Logger logger = LoggerFactory.getLogger(YearStrategyTicketController.class);

    @Autowired
    private YearStrategyService yearStrategyService;
    @Autowired
    private YearStrategyDao yearStrategyDao;


    /**
     * 进入新增页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String add(ModelMap map) {
//        List<Category> categorys = bookService.findCategorys();
        map.put("mainStadiumList", yearStrategyService.getAllMainStadium());
        map.put("merchantList", yearStrategyService.getAllMerchant());
        return "yearstrategyticket/add_year_strategy_ticket";
    }

    /**
     * 新增
     *
     * @param book
     * @param map
     * @return
     */
    @RequestMapping(value = "/addYearStrategyTicket.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(YearStrategyTicketModel yearStrategyTicket,HttpServletRequest request) {
        boolean result = false;
        try {
            if(yearStrategyService.doInserYearStrategyTicketAndRelateInfo(yearStrategyTicket,request)) {
                return RtnData.ok("新增年卡成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========新增年卡策略失败=========",e);
        }
        return RtnData.fail("新增年卡策略失败");
    }

    @RequestMapping(value = "/getSubStadiumListByMainId.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData getSubStadiumListByMainId(@RequestParam(required = true) String mainStadiumId) {
        try {
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("subStadiumList",yearStrategyService.getSubStadiumListByMainId(mainStadiumId));
            return RtnData.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("========根据主场馆ID获取子场馆列表失败=========",e);
        }
        return RtnData.fail("根据主场馆ID获取子场馆列表失败");
    }

//    /**
//     * 根据
//     * @param yearStrategyId
//     * @return
//     */
//    @RequestMapping(value = "/getYearStrategyTicketModelInfo.json")
//    @ResponseBody
//    public RtnData getYearStrategyTicketModelInfo(@RequestParam(required = true) String yearStrategyId) {
//        try {
//            Map<String,Object> resultMap = new HashMap<String,Object>();
//            resultMap.put("yearStrategyDetail",yearStrategyDao.getYearStrategyTicketModelById(yearStrategyId));
//            resultMap.put("studStadiumList",yearStrategyDao.getYearStrategyStadiumRelationsModelByYearStrategyId(yearStrategyId));
//            return RtnData.ok(resultMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("========根据票策略ID获取票策略详情及子场馆列表失败=========",e);
//        }
//        return RtnData.fail("根据票策略ID获取票策略详情及子场馆列表失败");
//    }


}

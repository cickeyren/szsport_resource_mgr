package com.digitalchina.sport.mgr.resource.controller.yearstrategyticket;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.model.Book;
import com.digitalchina.sport.mgr.resource.model.Category;
import com.digitalchina.sport.mgr.resource.service.BookService;
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
    private BookService bookService;


    /**
     * 进入新增页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String add(ModelMap map) {
//        List<Category> categorys = bookService.findCategorys();
//        map.put("categorys", categorys);
        return "yearstrategyticket/add_year_strategy_ticket";
    }
}

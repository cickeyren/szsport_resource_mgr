package com.digitalchina.sport.mgr.resource.controller.order;

import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.sport.mgr.resource.service.OrderService;
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
 * 订单
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;



    /**
     * 进入主页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/myOrder.html")
    public String myOrder(ModelMap map) {
        return "order/myOrder";
    }

    /**
     * 订单列表
     * @param pageSize
     * @param page
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderList.html")
    public String orderList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                            @RequestParam(required = false, defaultValue = "1") long page,
                            @RequestParam(required = false) String userTel,
                            @RequestParam(required = false) String ticketType,
                            @RequestParam(required = false) String ticketName,
                            @RequestParam(required = false) String orderChannel,
                            @RequestParam(required = false) String orderStartDate,
                            @RequestParam(required = false) String orderEndDate,
                            @RequestParam(required = false) String status,
                            ModelMap map,HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        //String userId = "5eb76ae3dd5246bda465b22aa1fdb0a8";
        //params.put("userId", userId);
        System.out.print(status);
        params.put("userTel", userTel);
        params.put("ticketType", ticketType);
        params.put("ticketName", ticketName);
        params.put("orderChannel", orderChannel);
        params.put("orderStartDate", orderStartDate);
        params.put("orderEndDate", orderEndDate);
        params.put("status", status);
        try {
            int totalSize = orderService.getCountByMap(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("pageSize", pageSize);
            List<Map<String,Object>> orderList = orderService.getOrderListByMap(params);
            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
            map.put("userTel", userTel);
            map.put("ticketType", ticketType);
            map.put("ticketName", ticketName);
            map.put("orderChannel", orderChannel);
            map.put("status", status);
            map.put("orderStartDate", orderStartDate);
            map.put("orderEndDate", orderEndDate);//回到页面,保留搜索关键字
            map.put("orderList",orderList);
            return "order/myOrder";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取订单失败");
            map.put("url",request.getRequestURI());
            map.put("exception",e);
            return "error";
        }

    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderDetails.html")
    public String orderDetailsAndOrderContentList(@RequestParam(required = true) String orderId,
                                                  ModelMap map,HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId", orderId);
        try {
            map.put("orderDetails", orderService.getOrderDetailsByMap(params));
            map.put("orderContentList",orderService.getOrderContentListByMap(params));
            return "order/orderDetails";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取订单详情失败");
            map.put("url",request.getRequestURI());
            map.put("exception",e);
            return "error";
        }
    }

    /**
     * 子单详情
     * @param orderContentId
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderContent.html")
    public String orderContentAndUsedRecords(@RequestParam(required = true) String orderContentId,
                                             ModelMap map,HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderContentId", orderContentId);
        try {
            map.put("orderContent",orderService.getOrderContentById(params));
            map.put("usedRecordsList",orderService.getUsedRecordsByOrderContentId(params));
            return "order/orderContent";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取子订单详情失败");
            map.put("url",request.getRequestURI());
            map.put("exception",e);
            return "error";
        }
    }
}

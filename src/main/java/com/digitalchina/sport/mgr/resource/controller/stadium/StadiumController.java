package com.digitalchina.sport.mgr.resource.controller.stadium;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.sport.mgr.resource.controller.exposeapi.ApiYearStrategyTicketController;
import com.digitalchina.sport.mgr.resource.service.StadiumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author:yangyt
 * @Description
 * @Date:Created in 10:30 2017/2/15
 */
@RestController
@RequestMapping(value = "/addMainStadiumInfo/api/")
public class StadiumController {

    public static final Logger logger = LoggerFactory.getLogger(StadiumController.class);

    @Autowired
    private StadiumService stadiumService;

    @RequestMapping(value = "insertMainStadiumInfo.json")
    @ResponseBody
    public RtnData insertMainStadiumInfo(@RequestParam(required = true) Map paramMap){

        return RtnData.ok(1);
    }


    @RequestMapping(value = "getZoneList.json")
    @ResponseBody
    public RtnData getZoneList(@RequestParam(required = true) String zoneId){
        //获取省级列表
        List<Map<String,Object>> provienceList = stadiumService.getProvienceList();
        return RtnData.ok(provienceList);
    }

}

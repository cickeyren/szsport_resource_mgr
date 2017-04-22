package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.quartz.util.DailyTime;
import com.digitalchina.sport.mgr.resource.dao.MerchantMapper;
import com.digitalchina.sport.mgr.resource.dao.StatisticsDao;
import com.digitalchina.sport.mgr.resource.model.StatisticsByPaytype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_ on 2017/4/17.
 */
@Service
public class StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;
    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 统计日报
     *
     * @param args
     * @param sType 统计方式 1临时统计  0日结
     *              userId 商户id
     *              stadiumId 主场馆id
     *              sTime 开始时间
     *              eTime 结束时间
     *              OR createDate
     *              ifDaily 统计类型
     */
    @Transactional
    public void statisticsByPaytype(Map<String, Object> args, String sType) {
        if ("1".equals(sType)) {//临时统计
            args.put("ifDaily","1");
            List<Map<String, Object>> temp = getStatisticsDaily(args);
            if(temp.size()>0){
                for (int i = 0; i < temp.size(); i++) {
                    Map<String, Object> daliyStatistic = temp.get(i);
                    if ("0".equals(daliyStatistic.get("ifDaily").toString())) {//以日结不做统计
                        return;
                    }else {//临时统计
                        statisticsDao.callStatisticByPayType(args);//重新临时统计
                    }
                }
            }else {//没有日结，也没有临时统计过，做第一次临时统计
                statisticsDao.callStatisticByPayType(args);
            }
        } else {//日结统计
            statisticsDao.callStatisticByPayType(args);
        }
        if (args.get("res")!=null&&(Integer)args.get("res")==-1) {//事务回滚
            try {
                throw new Exception("未知错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param args
     * userId 商户id
     *              stadiumId 主场馆id
     *              sTime 开始时间
     *              eTime 结束时间
     * @return
     * @throws Exception
     */
    @Transactional
    public List<Map<String, Object>> getStatisticsByPaytypes(Map<String, Object> args) throws Exception {
        statisticDailyTemporary(args);
        List<String> payTypes=statisticsDao.getPayment(args);
        args.put("payTypes",payTypes);
        return statisticsDao.getStatisticsByPaytypes(args);
    }
    public List<Map<String,Object>> getStatisticsByTicketTypes(Map<String, Object> args) throws Exception {
        statisticDailyTemporary(args);
        List<String> ticketTypes=statisticsDao.getTicketType(args);
        args.put("ticketTypes",ticketTypes);
        return statisticsDao.getStatisticsByTicketTypes(args);
    }

    /**
     * 临时统计
     * @param args
     */
    private void statisticDailyTemporary(Map<String, Object> args){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        String daily = fmt.format(today);
        String eDate = args.get("eDate")==null?null:args.get("eDate").toString();
        if (eDate != null && daily.contains(eDate)) {//如果查询结束日期是当天，则需要根据今天是否日结统计过来执行统计
            //userId stadiumId
            List<Map<String,String>> list = merchantMapper.getMerchants(args);
            String settlementTime = list.get(0).get("settlementTime");//日结时间点
            String sDate = DailyTime.getYesterday(today,"yyyy-MM-dd")+" "+settlementTime;
            args.put("createDate", eDate);
            args.put("sTime", sDate);//临时统计开始时间
            args.put("eTime", daily);//临时停机截止时间
            statisticsByPaytype(args, "1");
        }
    }
    public List<Map<String, Object>> getStatisticsDaily(Map<String, Object> args) {
        return statisticsDao.getStatisticsDaily(args);
    }
    public List<Map<String, Object>> getStatisticsInfo(Map<String, Object> args){
        statisticDailyTemporary(args);
        List<String> payTypes=statisticsDao.getPayment(args);
        args.put("payTypes",payTypes);
        return statisticsDao.getStatisticsInfo(args);
    }
}

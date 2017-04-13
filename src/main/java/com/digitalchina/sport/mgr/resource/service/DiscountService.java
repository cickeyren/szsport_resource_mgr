package com.digitalchina.sport.mgr.resource.service;


import com.digitalchina.sport.mgr.resource.dao.DiscountDao;
import com.digitalchina.sport.mgr.resource.model.DiscountConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class DiscountService {

    @Autowired
    private DiscountDao discountDao;

    public List<Map<String,Object>> getListByMap(Map<String, Object> param) throws Exception{
        return discountDao.getListByMap(param);
    };

    public int getCountByMap(Map<String, Object> param) throws Exception{
        return discountDao.getCountByMap(param);
    };

    public List<Map<String, String>> getPayType() throws Exception{
        return discountDao.getPayType();
    };
    public List<Map<String, String>> getMainStadium() throws Exception{
        return discountDao.getMainStadium();
    };
    public List<Map<String, String>> getSubStadium(String mainStadium) throws Exception {
        return discountDao.getSubStadium(mainStadium);
    };
    public List<Map<String, String>> getDiscountType() throws Exception{
        return discountDao.getDiscountType();
    };

    public int insert(DiscountConfigure discount) throws Exception{
        return discountDao.insert(discount);
    };

    public int zuofei(String id) throws Exception{
        return discountDao.zuofei(id);
    };

    public int update(DiscountConfigure discount) throws Exception{
        return discountDao.update(discount);
    };

    public DiscountConfigure getDiscountById(String id) throws Exception{
        return discountDao.getDiscountById(id);
    };

    public int getSameCountByParams(Map<String, Object> param) throws Exception{
        return discountDao.getSameCountByParams(param);
    };

    public int updateOverTimeStatus()throws Exception{
        return discountDao.updateOverTimeStatus("2");
    };
}

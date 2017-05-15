package com.digitalchina.sport.mgr.resource.service;


import com.digitalchina.common.utils.StringUtil;
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
        List<Map<String,Object>> list = discountDao.getListByMap(param);
        String subStadiumNames="";
        if (list.size()>0) {
            for(int i=0;i<list.size();i++){
                if (!StringUtil.isEmpty(list.get(i).get("subStadiumId"))){
                    String subStadiumIds = list.get(i).get("subStadiumId").toString();
                    String subStadiums[] = subStadiumIds.split(",");
                    for (int j=0;j<subStadiums.length;j++){
                        subStadiumNames += discountDao.getSubStadiumById(subStadiums[j]).get("name")+",";
                    }
                    subStadiumNames = subStadiumNames.substring(0, subStadiumNames.length() - 1);
                }else {
                    subStadiumNames = "全部";
                }
                list.get(i).put("subStadium",subStadiumNames);
            }
        }
        return list;
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

    public boolean getSameCountByParams(Map<String, Object> param) throws Exception{
        boolean flag = true;
        if (!StringUtil.isEmpty(param.get("subStadiumId"))){
            String subStadiumIds[] = param.get("subStadiumId").toString().split(",");
            if (subStadiumIds.length>0){
                for (int i=0;i<subStadiumIds.length;i++){
                    param.put("subStadiumId",subStadiumIds[i]);
                    Map<String, Object> sameMap = discountDao.getSameCountByParams(param);
                    String strcount = sameMap.get("count").toString();
                    int count = Integer.parseInt(strcount);
                    if (count>0){
                        if (!StringUtil.isEmpty(param.get("id"))){
                            String id = param.get("id").toString();
                            if (!id.equals(sameMap.get("id"))){
                                flag=false;
                                break;
                            }
                        }else {
                            flag=false;
                            break;
                        }
                    }
                }
            }else
                flag=false;
        }else
            flag=false;
        return flag;
    };

    public int updateOverTimeStatus()throws Exception{
        return discountDao.updateOverTimeStatus("2");
    };

    public DiscountConfigure getDetailById(String id) throws Exception{
        return discountDao.getDetailById(id);
    };

    public Map<String, Object> getMainStadiumById(String id) throws Exception{
        return discountDao.getMainStadiumById(id);
    };
    public Map<String, Object> getSubStadiumById(String id) throws Exception{
        return discountDao.getSubStadiumById(id);
    };
}

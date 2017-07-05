package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.utils.StringUtil;
import com.digitalchina.sport.mgr.resource.dao.CurriculumClassMapper;
import com.digitalchina.sport.mgr.resource.dao.CurriculumMapper;
import com.digitalchina.sport.mgr.resource.dao.CurriculumTypeMapper;
import com.digitalchina.sport.mgr.resource.model.Curriculum;
import com.digitalchina.sport.mgr.resource.model.CurriculumClass;
import com.digitalchina.sport.mgr.resource.model.CurriculumClassTimes;
import com.digitalchina.sport.mgr.resource.model.CurriculumType;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_ on 2017/5/5.
 */
@Service
public class CuriculumService {

    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private CurriculumClassMapper curriculumClassMapper;
    @Autowired
    private CurriculumTypeMapper curriculumTypeMapper;
    public int insertCurrriculum(Curriculum currriculum){
        return curriculumMapper.insertSelective(currriculum);
    }
    public int updataCurrriculum(Curriculum currriculum){
        return curriculumMapper.updateByPrimaryKeySelective(currriculum);
    }
    public int updataCurrriculumClass(CurriculumClass currriculumClass){
        return curriculumClassMapper.updateByPrimaryKeySelective(currriculumClass);
    }

    /**
     * 更新课程
     * @param currriculum
     * @return
     */
    public int doUpdataCurriculum(Curriculum currriculum){
        return curriculumMapper.updateByPrimaryKeySelective(currriculum);
    }

    /**
     * 新增班次
     * @param currriculumClass
     * @param classTimes
     * @return
     */
    @Transactional
    public int insertCurrriculumClass(CurriculumClass currriculumClass,List<Object> classTimes){
        int ret = 0;
        ret = curriculumClassMapper.insert(currriculumClass);
        String id = currriculumClass.getId();
        Map<String,Object> temp = null;
        for (int i=0;i<classTimes.size();i++){
            temp = (Map<String,Object>) classTimes.get(i);
            temp.put("class_id",id);
            ret = curriculumClassMapper.addCurriculumClassesTimes(temp);
        }
        return ret;
    }


    /**
     * 更新班次信息
     * @param currriculumClass
     * @param classTimes
     * @param class_times_id_del
     * @return
     */
    @Transactional
    public void doUpdataCurriculumClass(CurriculumClass currriculumClass, List<Object> classTimes, String class_times_id_del){

        curriculumClassMapper.updateByPrimaryKeySelective(currriculumClass);
        String id = currriculumClass.getId();

        for (int i=0;i<classTimes.size();i++){//新增时间段
            Map<String,Object> temp = (Map<String,Object>)classTimes.get(i);
            if (StringUtils.isEmpty(temp.get("id"))){
                //新增加的上课时段
                temp.put("class_id",id);
                curriculumClassMapper.addCurriculumClassesTimes(temp);
            }else{
                //更新的上课时段
                curriculumClassMapper.updateCurriculumClassesTimes(temp);
            }
        }
        //删除上课时段
        class_times_id_del = class_times_id_del == null ? "" : class_times_id_del;
        if(!StringUtil.isEmpty(class_times_id_del)){
            String[] class_times_id_dels = class_times_id_del.split(",");
            for(String id_del : class_times_id_dels){
                curriculumClassMapper.delTimess(id_del);
            }
        }
    }

    /**
     * 更新班次信息
     * @param currriculumClass
     * @param classTimes
     * @return
     */
    @Transactional
    public int doUpdataCurriculumClass(CurriculumClass currriculumClass,List<Object> classTimes){
        int ret = 0;
        ret = curriculumClassMapper.updateByPrimaryKeySelective(currriculumClass);
        String id = currriculumClass.getId();

        for (int i=0;i<classTimes.size();i++){//新增时间段
            Map<String,Object> temp = (Map<String,Object>)classTimes.get(i);
            if (StringUtils.isEmpty(temp.get("id"))){
                temp.put("class_id",id);
                ret = curriculumClassMapper.addCurriculumClassesTimes(temp);
            }
        }
        return ret;
    }
    public int doUpdataCurriculumClassTime(Map<String,Object> args){
        return curriculumClassMapper.updateCurriculumClassesTimes(args);
    }
    public List<Curriculum> getCurriculum(Map<String,Object> args){
        return curriculumMapper.getCurriculum(args);
    }
    public int getCurriculumCount(Map<String,Object> args){
        return curriculumMapper.getCurriculumCount(args);
    }
    public List<Curriculum> getCurriculumByNameExHas(Map<String,Object> args){
        return curriculumMapper.getCurriculumByNameExHas(args);
    }
    public List<Curriculum> getCurriculumByIds(Map<String,Object> args){
        return curriculumMapper.getCurriculumByIds(args);
    }
    public List<Curriculum> getCurriculumByIdsNot(Map<String,Object> args){
        return curriculumMapper.getCurriculumByIdsNot(args);
    }
    public Curriculum getCurriculumByKey(Integer key){
        //return curriculumMapper.selectByPrimaryKey(key);
        return curriculumMapper.selectById(key);
    }
    public CurriculumClass getCurriculumClassByKey(String id){
        return curriculumClassMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取班次
     * @param curriculumId
     * @return
     */
    public List<CurriculumClassTimes> getCurriculumClasses(String curriculumId ){
        return curriculumClassMapper.getCurriculumClasses(curriculumId);
    }

    /**
     * 获取班次上课时间段
     * @param classId
     * @return
     */
    public List<Map<String,Object>> getClassTimes(String classId){
        return curriculumClassMapper.getClassTimes(classId);
    }

    /**
     * 删除班次的上课时段
     * @param id  班次id
     * @return
     */
    public int delTimess(String id){
        return curriculumClassMapper.delTimess(id);
    }

    /**
     * 获取培训类型
     * @return
     */
    public List<CurriculumType> getCurriculumType(){
        return curriculumTypeMapper.selectAll();
    }

    public List<Map<String,Object>> getCurriculumOrderHasPay(Map<String,Object> args){
        return curriculumMapper.getCurriculumOrderHasPay(args);
    }
    public int getCurriculumOrderHasPayCount(Map<String,Object> args){
        return curriculumMapper.getCurriculumOrderHasPayCount(args);
    }

    public List<Map<String,Object>> getAvailCurriculumListByInstitutionId(String institutionId, Integer curriculumId){
        return curriculumMapper.getAvailCurriculumListByInstitutionId(institutionId, curriculumId);
    }
    /**
     * 根据条件查询所有订单
     * @param param
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getOrderListByMap(Map<String,Object> param) throws Exception {
        return curriculumMapper.getOrderListByMap(param);
    }

    /**
     * 根据条件查询所有订单
     * @param param
     * @return
     * @throws Exception
     */
    public int getCountByMap(Map<String,Object> param) throws Exception {
        return curriculumMapper.getCountByMap(param);
    }
    public List<Map<String, String>> getCurriculumTypes() {
        return curriculumMapper.getCurriculumTypes();
    }
    /**
     * 订单详情
     * @param param
     * @return
     * @throws Exception
     */
    public Map<String,Object> getOrderDetailsByMap(Map<String,Object> param) throws Exception{
        Map<String,Object> retmap = curriculumMapper.getOrderDetailsByOrderId(param);
        String fee = "";
        String payFee="";
        String discountFee = "";
        if (!StringUtil.isEmpty(retmap.get("fee"))){
            fee=retmap.get("fee").toString();
        }
        if (!StringUtil.isEmpty(retmap.get("payFee"))){
            payFee = retmap.get("payFee").toString();
        }
        if (!StringUtil.isEmpty(fee) && !StringUtil.isEmpty(payFee)){
            double f = Double.parseDouble(fee);
            double p = Double.parseDouble(payFee);
            double d = f-p;
            discountFee = d+"";
        }
        retmap.put("discountFee",discountFee);
        return retmap;
    }
    public Map<String, Object> getCurriculumRefund(String orderId) throws Exception{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId",orderId);
        Map<String, Object> orderDetail = curriculumMapper.getOrderDetailsByOrderId(params);
        if (!StringUtil.isEmpty(orderDetail.get("refundStatus"))){
            return curriculumMapper.getCurriculumRefund(params);
        }else {
            return null;
        }
    }
    //（未支付超时订单根据失效时间逻辑判断）
    public int updateOrderByOrderTime() throws Exception{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status","4");
        params.put("remarks","未支付超时订单");
        return curriculumMapper.updateOrderByOrderTime(params);
    }


    public List<Map<String,Object>> getSlCurriculumList(Map<String,Object> param) {
        return curriculumMapper.getSlCurriculumList(param);
    }

    public List<Map<String,Object>> getSlCurriculumClassList(Map<String,Object> param) {
        return curriculumMapper.getSlCurriculumClassList(param);
    }
}

package com.digitalchina.sport.mgr.resource.service;

import com.digitalchina.common.data.Constants;
import com.digitalchina.common.utils.StringUtils;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.dao.*;
import com.digitalchina.sport.mgr.resource.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xujin on 2017/6/9.
 */
@Service
public class TrainInstitutionService {

    @Autowired
    TrainingInstitutionMapper trainingInstitutionMapper;
    @Autowired
    TrainingInstitutionPicMapper trainingInstitutionPicMapper;
    @Autowired
    TrainingInstitutionWpMapper trainingInstitutionWpMapper;
    @Autowired
    CurriculumDiscountMapper curriculumDiscountMapper;
    @Autowired
    CurriculumMapper curriculumMapper;

    /*
    此方法保留，之前开龙开发
     */
    public List<Map<String,Object>> listTrainInstitution() throws Exception{
        return trainingInstitutionMapper.listTrainInstitution();
    }

    public List<Map<String,Object>> getTrainInstitutionList(Map<String, Object> params) {
        List<Map<String,Object>> list = trainingInstitutionMapper.queryList(params);
        return list;
    }

    public int getTrainInstitutionListCount(Map<String, Object> params) {
        int count = trainingInstitutionMapper.queryListCount(params);
        return count;
    }

    public Map<String, Object> addTrainInstitution(TrainingInstitutionModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        if(StringUtils.isBlank(model.getName())){
            rtnMap.put(Constants.RTN_MSG, "培训机构名称不能为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getProvincial_level())){
            rtnMap.put(Constants.RTN_MSG, "区域-省不能为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getCity_level())){
            rtnMap.put(Constants.RTN_MSG, "区域-城市不能为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getDistrict_level())){
            rtnMap.put(Constants.RTN_MSG, "区域-区县不能为空");
            return rtnMap;
        }

        TrainingInstitution entity = new TrainingInstitution();
        entity.setId(UUIDUtil.generateUUID());
        entity.setOrg_name(model.getName());
        entity.setProvincial_level(model.getProvincial_level());
        entity.setCity_level(model.getCity_level());
        entity.setDistrict_level(model.getDistrict_level());
        entity.setAddress(model.getAddress());
        entity.setTelephone(model.getTelephone());
        entity.setLogo_url(model.getLogo_url());
        entity.setIntroduction(model.getIntroduction());
        entity.setStatus(model.getStatus());
        entity.setCreate_time(new Date());
        entity.setLng(model.getLng());
        entity.setLat(model.getLat());
        trainingInstitutionMapper.insert(entity);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public TrainingInstitution selectById(Map<String, Object> param) {

        String id = (String) param.get("id");
        if(StringUtils.isBlank(id)){
            return null;
        }
        TrainingInstitution entity  = trainingInstitutionMapper.selectByPrimaryKey(id);

        return entity;
    }

    public Map<String,Object> updateTrainInstitution(TrainingInstitutionModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        if(StringUtils.isBlank(model.getName())){
            rtnMap.put(Constants.RTN_MSG, "培训机构名称不能为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getProvincial_level())){
            rtnMap.put(Constants.RTN_MSG, "区域-省不能为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getCity_level())){
            rtnMap.put(Constants.RTN_MSG, "区域-城市不能为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getDistrict_level())){
            rtnMap.put(Constants.RTN_MSG, "区域-区县不能为空");
            return rtnMap;
        }

        String id = model.getId();
        TrainingInstitution entity = trainingInstitutionMapper.selectByPrimaryKey(id);
        if(entity==null){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "培训机构不存在");
            return rtnMap;
        }

        entity.setOrg_name(model.getName());
        entity.setProvincial_level(model.getProvincial_level());
        entity.setCity_level(model.getCity_level());
        entity.setDistrict_level(model.getDistrict_level());
        entity.setAddress(model.getAddress());
        entity.setTelephone(model.getTelephone());
        entity.setLogo_url(model.getLogo_url());
        entity.setIntroduction(model.getIntroduction());
        entity.setStatus(model.getStatus());
        entity.setUpdate_time(new Date());
        entity.setLng(model.getLng());
        entity.setLat(model.getLat());
        trainingInstitutionMapper.updateByPrimaryKeySelective(entity);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public List<Map<String,Object>> getPicList(Map<String, Object> params) {
        String id = (String) params.get("id");
        List<Map<String,Object>> picList = trainingInstitutionPicMapper.selectByInstitutionId(id);
        return picList;
    }


    @Transactional
    public Map<String,Object> setPicIsFirst(TrainingInstitutionPicModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        String id = model.getInstitution_id();
        TrainingInstitution entity = trainingInstitutionMapper.selectByPrimaryKey(id);
        if(entity==null){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "培训机构不存在");
            return rtnMap;
        }
        String picId = model.getId();
        if(StringUtils.isBlank(picId)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "机构图片id为空");
            return rtnMap;
        }

        TrainingInstitutionPic picEntity = new TrainingInstitutionPic();
        picEntity.setIs_first("0");
        picEntity.setUpdate_time(new Date());
        trainingInstitutionPicMapper.updateDefaultStatus(picEntity);

        picEntity = new TrainingInstitutionPic();
        picEntity.setId(picId);
        picEntity.setIs_first("1");
        trainingInstitutionPicMapper.updateByPrimaryKeySelective(picEntity);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public Map<String,Object> delInstitutionPic(TrainingInstitutionPicModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        String picId = model.getId();
        trainingInstitutionPicMapper.deleteByPrimaryKey(picId);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public Map<String,Object> addInstitutionPic(TrainingInstitutionPicModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        String id = model.getInstitution_id();
        TrainingInstitution entity = trainingInstitutionMapper.selectByPrimaryKey(id);
        if(entity==null){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "培训机构不存在");
            return rtnMap;
        }
        TrainingInstitutionPic entityPic = new TrainingInstitutionPic();
        entityPic.setId(UUIDUtil.generateUUID());
        entityPic.setPic_url(model.getPic_url());
        entityPic.setInstitution_id(model.getInstitution_id());
        entityPic.setCreate_time(new Date());
        entityPic.setIs_first("0");
        trainingInstitutionPicMapper.insert(entityPic);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public List<Map<String,Object>> getWindowPaymentList(Map<String, Object> params) {
        List<Map<String,Object>> list = trainingInstitutionWpMapper.queryList(params);
        return list;
    }

    public int getWindowPaymentListCount(Map<String, Object> params) {
        int count = trainingInstitutionWpMapper.queryListCount(params);
        return count;
    }

    public TrainingInstitutionWp selectWindowPaymentById(Map<String, Object> param) {
        String wpId = (String) param.get("wpId");
        if(StringUtils.isBlank(wpId)){
            return null;
        }
        TrainingInstitutionWp entity = this.trainingInstitutionWpMapper.selectByPrimaryKey(wpId);
        return entity;
    }

    public Map<String,Object> addWindowPayment(TrainingInstitutionWpModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        String id = model.getInstitution_id();
        if(StringUtils.isBlank(id)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "培训机构id为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getMerchant_id())){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "合作商户id为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getPayment_id())){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "支付方式id为空");
            return rtnMap;
        }
        TrainingInstitution entity = trainingInstitutionMapper.selectByPrimaryKey(id);
        if(entity==null) {
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "培训机构不存在");
            return rtnMap;
        }

        Map<String,Object> existWp = trainingInstitutionWpMapper.selectById(model.getInstitution_id(), model.getMerchant_id(), model.getPayment_id());
        if(existWp!=null){
            String wpId = (String) existWp.get("id");
            if(!StringUtils.isBlank(wpId)){
                String wpName = (String) existWp.get("payment");
                String rtnMsg = StringUtils.isBlank(wpName) ? "支付方式已添加" : "支付方式["+wpName+"]已添加";
                rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
                rtnMap.put(Constants.RTN_MSG, rtnMsg);
                return rtnMap;
            }
        }

        TrainingInstitutionWp entityWp = new TrainingInstitutionWp();
        entityWp.setId(UUIDUtil.generateUUID());
        entityWp.setMerchant_id(model.getMerchant_id());
        entityWp.setPayment_id(model.getPayment_id());
        entityWp.setInstitution_id(model.getInstitution_id());
        entityWp.setRemark(model.getRemark());
        entityWp.setCreate_time(new Date());
        trainingInstitutionWpMapper.insert(entityWp);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public Map<String,Object> updateWindowPayment(TrainingInstitutionWpModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        String id = model.getId();
        if(StringUtils.isBlank(id)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "窗口支付id为空");
            return rtnMap;
        }

        String institutionId = model.getInstitution_id();
        if(StringUtils.isBlank(institutionId)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "培训机构id为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getMerchant_id())){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "合作商户id为空");
            return rtnMap;
        }
        if(StringUtils.isBlank(model.getPayment_id())){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "支付方式id为空");
            return rtnMap;
        }
        TrainingInstitution entity = trainingInstitutionMapper.selectByPrimaryKey(institutionId);
        if(entity==null) {
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "培训机构不存在");
            return rtnMap;
        }

        Map<String,Object> existWp = trainingInstitutionWpMapper.selectById(model.getInstitution_id(), model.getMerchant_id(), model.getPayment_id());
        if(existWp!=null){
            String wpId = (String) existWp.get("id");
            if(!StringUtils.isBlank(wpId) && !wpId.equals(id)){
                String wpName = (String) existWp.get("payment");
                String rtnMsg = StringUtils.isBlank(wpName) ? "支付方式已添加" : "支付方式["+wpName+"]已添加";
                rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
                rtnMap.put(Constants.RTN_MSG, rtnMsg);
                return rtnMap;
            }
        }

        TrainingInstitutionWp entityWp = new TrainingInstitutionWp();
        entityWp.setId(id);
        entityWp.setMerchant_id(model.getMerchant_id());
        entityWp.setPayment_id(model.getPayment_id());
        entityWp.setRemark(model.getRemark());
        entityWp.setUpdate_time(new Date());
        trainingInstitutionWpMapper.updateByPrimaryKeySelective(entityWp);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public Map<String,Object> delWindowPayment(TrainingInstitutionWpModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        String wpId = model.getId();
        trainingInstitutionWpMapper.deleteByPrimaryKey(wpId);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public List<Map<String,Object>> getWindowDiscountList(Map<String, Object> params) {
        List<Map<String,Object>> list = curriculumDiscountMapper.queryList(params);
        return list;
    }

    public int getWindowDiscountListCount(Map<String, Object> params) {
        int count = curriculumDiscountMapper.queryListCount(params);
        return count;
    }

    public CurriculumDiscount selectWindowDiscountByCurriculumId(Map<String, Object> param) {
        String curriculum_id = (String) param.get("curriculum_id");
        CurriculumDiscount entity = curriculumDiscountMapper.selectByPrimaryKey(curriculum_id);
        return entity;
    }

    @Transactional
    public Map< String, Object> addWindowDiscount(CurriculumDiscountModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        Integer curriculumId = model.getCurriculum_id();
        if(curriculumId == null){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "课程id为空");
            return rtnMap;
        }
        String discountValueIdStr = model.getDiscount_value_id();
        if(StringUtils.isBlank(discountValueIdStr)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "折扣id为空");
            return rtnMap;
        }
        String[] discountValueIds = discountValueIdStr.split(",");

        CurriculumNew curriculumNew = curriculumMapper.selectNewByPrimaryKey(curriculumId);
        if(curriculumNew==null) {
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "课程id不存在");
            return rtnMap;
        }

        List<CurriculumDiscount> cdexist = curriculumDiscountMapper.selectById(curriculumId);
        if(cdexist==null || cdexist.size()>0){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "该课程窗口折扣已添加");
            return rtnMap;
        }

        for(int i=0; i<discountValueIds.length;i++){
            String discountValueId = discountValueIds[i];
            CurriculumDiscount entityCd = new CurriculumDiscount();
            entityCd.setId(UUIDUtil.generateUUID());
            entityCd.setCurriculum_id(model.getCurriculum_id());
            entityCd.setDiscount_value_id(discountValueId);
            curriculumDiscountMapper.insert(entityCd);
        }

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    @Transactional
    public Map<String, Object> updateWindowDiscount(CurriculumDiscountModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        Integer curriculumId = model.getCurriculum_id();
        Integer curriculumIdNew = model.getCurriculum_id_new();
        if(curriculumId == null || curriculumIdNew == null){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "课程id为空");
            return rtnMap;
        }
        String discountValueIdStr = model.getDiscount_value_id();
        if(StringUtils.isBlank(discountValueIdStr)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "折扣id为空");
            return rtnMap;
        }
        String[] discountValueIds = discountValueIdStr.split(",");

        curriculumDiscountMapper.deleteById(curriculumId);

        CurriculumNew curriculumNew = curriculumMapper.selectNewByPrimaryKey(curriculumIdNew);
        if(curriculumNew==null) {
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "课程id不存在");
            return rtnMap;
        }

        List<CurriculumDiscount> cdexist = curriculumDiscountMapper.selectById(curriculumIdNew);
        if(cdexist==null || cdexist.size()>0){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "该课程窗口折扣已添加");
            return rtnMap;
        }

        for(int i=0; i<discountValueIds.length;i++){
            String discountValueId = discountValueIds[i];
            CurriculumDiscount entityCd = new CurriculumDiscount();
            entityCd.setId(UUIDUtil.generateUUID());
            entityCd.setCurriculum_id(model.getCurriculum_id_new());
            entityCd.setDiscount_value_id(discountValueId);
            curriculumDiscountMapper.insert(entityCd);
        }

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }

    public Map<String,Object> updateDiscountStatus(CurriculumModel model) {
        Map<String,Object> rtnMap = new HashMap<String,Object>();
        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);

        Integer curriculumId = model.getId();
        if(curriculumId == null){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "课程id为空");
            return rtnMap;
        }
        String status = model.getDiscount_status();
        if(StringUtils.isBlank(status)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "操作未知");
            return rtnMap;
        }
        if(!"0".equals(status) && !"1".equals(status)){
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "操作未知");
            return rtnMap;
        }
        CurriculumNew curriculumNew = curriculumMapper.selectNewByPrimaryKey(curriculumId);
        if(curriculumNew==null) {
            rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_FAIL);
            rtnMap.put(Constants.RTN_MSG, "课程id不存在");
            return rtnMap;
        }

        CurriculumNew entity = new CurriculumNew();
        entity.setId(curriculumId);
        entity.setDiscount_status(status);
        curriculumMapper.updateNewByPrimaryKeySelective(entity);

        rtnMap.put(Constants.RTN_CODE, Constants.RTN_CODE_SUCCESS);
        rtnMap.put(Constants.RTN_MSG, "");
        return rtnMap;
    }
}

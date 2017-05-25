package com.digitalchina.sport.mgr.resource.service;

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
        return curriculumMapper.selectByPrimaryKey(key);
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
}

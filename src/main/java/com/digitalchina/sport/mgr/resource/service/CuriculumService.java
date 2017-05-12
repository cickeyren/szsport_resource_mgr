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
        return curriculumMapper.updateByPrimaryKey(currriculum);
    }

    /**
     * 新增班次
     * @param currriculumClass
     * @param classTimes
     * @return
     */
    @Transactional
    public int insertCurrriculumClass(CurriculumClass currriculumClass,List<String> classTimes){
        int ret = 0;
        ret = curriculumClassMapper.insert(currriculumClass);
        String id = currriculumClass.getId();
        Map<String,Object> temp = Maps.newHashMap();
        temp.put("class_id",id);
        for (int i=0;i<classTimes.size();i++){
            temp.put("time",classTimes.get(i));
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
    public int doUpdataCurriculumClass(CurriculumClass currriculumClass,List<String> classTimes){
        int ret = 0;
        ret = curriculumClassMapper.updateByPrimaryKeySelective(currriculumClass);
        String id = currriculumClass.getId();
        Map<String,Object> temp = Maps.newHashMap();
        temp.put("class_id",id);
        for (int i=0;i<classTimes.size();i++){//新增时间段
            temp.put("time",classTimes.get(i));
            ret = curriculumClassMapper.addCurriculumClassesTimes(temp);
        }
        return ret;
    }
    public List<Curriculum> getCurriculum(){
        return curriculumMapper.getCurriculum();
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
}

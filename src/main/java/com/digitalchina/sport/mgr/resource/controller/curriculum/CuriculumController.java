package com.digitalchina.sport.mgr.resource.controller.curriculum;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.model.Curriculum;
import com.digitalchina.sport.mgr.resource.model.CurriculumClass;
import com.digitalchina.sport.mgr.resource.model.CurriculumClassTimes;
import com.digitalchina.sport.mgr.resource.model.CurriculumType;
import com.digitalchina.sport.mgr.resource.service.CuriculumService;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @date 2017/2/27 14:06
 * @see
 */
@Controller
@RequestMapping(value = "/curriculumController")
public class CuriculumController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuriculumController.class);

    @Autowired
    private CuriculumService curiculumService;

    @RequestMapping(value = "/curriculum.html")
    public String getCurriculumList(ModelMap map) {
        List<Curriculum> list = curiculumService.getCurriculum();
        map.put("curriculums",list);
        return "curriculum/curriculum";
    }
    @RequestMapping(value = "/curriculumUpline.html")
    public String curriculumUpAndDownline(ModelMap map,Integer curriculumId,Integer status) {
        Curriculum curriculum = new Curriculum();
        curriculum.setId(curriculumId);
        if(status==1){
            curriculum.setStatus(2);
        }else if(status==2){
            curriculum.setStatus(1);
        }
        curiculumService.updataCurrriculum(curriculum);
        List<Curriculum> list = curiculumService.getCurriculum();
        map.put("curriculums",list);
        return "curriculum/curriculum";
    }
    @RequestMapping(value = "/recommend.html")
    public String recommend(ModelMap map,Integer curriculumId,Integer recommend) {
        Curriculum curriculum = new Curriculum();
        curriculum.setId(curriculumId);
        if(recommend==1){
            curriculum.setRecommend(2);
        }else if(recommend==2){
            curriculum.setRecommend(1);
        }
        curriculum.setRecommend_time(new Date());
        curiculumService.updataCurrriculum(curriculum);
        List<Curriculum> list = curiculumService.getCurriculum();
        map.put("curriculums",list);
        return "curriculum/curriculum";
    }
    @RequestMapping(value = "/editClassStatus")
    public String editClassStatus(ModelMap map,String id,Integer status,String curriculumId) {
        CurriculumClass curriculumClass = new CurriculumClass();
        curriculumClass.setId(id);
        curriculumClass.setStatus(status);
        curiculumService.updataCurrriculumClass(curriculumClass);
        List<CurriculumClassTimes> list = curiculumService.getCurriculumClasses(curriculumId);
        map.put("list",list);
        map.put("curriculumId",curriculumId);
        return "curriculum/curriculum_class";
    }
    @RequestMapping(value = "/addCurriculum.html")
    public String addCurriculum(ModelMap map) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("value","jg1");
        m1.put("text","机构1");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("value","jg2");
        m2.put("text","机构2");
        list.add(m1);
        list.add(m2);
        List<CurriculumType> types = curiculumService.getCurriculumType();
        map.put("trainingInstitutions",list);
        map.put("curiculumTypes",types);
        return "curriculum/add_curriculum";
    }
    @RequestMapping(value = "/addCurriculumClass.html")
    public String addCurriculumClass(String curriculumId,ModelMap map) {
        map.put("curriculumId",curriculumId);
        return "curriculum/add_curriculum_class";
    }
    @RequestMapping(value = "/curriculumClass.html")
    public String curriculumClass(ModelMap map,String curriculumId) {
        try {
            List<CurriculumClassTimes> list = curiculumService.getCurriculumClasses(curriculumId);
            map.put("list",list);
            map.put("curriculumId",curriculumId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "curriculum/curriculum_class";
    }
    @RequestMapping(value = "/editCurriculum.html")
    public String editCurriculum(ModelMap map,Integer curriculumId) {
        Curriculum curriculum =curiculumService.getCurriculumByKey(curriculumId);

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("value","jg1");
        m1.put("text","机构1");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("value","jg2");
        m2.put("text","机构2");
        list.add(m1);
        list.add(m2);
        String teachers = curriculum.getTeachers();
        Gson gson = new Gson();
        Map<String,Object> teachersMap =gson.fromJson(teachers,Map.class);
        Map<String,Object> allTeacher = Maps.newHashMap();
        allTeacher.put("volvo","Volvo");
        allTeacher.put("saab","Saab");
        allTeacher.put("opel","Opel");
        allTeacher.put("audi","Audi");
        System.out.println(teachersMap);
        List<Map<String,Object>> teacherList = new ArrayList();
        for (String key:teachersMap.keySet()){
            Map<String,Object> temp = Maps.newHashMap();
            temp.put("id",key);
            temp.put("value",teachersMap.get(key));
            teacherList.add(temp);
            allTeacher.remove(key);
        }
        List<Map<String,Object>> allTeacherList = new ArrayList();
        for (String key : allTeacher.keySet()){
            Map<String,Object> temp = Maps.newHashMap();
            temp.put("id",key);
            temp.put("value",allTeacher.get(key));
            allTeacherList.add(temp);
        }
        System.out.println(allTeacher);
        map.put("trainingInstitutions",list);
        map.put("curriculum",curriculum);
        map.put("teacherList",teacherList);
        map.put("allTeacherList",allTeacherList);
        return "curriculum/edit_curriculum";
    }
    @RequestMapping(value = "/edit_curriculum_class.html")
    public String editCurriculumClass(ModelMap map,String id) {
        CurriculumClass curriculumClass =curiculumService.getCurriculumClassByKey(id);
        List<Map<String,Object>> classTimes = curiculumService.getClassTimes(id);
        map.put("classTimes",classTimes);
        map.put("curriculumClass",curriculumClass);
        return "curriculum/edit_curriculum_class";
    }
    @RequestMapping(value = "/doAddCurriculum")
    @ResponseBody
    public RtnData doAddCurriculum(Curriculum curriculum) {
        System.out.println(curriculum);
        curiculumService.insertCurrriculum(curriculum);
        return RtnData.ok("");
    }
    @RequestMapping(value = "/doUpdataCurriculum")
    @ResponseBody
    public RtnData doUpdataCurriculum(Curriculum curriculum) {
        System.out.println(curriculum);
        curiculumService.doUpdataCurriculum(curriculum);
        return RtnData.ok("");
    }
    @RequestMapping(value = "/doUpdataCurriculumClass")
    @ResponseBody
    public RtnData doUpdataCurriculumClass(CurriculumClass curriculumClass,String timess) {
        System.out.println(curriculumClass);
        Gson gson = new Gson();
        List<String> classTimes =gson.fromJson(timess,List.class);
        curiculumService.doUpdataCurriculumClass(curriculumClass,classTimes);
        return RtnData.ok("");
    }
    @RequestMapping(value = "/delTimess")
    @ResponseBody
    public RtnData delTimess(String id) {
        return RtnData.ok(curiculumService.delTimess(id));
    }
    @RequestMapping(value = "/doAddCurriculumClass")
    @ResponseBody
    public RtnData doAddCurriculumClass(CurriculumClass curriculumClass,String timess) {
        System.out.println(curriculumClass);
        System.out.println(timess);
        String id = UUIDUtil.generateUUID();
        curriculumClass.setId(id);
        curriculumClass.setStatus(1);
        Gson gson = new Gson();

        List<String> classTimes =gson.fromJson(timess,List.class);
        curiculumService.insertCurrriculumClass(curriculumClass,classTimes);
        return RtnData.ok("");
    }
}

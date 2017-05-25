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
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getCurriculumList(ModelMap map, String training_institutions_name, String name,
                                    String train_type, @RequestParam(required = false, defaultValue = "1") long pageNum, @RequestParam(required = false, defaultValue = "5") long pageSize) {
        Map<String, Object> args = Maps.newHashMap();
        args.put("training_institutions_name", training_institutions_name);
        args.put("name", name);
        args.put("train_type", train_type);
        args.put("pNum", (pageNum - 1) * pageSize);
        args.put("pSize", pageSize);
        try {
            List<Curriculum> list = curiculumService.getCurriculum(args);
            map.put("curriculums", list);
            map.put("training_institutions_name", training_institutions_name);
            map.put("name", name);
            map.put("train_type", train_type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<CurriculumType> types = curiculumService.getCurriculumType();
        int total = curiculumService.getCurriculumCount(args);
        map.put("curiculumTypes", types);
        map.put("totalPage", total % pageSize > 0 ? (total / pageSize) + 1 : total / pageSize);
        map.put("currentPage", pageNum);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        return "curriculum/curriculum";
    }

    @RequestMapping(value = "/curriculumUpline.do")
    @ResponseBody
    public RtnData curriculumUpAndDownline(Integer curriculumId, Integer status) {
        Curriculum curriculum = new Curriculum();
        curriculum.setId(curriculumId);
        if (status == 1) {
            curriculum.setStatus(2);
        } else if (status == 2) {
            curriculum.setStatus(1);
        }
        curiculumService.updataCurrriculum(curriculum);
//        Map<String, Object> args = Maps.newHashMap();
//        List<Curriculum> list = curiculumService.getCurriculum(args);
//        map.put("curriculums", list);
        return RtnData.ok("");
    }

    @RequestMapping(value = "/recommend.do")
    @ResponseBody
    public RtnData recommend(Integer curriculumId, Integer recommend) {
        Curriculum curriculum = new Curriculum();
        curriculum.setId(curriculumId);
        if (recommend == 1) {
            curriculum.setRecommend(2);
        } else if (recommend == 2) {
            curriculum.setRecommend(1);
        }
        curriculum.setRecommend_time(new Date());
        curiculumService.updataCurrriculum(curriculum);
        return RtnData.ok("");
    }

    @RequestMapping(value = "/editClassStatus")
    public String editClassStatus(ModelMap map, String id, Integer status, String curriculumId) {
        CurriculumClass curriculumClass = new CurriculumClass();
        curriculumClass.setId(id);
        curriculumClass.setStatus(status);
        curiculumService.updataCurrriculumClass(curriculumClass);
        List<CurriculumClassTimes> list = curiculumService.getCurriculumClasses(curriculumId);
        map.put("list", list);
        map.put("curriculumId", curriculumId);
        return "curriculum/curriculum_class";
    }

    @RequestMapping(value = "/addCurriculum.html")
    public String addCurriculum(ModelMap map) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("value", "jg1");
        m1.put("text", "机构1");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("value", "jg2");
        m2.put("text", "机构2");
        list.add(m1);
        list.add(m2);
        List<CurriculumType> types = curiculumService.getCurriculumType();
        map.put("trainingInstitutions", list);
        map.put("curiculumTypes", types);
        return "curriculum/add_curriculum";
    }

    @RequestMapping(value = "/addCurriculumClass.html")
    public String addCurriculumClass(String curriculumId, ModelMap map) {
        map.put("curriculumId", curriculumId);
        return "curriculum/add_curriculum_class";
    }

    @RequestMapping(value = "/curriculumClass.html")
    public String curriculumClass(ModelMap map, String curriculumId) {
        try {
            List<CurriculumClassTimes> list = curiculumService.getCurriculumClasses(curriculumId);
            map.put("list", list);
            map.put("curriculumId", curriculumId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "curriculum/curriculum_class";
    }

    @RequestMapping(value = "/editCurriculum.html")
    public String editCurriculum(ModelMap map, Integer curriculumId) {
        Curriculum curriculum = curiculumService.getCurriculumByKey(curriculumId);

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("value", "jg1");
        m1.put("text", "机构1");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("value", "jg2");
        m2.put("text", "机构2");
        list.add(m1);
        list.add(m2);
        String teachers = curriculum.getTeachers();
        Gson gson = new Gson();
        Map<String, Object> teachersMap = gson.fromJson(teachers, Map.class);
        Map<String, Object> allTeacher = Maps.newHashMap();
        allTeacher.put("volvo", "Volvo");
        allTeacher.put("saab", "Saab");
        allTeacher.put("opel", "Opel");
        allTeacher.put("audi", "Audi");
        System.out.println(teachersMap);
        List<Map<String, Object>> teacherList = new ArrayList();
        for (String key : teachersMap.keySet()) {
            Map<String, Object> temp = Maps.newHashMap();
            temp.put("id", key);
            temp.put("value", teachersMap.get(key));
            teacherList.add(temp);
            allTeacher.remove(key);
        }
        List<Map<String, Object>> allTeacherList = new ArrayList();
        for (String key : allTeacher.keySet()) {
            Map<String, Object> temp = Maps.newHashMap();
            temp.put("id", key);
            temp.put("value", allTeacher.get(key));
            allTeacherList.add(temp);
        }
        List<CurriculumType> types = curiculumService.getCurriculumType();
        map.put("curiculumTypes", types);
        map.put("trainingInstitutions", list);
        map.put("curriculum", curriculum);
        map.put("teacherList", teacherList);
        map.put("allTeacherList", allTeacherList);
        return "curriculum/edit_curriculum";
    }

    @RequestMapping(value = "/edit_curriculum_class.html")
    public String editCurriculumClass(ModelMap map, String id) {
        CurriculumClass curriculumClass = curiculumService.getCurriculumClassByKey(id);
        List<Map<String, Object>> classTimes = curiculumService.getClassTimes(id);
        map.put("classTimes", classTimes);
        map.put("curriculumClass", curriculumClass);
        return "curriculum/edit_curriculum_class";
    }

    @RequestMapping(value = "/xuban")
    public String xuban(ModelMap map, String id, Integer curriculumId) {
        Map<String, Object> args = Maps.newHashMap();//数据库查询
        try {
            //获取当前班次信息
            CurriculumClass curriculumClass = curiculumService.getCurriculumClassByKey(id);
            //当前班次产于续班优惠的课程id
            String xuban_curriculum = curriculumClass.getXuban_curriculum();
            Gson gson = new Gson();
            //当前班次产于续班优惠的课程id List
            List<Integer> xuban_curriculums_id = gson.fromJson(xuban_curriculum, List.class);
//            List<Integer> xuban_curriculums_id = new ArrayList<>();
//            xuban_curriculums_id.add(14);
            //获取当前班次所属的课程
            Curriculum curriculum = curiculumService.getCurriculumByKey(curriculumId);
            //获取当前班次所属的课程的所属机构id
            String training_institutions_id = curriculum.getTraining_institutions_id();
            args.put("training_institutions_id", training_institutions_id);
            args.put("ids", xuban_curriculums_id);
            //获取未参加该班次续报优惠本机构的课程列表
            List<Curriculum> alllist = curiculumService.getCurriculumByIdsNot(args);
            //获取以参加该班次续报优惠本机构的课程列表
            List<Curriculum> haslist = new ArrayList<>();
            if (xuban_curriculums_id != null && xuban_curriculums_id.size() > 0) {
                haslist = curiculumService.getCurriculumByIds(args);
            }
            map.put("class_id", id);
            map.put("curriculumId", curriculumId);
            map.put("alllist", alllist);
            map.put("haslist", haslist);
            map.put("discount_fee", curriculumClass.getDiscount_fee());
            return "curriculum/xuban";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @RequestMapping(value = "/getCurriculumByNameExHas")
    @ResponseBody
    public RtnData getCurriculumByNameExHas(String name, Integer curriculumId, String id) {
        //获取当前班次信息
        CurriculumClass curriculumClass = curiculumService.getCurriculumClassByKey(id);
        //当前班次产于续班优惠的课程id
        String xuban_curriculum = curriculumClass.getXuban_curriculum();
        Gson gson = new Gson();
        //当前班次产于续班优惠的课程id List
        List<Integer> xuban_curriculums_id = gson.fromJson(xuban_curriculum, List.class);
        //获取当前班次所属的课程
        Curriculum curriculum = curiculumService.getCurriculumByKey(curriculumId);
        //获取当前班次所属的课程的所属机构id
        String training_institutions_id = curriculum.getTraining_institutions_id();
        Map<String, Object> args = Maps.newHashMap();
        args.put("training_institutions_id", training_institutions_id);
        args.put("name", name);
        args.put("ids", xuban_curriculums_id);
        List<Curriculum> curriculumList = curiculumService.getCurriculumByNameExHas(args);
        return RtnData.ok(curriculumList);
    }

    @RequestMapping(value = "/doAddCurriculum")
    @ResponseBody
    public RtnData doAddCurriculum(Curriculum curriculum) {
        System.out.println(curriculum);
        curiculumService.insertCurrriculum(curriculum);
        return RtnData.ok("");
    }

    @RequestMapping(value = "/xubanCurriculum")
    @ResponseBody
    public RtnData xubanCurriculum(CurriculumClass curriculumClass) {
        curiculumService.updataCurrriculumClass(curriculumClass);
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
    public RtnData doUpdataCurriculumClass(CurriculumClass curriculumClass, String timess) {
        System.out.println(curriculumClass);
        Gson gson = new Gson();
        List<Object> classTimes = gson.fromJson(timess, List.class);
        curiculumService.doUpdataCurriculumClass(curriculumClass, classTimes);
        return RtnData.ok("");
    }

    @RequestMapping(value = "/doUpdataCurriculumClassTime")
    @ResponseBody
    public RtnData doUpdataCurriculumClassTime(String id, String time, String max_people, String reserve_people) {
        Map<String, Object> args = Maps.newHashMap();
        args.put("id", id);
        args.put("time", time);
        args.put("max_people", max_people);
        args.put("reserve_people", reserve_people);
        curiculumService.doUpdataCurriculumClassTime(args);
        return RtnData.ok("");
    }

    @RequestMapping(value = "/delTimess")
    @ResponseBody
    public RtnData delTimess(String id) {
        return RtnData.ok(curiculumService.delTimess(id));
    }

    @RequestMapping(value = "/doAddCurriculumClass")
    @ResponseBody
    public RtnData doAddCurriculumClass(CurriculumClass curriculumClass, String timess) {
        System.out.println(curriculumClass);
        System.out.println(timess);
        String id = UUIDUtil.generateUUID();
        curriculumClass.setId(id);
        curriculumClass.setStatus(1);
        Gson gson = new Gson();

        List<Object> classTimes = gson.fromJson(timess, List.class);
        curiculumService.insertCurrriculumClass(curriculumClass, classTimes);
        return RtnData.ok("");
    }


    /**
     * 查看报名
     *
     * @param name
     * @param training_institutions_id
     * @param class_name
     * @param train_type
     * @param phone
     * @param student_name
     * @return
     */
    @RequestMapping(value = "/getCurriculumOrderHasPay")
    @ResponseBody
    public RtnData getCurriculumOrderHasPay(String name, String training_institutions_id,
                                            String class_name, String train_type, String phone,
                                            String student_name, Integer pageNum, Integer pageSize) {

        Map<String, Object> args = Maps.newHashMap();
        args.put("name", name);
        args.put("training_institutions_id", training_institutions_id);
        args.put("class_name", class_name);
        args.put("train_type", train_type);
        args.put("phone", phone);
        args.put("student_name", student_name);
        args.put("pNum", (pageNum - 1) * pageSize);
        args.put("pSize", pageSize);
        try {
            List<Map<String, Object>> list = curiculumService.getCurriculumOrderHasPay(args);
            int total = curiculumService.getCurriculumOrderHasPayCount(args);
            Map<String, Object> res = Maps.newHashMap();
            res.put("list", list);
            res.put("total", total);
            return RtnData.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail("未知异常");
        }
    }

    @RequestMapping(value = "/signUpView.html")
    public String signUpView(ModelMap map) {
        List<CurriculumType> types = curiculumService.getCurriculumType();
        map.put("curiculumTypes", types);
        return "curriculum/sign_up_view";
    }
}

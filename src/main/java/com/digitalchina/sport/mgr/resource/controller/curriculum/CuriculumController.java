package com.digitalchina.sport.mgr.resource.controller.curriculum;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.StringUtil;
import com.digitalchina.common.utils.UUIDUtil;
import com.digitalchina.sport.mgr.resource.model.Curriculum;
import com.digitalchina.sport.mgr.resource.model.CurriculumClass;
import com.digitalchina.sport.mgr.resource.model.CurriculumClassTimes;
import com.digitalchina.sport.mgr.resource.model.CurriculumType;
import com.digitalchina.sport.mgr.resource.service.CuriculumService;
import com.digitalchina.sport.mgr.resource.service.TrainInstitutionService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @date 2017/2/27 14:06
 * @see
 */
//用于课程及班次的控制层
@Controller
@RequestMapping(value = "/curriculumController")
public class CuriculumController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuriculumController.class);

    @Autowired
    private CuriculumService curiculumService;
    @Autowired
    private TrainInstitutionService trainInstitutionService;

    /**
     * 课程列表界面
     * @param map
     * @param training_institutions_name
     * @param name
     * @param train_type
     * @param pageNum
     * @param pageSize
     * @return
     */
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
        map.put("curriculumTypes", types);
        map.put("totalPage", total % pageSize > 0 ? (total / pageSize) + 1 : total / pageSize);
        map.put("currentPage", pageNum);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        return "curriculum/curriculum";
    }

    /**
     * 课程上线下线
     * @param curriculumId
     * @param status
     * @return
     */
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

    /**
     * 课程设为推荐取消推荐
     * @param curriculumId
     * @param recommend
     * @return
     */
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

    /**
     *班次状态修改
     * @param map
     * @param id
     * @param status
     * @param curriculumId
     * @return
     */
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

    /**
     * 添加课程
     * @param map
     * @return
     */
    @RequestMapping(value = "/addCurriculum.html")
    public String addCurriculum(ModelMap map) {
        List<Map<String, Object>> list = null;
        try {
            list = trainInstitutionService.listTrainInstitution();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Map<String, Object> m1 = new HashMap<>();
        m1.put("value", "jg1");
        m1.put("text", "机构1");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("value", "jg2");
        m2.put("text", "机构2");
        list.add(m1);
        list.add(m2);*/
        List<CurriculumType> types = curiculumService.getCurriculumType();
        map.put("trainingInstitutions", list);
        map.put("curiculumTypes", types);
        return "curriculum/add_curriculum";
    }

    /**
     * 添加班次
     * @param curriculumId
     * @param map
     * @return
     */
    @RequestMapping(value = "/addCurriculumClass.html")
    public String addCurriculumClass(String curriculumId, ModelMap map) {
        map.put("curriculumId", curriculumId);
        return "curriculum/add_curriculum_class";
    }

    /**
     * 班次列表
     * @param map
     * @param curriculumId
     * @return
     */
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

    /**
     * 编辑课程页面
     * @param map
     * @param curriculumId
     * @return
     */
    @RequestMapping(value = "/editCurriculum.html")
    public String editCurriculum(ModelMap map, Integer curriculumId) {
        Curriculum curriculum = curiculumService.getCurriculumByKey(curriculumId);

        List<Map<String, Object>> list = new ArrayList<>();
        /*Map<String, Object> m1 = new HashMap<>();
        m1.put("value", "jg1");
        m1.put("text", "机构1");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("value", "jg2");
        m2.put("text", "机构2");
        list.add(m1);
        list.add(m2);*/
        try {
            list = trainInstitutionService.listTrainInstitution();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        map.put("curriculumTypes", types);
        map.put("trainingInstitutions", list);
        map.put("curriculum", curriculum);
        map.put("teacherList", teacherList);
        map.put("allTeacherList", allTeacherList);
        return "curriculum/edit_curriculum";
    }

    /**
     * 编辑班次页面
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit_curriculum_class.html")
    public String editCurriculumClass(ModelMap map, String id) {
        CurriculumClass curriculumClass = curiculumService.getCurriculumClassByKey(id);
        List<Map<String, Object>> classTimes = curiculumService.getClassTimes(id);
        map.put("classTimes", classTimes);
        map.put("curriculumClass", curriculumClass);
        return "curriculum/edit_curriculum_class";
    }

    /**
     * 续报设置页面
     * @param map
     * @param id
     * @param curriculumId
     * @return
     */
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

    /**
     * 续班设置获取已参加有课程
     * @param name
     * @param curriculumId
     * @param id
     * @return
     */
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

    /**
     * 添加课程
     * @param curriculum
     * @return
     */
    @RequestMapping(value = "/doAddCurriculum")
    @ResponseBody
    public RtnData doAddCurriculum(Curriculum curriculum) {
        System.out.println(curriculum);
        curiculumService.insertCurrriculum(curriculum);
        return RtnData.ok("");
    }

    /**
     * 续班保存
     * @param curriculumClass
     * @return
     */
    @RequestMapping(value = "/xubanCurriculum")
    @ResponseBody
    public RtnData xubanCurriculum(CurriculumClass curriculumClass) {
        curiculumService.updataCurrriculumClass(curriculumClass);
        return RtnData.ok("");
    }

    /**
     * 跟新课程
     * @param curriculum
     * @return
     */
    @RequestMapping(value = "/doUpdataCurriculum")
    @ResponseBody
    public RtnData doUpdataCurriculum(Curriculum curriculum) {
        System.out.println(curriculum);
        curiculumService.doUpdataCurriculum(curriculum);
        return RtnData.ok("");
    }

    /**
     * 跟新班次
     * @param curriculumClass
     * @param timess
     * @return
     */
    @RequestMapping(value = "/doUpdataCurriculumClass")
    @ResponseBody
    public RtnData doUpdataCurriculumClass(CurriculumClass curriculumClass, String timess) {
        System.out.println(curriculumClass);
        Gson gson = new Gson();
        List<Object> classTimes = gson.fromJson(timess, List.class);
        curiculumService.doUpdataCurriculumClass(curriculumClass, classTimes);
        return RtnData.ok("");
    }

    /**
     * 跟新班次时间段
     * @param id
     * @param time
     * @param max_people
     * @param reserve_people
     * @return
     */
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

    /**
     * 删除班次时间段
     * @param id
     * @return
     */
    @RequestMapping(value = "/delTimess")
    @ResponseBody
    public RtnData delTimess(String id) {
        return RtnData.ok(curiculumService.delTimess(id));
    }

    /**
     * 添加班次
     * @param curriculumClass
     * @param timess
     * @return
     */
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

    /**
     * 查看报名界面
     * @param map
     * @return
     */
    @RequestMapping(value = "/signUpView.html")
    public String signUpView(ModelMap map) {
        List<CurriculumType> types = curiculumService.getCurriculumType();
        map.put("curriculumTypes", types);
        return "curriculum/sign_up_view";
    }

    /**
     * 进入培训订单主页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/myTrainOrder.html")
    public String myTrainOrder(ModelMap map) {
        return "curriculum/myTrainOrder";
    }

    /**
     * 订单列表
     * @param pageSize
     * @param page
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderList.html")
    public String orderList(@RequestParam(required = false, defaultValue = "10") long pageSize,
                            @RequestParam(required = false, defaultValue = "1") long page,
                            @RequestParam(required = false) String status,
                            ModelMap map,HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        String checkAll = request.getParameter("checkAll");
        System.out.print(status);
        params.put("phone", request.getParameter("phone"));
        params.put("come", request.getParameter("come"));
        params.put("trainType", request.getParameter("trainType"));
        params.put("curriculumName", request.getParameter("curriculumName"));
        if (!StringUtil.isEmpty(request.getParameter("startDate")) && !StringUtil.isEmpty(request.getParameter("endDate"))){
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            startDate = startDate+" 00:00:00";
            endDate = endDate+" 23:59:59";
            params.put("startDate", startDate);
            params.put("endDate", endDate);
        }
        if(StringUtil.isEmpty(checkAll)){
            params.put("status", status);
        }else{
            params.put("checkAll", request.getParameter("checkAll"));
        }
        try {
            int totalSize = curiculumService.getCountByMap(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            params.put("startIndex", pagination.getStartIndex());
            params.put("pageSize", pageSize);
            List<Map<String,Object>> orderList = curiculumService.getOrderListByMap(params);
            pagination.setUrl(request.getRequestURI());
            map.put("pageModel", pagination);
            map.put("pageSize",String.valueOf(pageSize));
            map.put("page",String.valueOf(page));
            map.put("phone", request.getParameter("phone"));
            map.put("come", request.getParameter("come"));
            map.put("trainType", request.getParameter("trainType"));
            map.put("curriculumName", request.getParameter("curriculumName"));
            map.put("status", status);
            map.put("checkAll", request.getParameter("checkAll"));
            map.put("startDate", request.getParameter("startDate"));
            map.put("endDate", request.getParameter("endDate"));//回到页面,保留搜索关键字
            //培训类型
            List<Map<String,String>> trainTypeList = curiculumService.getCurriculumTypes();
            map.put("orderList",orderList);
            map.put("trainTypeList",trainTypeList);
            //（未支付超时订单根据失效时间逻辑判断）
            curiculumService.updateOrderByOrderTime();
            return "curriculum/myTrainOrder";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取培训订单失败");
            map.put("url",request.getRequestURI());
            map.put("exception",e);
            return "error";
        }
    }
    /**
     * 订单详情
     * @param orderId
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderDetails.html")
    public String orderDetails(@RequestParam(required = true) String orderId,
                               ModelMap map,HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId", orderId);
        try {
            map.put("orderDetails", curiculumService.getOrderDetailsByMap(params));
            //退款详情
            map.put("curriculumRefund",curiculumService.getCurriculumRefund(orderId));
            return "curriculum/trainOrderDetails";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取培训订单详情失败");
            map.put("url",request.getRequestURI());
            map.put("exception",e);
            return "error";
        }
    }
}

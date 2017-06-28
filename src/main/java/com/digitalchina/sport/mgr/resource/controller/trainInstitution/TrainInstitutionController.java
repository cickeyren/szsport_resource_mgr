package com.digitalchina.sport.mgr.resource.controller.trainInstitution;

import com.digitalchina.common.data.Constants;
import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.pagination.Page;
import com.digitalchina.common.pagination.PaginationUtils;
import com.digitalchina.common.utils.StringUtils;
import com.digitalchina.sport.mgr.resource.model.*;
import com.digitalchina.sport.mgr.resource.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xujin on 2017/6/8.
 */

//用于机构模块的控制层
@Controller
@RequestMapping(value = "/trainInstitutionController")
public class TrainInstitutionController {

    public static final Logger LOGGER = LoggerFactory.getLogger(TrainInstitutionController.class);

    @Autowired
    private TrainInstitutionService service;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CuriculumService curriculumService;

    @Autowired
    private TrainInstitutionDiscountValueService discountValueService;


    /**
     * 列表页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/list.html")
    public String list(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                    @RequestParam(required = false, defaultValue = "1") long page, ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String name = request.getParameter("name");
        params.put("name", name);//获取查询条件
        try {
            int totalSize = service.getTrainInstitutionListCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());

            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));
            map.put("name", name);//回到页面,保留搜索关键字

            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> list = service.getTrainInstitutionList(params);
            map.put("list", list);
            return "trainInstitution/list";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询培训机构数据失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 进入新增页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/add.html")
    public String add(ModelMap map) {
        //获取所有省份列表
        List<Map<String, Object>> provinceList = commonService.getAllProvince();

        //获取所有市区数据(北京市区列表)
        Map<String, Object> params = new HashMap<>();
        params.put("provinceID", "110000");
        List<Map<String, Object>> cityList = commonService.getAllCity(params);

        //获取所有县区数据（北京市辖区列表）
        params.put("cityID", "110100");
        List<Map<String, Object>> areaList = commonService.getAllArea(params);

        map.put("provinceList", provinceList);
        map.put("cityList", cityList);
        map.put("areaList", areaList);

        return "trainInstitution/add";//进入对应的页面
    }


    /**
     * 新增培训机构
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addTrainInstitution.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData add(TrainingInstitutionModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.addTrainInstitution(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("新增培训机构成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增培训机构失败=========", e);
        }
        return RtnData.fail("新增培训机构失败");
    }


    /**
     * 进入编辑页面
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String edit(@RequestParam String id, ModelMap map) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        try {
            TrainingInstitution trainInstitution = service.selectById(param);

            if(trainInstitution!=null){
                //获取所有省份列表
                List<Map<String, Object>> provinceList = commonService.getAllProvince();
                //获取所有市区数据
                param.put("provinceID",trainInstitution.getProvincial_level());
                List<Map<String, Object>> cityList = commonService.getAllCity(param);
                //获取所有县区数据
                param.put("cityID", trainInstitution.getCity_level());
                List<Map<String, Object>> areaList = commonService.getAllArea(param);
                map.put("provinceList", provinceList);
                map.put("cityList", cityList);
                map.put("areaList", areaList);

                map.put("trainInstitution", trainInstitution);
            }

            return "trainInstitution/edit";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========进入编辑页面失败=========", e);
            return "error";
        }

    }

    /**
     * 更新
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/updateTrainInstitution.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData update(TrainingInstitutionModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.updateTrainInstitution(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("修改培训机构成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========修改培训机构失败=========", e);
        }
        return RtnData.fail("修改培训机构失败");
    }

    /**
     * 进入机构图片
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/pic.html")
    public String pic(ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        String id = request.getParameter("id");
        params.put("id", id);
        map.put("id", id);
        List<Map<String, Object>> picList = service.getPicList(params);
        map.put("picList", picList);
        return "trainInstitution/pic";
    }

    /**
     * 新增机构图片
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addInstitutionPic.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addInstitutionPic(TrainingInstitutionPicModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.addInstitutionPic(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("保存机构图片成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========保存机构图片失败=========", e);
        }
        return RtnData.fail("保存机构图片失败");
    }

    /**
     * 机构图片设为首图
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/setPicIsFirst.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData setPicIsFirst(TrainingInstitutionPicModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.setPicIsFirst(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("设置机构图片为首图成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========设置机构图片为首图失败=========", e);
        }
        return RtnData.fail("设置机构图片为首图失败");
    }



    /**
     * 删除机构图片
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/delInstitutionPic.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delInstitutionPic(TrainingInstitutionPicModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.delInstitutionPic(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("删除机构图片成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========删除机构图片失败=========", e);
        }
        return RtnData.fail("删除机构图片失败");
    }

    /**
     * 进入窗口支付
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/window_payment_list.html")
    public String window_payment_list(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                      @RequestParam(required = false, defaultValue = "1") long page, ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        params.put("id", id);
        try {
            int totalSize = service.getWindowPaymentListCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());

            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));

            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> list = service.getWindowPaymentList(params);
            map.put("list", list);
            map.put("id", id);
            return "trainInstitution/window_payment_list";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询培训机构窗口支付列表失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 进入新增窗口支付页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/window_payment_add.html")
    public String window_payment_add(ModelMap map, HttpServletRequest request) {
        String id = request.getParameter("id");
        map.put("id", id);

        List<Map<String,String>> merchantList = merchantService.getMerchantListByInstitutionId(id);
        List<Map<String,String>> paymentList = paymentService.getAllPaymentList();

        map.put("merchantList", merchantList);
        map.put("paymentList", paymentList);

        return "trainInstitution/window_payment_add";
    }


    /**
     * 新增窗口支付页面
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addWindowPayment.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addWindowPayment(TrainingInstitutionWpModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.addWindowPayment(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("新增窗口支付成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增窗口支付失败=========", e);
        }
        return RtnData.fail("新增窗口支付失败");
    }


    /**
     * 进入编辑窗口支付页面
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/window_payment_edit.html", method = RequestMethod.GET)
    public String window_payment_edit(@RequestParam String id, @RequestParam String wpId, ModelMap map) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("wpId", wpId);
        try {
            TrainingInstitutionWp windowPayment = service.selectWindowPaymentById(param);

            if(windowPayment!=null){
                map.put("windowPayment", windowPayment);

                List<Map<String,String>> merchantList = merchantService.getMerchantListByInstitutionId(id);
                List<Map<String,String>> paymentList = paymentService.getAllPaymentList();
                map.put("merchantList", merchantList);
                map.put("paymentList", paymentList);
            }

            map.put("id", id);
            map.put("wpId", wpId);
            return "trainInstitution/window_payment_edit";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========进入编辑页面失败=========", e);
            return "error";
        }

    }

    /**
     * 更新
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/updateWindowPayment.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData updateWindowPayment(TrainingInstitutionWpModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.updateWindowPayment(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("修改窗口支付成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========修改窗口支付失败=========", e);
        }
        return RtnData.fail("修改窗口支付失败");
    }

    /**
     * 删除窗口支付
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/delWindowPayment.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delWindowPayment(TrainingInstitutionWpModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.delWindowPayment(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("删除窗口支付成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========删除窗口支付失败=========", e);
        }
        return RtnData.fail("删除窗口支付失败");
    }

    /**
     * 进入窗口培训折扣
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/window_discount_list.html")
    public String window_discount_list(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                      @RequestParam(required = false, defaultValue = "1") long page, ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        params.put("id", id);
        params.put("name", name);
        try {
            int totalSize = service.getWindowDiscountListCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());

            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));

            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> list = service.getWindowDiscountList(params);
            map.put("list", list);

            map.put("id", id);
            map.put("name", name);
            return "trainInstitution/window_discount_list";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========查询窗口培训折扣列表失败=========", e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 进入新增窗口培训页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/window_discount_add.html")
    public String window_discount_add(ModelMap map, HttpServletRequest request) {
        String id = request.getParameter("id");
        List<Map<String,Object>> curriculumList = curriculumService.getAvailCurriculumListByInstitutionId(id, null);
        List<Map<String,Object>> discountValueList = discountValueService.getAllDiscountValueList();
        map.put("curriculumList", curriculumList);
        map.put("discountValueList", discountValueList);
        map.put("id", id);
        return "trainInstitution/window_discount_add";
    }


    /**
     * 新增窗口折扣
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addWindowDiscount.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addWindowDiscount(CurriculumDiscountModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.addWindowDiscount(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("新增窗口折扣成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增窗口折扣失败=========", e);
        }
        return RtnData.fail("新增窗口折扣失败");
    }



    /**
     * 进入编辑窗口支付页面
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/window_discount_edit.html", method = RequestMethod.GET)
    public String window_discount_edit(@RequestParam String id, @RequestParam Integer curriculum_id, ModelMap map) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("curriculum_id", curriculum_id);
        try {
            List<Map<String,Object>> curriculumList = curriculumService.getAvailCurriculumListByInstitutionId(id, curriculum_id);
            List<Map<String,Object>> discountValueList = discountValueService.getAllDiscountValueListWithChecked(curriculum_id);
            map.put("curriculumList", curriculumList);
            map.put("discountValueList", discountValueList);

            map.put("id", id);
            map.put("curriculum_id", curriculum_id);
            return "trainInstitution/window_discount_edit";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========进入编辑页面失败=========", e);
            return "error";
        }
    }

    /**
     * 更新窗口折扣
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/updateWindowDiscount.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData updateWindowDiscount(CurriculumDiscountModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.updateWindowDiscount(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("更新窗口折扣成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========更新窗口折扣失败=========", e);
        }
        return RtnData.fail("更新窗口折扣失败");
    }

    /**
     * 更新窗口折扣状态
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/updateDiscountStatus.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData updateDiscountStatus(CurriculumModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.updateDiscountStatus(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("更新窗口折扣状态成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========更新窗口折扣状态失败=========", e);
        }
        return RtnData.fail("更新窗口折扣状态失败");
    }


    /**
     * 进入合作商户列表页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/merchant_list.html")
    public String merchant_list(@RequestParam(required = false, defaultValue = "10") long pageSize,
                                @RequestParam(required = false, defaultValue = "1") long page,
                                ModelMap map, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        params.put("id", id);
        try {
            int totalSize = service.getMerchantListCount(params);
            Page pagination = PaginationUtils.getPageParam(totalSize, pageSize, page); //计算出分页查询时需要使用的索引
            pagination.setUrl(request.getRequestURI());

            map.put("pageModel", pagination);
            map.put("pageSize", String.valueOf(pageSize));
            map.put("page", String.valueOf(page));

            params.put("startIndex", pagination.getStartIndex());
            params.put("endIndex", pageSize);
            List<Map<String, Object>> list = service.getMerchantList(params);
            map.put("list", list);

            map.put("id", id);
            return "trainInstitution/merchant_list";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========进入合作商户账户列表页面失败=========",e);
            map.put("url", request.getRequestURL());
            map.put("exception", e);
            return "error";
        }
    }

    /**
     * 删除培训机构-合作商户绑定
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/delInstitutionMerchant.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delInstitutionMerchant(OrganRelevantMerchantModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.delInstitutionMerchant(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("删除合作商户成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========删除删除培训机构-合作商户绑定失败=========", e);
        }
        return RtnData.fail("删除合作商户失败");
    }

    /**
     * 进入新增合作商户页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/merchant_add.html")
    public String merchant_add(ModelMap map, HttpServletRequest request) {
        String id = request.getParameter("id");
        map.put("id", id);

        List<Map<String,String>> merchantList = merchantService.getAllMerchantList();

        map.put("merchantList", merchantList);

        return "trainInstitution/merchant_add";
    }


    /**
     * 新增培训机构合作商户绑定
     *
     * @param
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/addInstitutionMerchant.do", method = RequestMethod.POST)
    @ResponseBody
    public RtnData addInstitutionMerchant(OrganRelevantMerchantModel model, ModelMap map) {
        try {

            Map<String,Object> resMap = service.addInstitutionMerchant(model);

            if(Constants.RTN_CODE_SUCCESS.equals(resMap.get(Constants.RTN_CODE))){
                return RtnData.ok("新增合作商户成功");
            }else{
                return RtnData.fail("999999", (String) resMap.get(Constants.RTN_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("========新增合作商户失败=========", e);
        }
        return RtnData.fail("新增合作商户失败");
    }

}

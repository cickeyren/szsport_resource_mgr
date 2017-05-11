package com.digitalchina.sport.mgr.resource.controller.upload;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.utils.StringUtil;
import com.digitalchina.config.Config;
import com.digitalchina.sport.mgr.resource.service.StadiumPicService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * （一句话描述）
 * 作用：
 *
 * @author 王功文
 * @date 2017/3/31 16:29
 * @see
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private static final Logger logger = Logger.getLogger(UploadController.class);
    @Autowired
    Config config;

    public String uploadType_file = "1"; // 上传文件类型 文件型

    public String uploadType_image = "0"; // 上传文件类型 图片型

    @Autowired
    private StadiumPicService stadiumPicService;


    /**
     * 进入新增页面
     *
     * @param map
     *
     * @return
     */
    @RequestMapping(value = "/img.html")
    public String add(ModelMap map,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        String mainstadium_id = request.getParameter("mainstadium_id");
        params.put("stadiumId", mainstadium_id);
        map.put("mainstadium_id", mainstadium_id);
        List<Map<String, Object>> picList = new ArrayList<Map<String, Object>>();
        try {
            picList = stadiumPicService.getMainStadiumPicList(params);
        } catch (Exception e){
        }
        map.put("picList", picList);
        return "img/imgall";//进入对应的页面
    }

//    /**
//     * 上传文件
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/uploadFile")
//    public RtnData uploadFile(HttpServletRequest request, HttpServletResponse response) {
//
//        //获取文件处理
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        //判断当前的请求类型是否符合文件传输类型
//        if (multipartResolver.isMultipart(request)) {
//            //如果是的话，则转换request对象，这个对象里面包含了从前台传出的文件的具体数据信息
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            Iterator<String> iterator = multiRequest.getFileNames();
//            while (iterator.hasNext()) {
//                MultipartFile file = multiRequest.getFile(iterator.next());//前台传出的文件数据集合
//                if (file != null) {
//                    try {
//                        byte[] byteArr = file.getBytes();//文件字节码
//                        String fileName = file.getName();//文件名称
//
//                        String filePath = "E:\\WorkDocument";
//                        createFile(byteArr, filePath, fileName);
//
//                        System.out.println("文件:" + fileName + "的字节码长度为:" + byteArr.length);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        return  RtnData.fail("文件上传失败");
//                    }
//                }
//            }
//        }
//        return RtnData.ok("文件上传成功");
//    }
//
//
//    /**
//     * 根据byte数组，生成文件
//     */
//    public static void createFile(byte[] bfile, String filePath, String fileName) {
//        BufferedOutputStream bos = null;
//        FileOutputStream fos = null;
//        File file = null;
//        try {
//            File dir = new File(filePath);
//            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
//                dir.mkdirs();
//            }
//            file = new File(filePath + "\\" + fileName);
//            fos = new FileOutputStream(file);
//            bos = new BufferedOutputStream(fos);
//            bos.write(bfile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 文件上传
//     * @param request
//     * @param response
//     * @return
//     */
//    public RtnData uploadContractTempFiles(HttpServletRequest request, HttpServletResponse response) {
//        //存储文件中的标记位
//        List<String> docFlags = new ArrayList<>();
//
//        //取出额外的两个参数：合同名称、合同类型
//        Map<String, String[]> requestMap = request.getParameterMap();
//        String TEMPLATE_NAME = requestMap.get("TEMPLATE_NAME")[0];
//        String TEMPLATE_TYPE = requestMap.get("TEMPLATE_TYPE")[0];
//        //实际名称
//        String fileName = "";
//        //生成的UUID名称
//        String uuidFileName = "";
//        //模版大小
//        int fileSize = 0;
//        //模版路径
//        String filePath = "";
//        //文件扩展名
//        String fileExt = "";
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        if (multipartResolver.isMultipart(request)) {
//            //如果是的话，则转换request对象，这个对象里面包含了从前台传出的文件的具体数据信息
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            Iterator<String> iterator = multiRequest.getFileNames();
//            while (iterator.hasNext()) {
//                MultipartFile file = multiRequest.getFile(iterator.next());//前台传出的文件数据集合
//                if (file != null) {
//                    try {
//                        byte[] byteArr = file.getBytes();//文件字节码
//                        String fileName_old = fileName = file.getOriginalFilename();//上传时候的文件名称
//                        String extName = fileExt = fileName_old.substring(fileName_old.lastIndexOf(".") + 1);//扩展名
//                        fileSize = byteArr.length;
//                        //现在开始先存储word文档，然后再从磁盘读取文件进行分析
//                        //先构建随机的文件名称，存储到临时文件夹
//                        uuidFileName = UUID.randomUUID().toString().concat(".").concat(extName);
//                        //存储文件夹路径
//                        filePath = request.getSession().getServletContext().getRealPath("/").concat("E:\\WorkDocument");
//                        //然后存储到临时文件夹路径
//                        createFile(byteArr, filePath, uuidFileName);
//                        //存储完毕之后，根据路径和名称，进行POI读取，根据doc/docx进行不同的设计
//                        InputStream inputStream = new FileInputStream(new File(filePath.concat("\\").concat(uuidFileName)));
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        return RtnData.fail("文件上传失败");
//                    }
//                }
//            }
//        }
//        return  RtnData.ok("文件上传成功");
//    }



        /**
         * 上传图片
         *
         * @throws Exception
         */
    @RequestMapping(value = "/imageUpload.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData imageUpload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) throws Exception {
        return doUpload(file, request, uploadType_image);
    }

    /**
     * 上传文件
     *
     * @throws Exception
     */
    @RequestMapping("/fileUpload")
    public RtnData fileUpload(@RequestParam(required = true) MultipartFile file,
                              HttpServletRequest request) throws Exception {
        return doUpload(file, request, uploadType_file);
    }

    public RtnData doUpload(MultipartFile file,
                            HttpServletRequest request, String uploadType) {
        String datePath = getDatePath();
        String parentPath = config.uploadPath;
        String project_context_path = config.projectContextPath;
        String secondePath = "";
        if (uploadType_image.equals(uploadType)) {
            // 上传图片
            secondePath = config.uploadImageFolder;
        } else {
            // 上传文件
            secondePath = config.uploadFileFolder;
        }
        String path = parentPath + secondePath + datePath;
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 校验文件类型
        if (!checkPrefix(prefix, uploadType)) {
            return RtnData.fail("","上传的文件类型有误");
        }
        // 加入时间戳保证文件名唯一
        String uniquefileName = UUID.randomUUID() + "_" + new Date().getTime() + "."
                + prefix;
        // 相对路径
        String returnPath = project_context_path + secondePath + datePath;
        String returnUrl = returnPath + uniquefileName;
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File targetFile = new File(path, uniquefileName);
        return this.uploadFile(file, targetFile, returnUrl, request);
    }

    public RtnData uploadFile(MultipartFile file, File targetFile, String returnUrl, HttpServletRequest request) {
        // 保存至项目文件夹
        try {
            //file.transferTo(targetFile);
            FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtil.isNotEmpty(returnUrl)) {
            //数据库添加图片信息
            String photoDownloadPath = config.photoDownloadPath;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("picAddress", photoDownloadPath + returnUrl);
            paramMap.put("stadiumId", request.getParameter("mainstadium_id"));
            if(stadiumPicService.addMainStaiumPic(paramMap) > 0){
                return RtnData.ok(returnUrl, "上传成功");
            } else {
                return RtnData.fail("上传失败","");
            }
        } else {
            return RtnData.fail("上传失败","");
        }
    }

    /**
     * 校验文件后缀名 type 0:图片后缀 1：文件后缀
     */
    public boolean checkPrefix(String prefix, String type) {
        if ("0".equals(type)) {
            return UploadFileEnum.ImgEnum.isApproved(prefix);
        } else {
            // 可上传的文件后缀
            return true;
        }
    }

    /**
     * 生成文件保存路径
     */
    public String getDatePath() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
        String time = format.format(date);
        return time;
    }

    /**
     * 场馆图片设为首图
     * @param id
     * @return
     */
    @RequestMapping(value = "/setPicIsFirst.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData setPicIsFirst(String id, String stadiumId){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("stadiumId", stadiumId);
        try {
            stadiumPicService.setPicIsFirst(paramMap);
            return RtnData.ok("场馆图片设为首图成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========场馆图片设为首图失败=========",e);
        }
        return RtnData.fail("场馆图片设为首图失败");
    }

    /**
     * 删除场馆图片
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMainStadiumPic.json", method = RequestMethod.POST)
    @ResponseBody
    public RtnData delMainStadiumPic(String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        try {
            stadiumPicService.delMainStadiumPic(paramMap);
            return RtnData.ok("删除场馆图片成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("========删除场馆图片失败=========",e);
        }
        return RtnData.fail("删除场馆图片失败");
    }
}

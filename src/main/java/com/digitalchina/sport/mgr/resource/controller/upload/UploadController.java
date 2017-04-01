package com.digitalchina.sport.mgr.resource.controller.upload;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.common.utils.StringUtil;
import com.digitalchina.config.Config;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
        map.put("mainstadium_id", mainstadium_id);
        return "img/imgall";//进入对应的页面
    }



    /**
     * 上传图片
     *
     * @throws Exception
     */
    @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
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
        return this.uploadFile(file, targetFile, returnUrl);
    }

    public RtnData uploadFile(MultipartFile file, File targetFile, String returnUrl) {
        // 保存至项目文件夹
        try {
            //file.transferTo(targetFile);
            FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtil.isNotEmpty(returnUrl)) {
            return RtnData.ok(returnUrl, "上传成功");
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
}

package com.digitalchina.sport.mgr.resource.controller.upload;

import com.digitalchina.common.data.RtnData;
import com.digitalchina.config.Config;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yang_ on 2017/5/16.
 */
@Controller
@RequestMapping(value = "/uploadImg")
public class UploadImgController {

    @Autowired
    Config config;

    @RequestMapping(value = "/uploadImg.json", method = RequestMethod.POST, consumes = "multipart/form-data", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RtnData addBasic(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("previewImg");
        String datePath = getDatePath();
        String parentPath = config.uploadPath;
        String project_context_path = config.projectContextPath;
        String secondePath = "";

        secondePath = config.uploadImageFolder;

        String path = parentPath + secondePath + datePath;
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 加入时间戳保证文件名唯一
        String uniquefileName = UUID.randomUUID()+ "."
                + prefix;
        // 相对路径
        String returnPath = project_context_path + secondePath + datePath;
        String returnUrl = returnPath + uniquefileName;
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File targetFile = new File(path, uniquefileName);
        // 保存至项目文件夹
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String photoDownloadPath = config.photoDownloadPath;
        return RtnData.ok(photoDownloadPath+returnUrl);
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

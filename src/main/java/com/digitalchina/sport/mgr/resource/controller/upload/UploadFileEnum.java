package com.digitalchina.sport.mgr.resource.controller.upload;

import java.util.EnumSet;

public class UploadFileEnum {

    public static enum ImgEnum {// 图片格式
        bmp, jpg, gif, png, jpeg;

        public static boolean isApproved(String imgType) {
            imgType = imgType.toLowerCase().trim();
            boolean result = false;
            EnumSet<ImgEnum> imageSet = EnumSet.allOf(ImgEnum.class);
            for (ImgEnum img : imageSet) {
                if (img.name().equals(imgType)) {
                    result = true;
                    break;
                }
            }
            return result;
        }
    }

    public static enum ResumeEnum {// 文件格式

    }

}
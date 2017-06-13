package com.digitalchina.sport.mgr.resource.model;

/**
 * （一句话描述）
 * 作用：
 *
 * @author xujin
 * @date 2017/6/8 17:25
 * @see
 */
public class TrainingInstitutionPicModel {

    private String id;

    private String pic_url;

    private String is_first;

    private String institution_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getIs_first() {
        return is_first;
    }

    public void setIs_first(String is_first) {
        this.is_first = is_first;
    }

    public String getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(String institution_id) {
        this.institution_id = institution_id;
    }
}

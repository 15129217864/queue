package com.xct.media.queuing.pojo;

/**
 * Created by Chris on 2018/5/20.
 */
public class JobUserPojo {

    private int job_user_id;
    private String job_user_name;

    //0-男，1-女
    private String job_user_sex;
    private String job_user_num;
    private String job_user_photo;

    // 业务数据
    private int base_id;
    private String base_biz;

    //0-办理业务、1-业务员
    private String biz_screen_mark;
    private String screen_ip;


    public int getJob_user_id() {
        return job_user_id;
    }

    public void setJob_user_id(int job_user_id) {
        this.job_user_id = job_user_id;
    }

    public String getJob_user_name() {
        return job_user_name;
    }

    public void setJob_user_name(String job_user_name) {
        this.job_user_name = job_user_name;
    }

    public String getJob_user_sex() {
        return job_user_sex;
    }

    public void setJob_user_sex(String job_user_sex) {
        String sex = "";
        switch (job_user_sex) {
            case "0":
                sex = "男";
                break;
            case "1":
                sex = "女";
                break;
            default:
                // don't nothing ....
        }
        this.job_user_sex = sex;
    }

    public String getJob_user_num() {
        return job_user_num;
    }

    public void setJob_user_num(String job_user_num) {
        this.job_user_num = job_user_num;
    }

    public String getJob_user_photo() {
        return job_user_photo;
    }

    public void setJob_user_photo(String job_user_photo) {
        this.job_user_photo = job_user_photo;
    }

    public String getBase_biz() {
        return base_biz;
    }

    public void setBase_biz(String base_biz) {
        this.base_biz = base_biz;
    }

    public int getBase_id() {
        return base_id;
    }

    public void setBase_id(int base_id) {
        this.base_id = base_id;
    }

    public String getBiz_screen_mark() {
        return biz_screen_mark;
    }

    public void setBiz_screen_mark(String biz_screen_mark) {
        this.biz_screen_mark = biz_screen_mark;
    }

    public String getScreen_ip() {
        return screen_ip;
    }

    public void setScreen_ip(String screen_ip) {
        this.screen_ip = screen_ip;
    }
}

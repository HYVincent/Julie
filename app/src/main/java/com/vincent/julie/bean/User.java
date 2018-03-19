package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.bean
 * @class describe 用户信息
 * @date 2018/2/7 0:33
 */

public class User {


    /**
     * user_account : 0
     * user_birthday : 1970-01-01
     * user_create_time : 2018-03-14 00:18:06.0
     * user_height : 0
     * user_id : 10000
     * user_name : name
     * user_password : ********
     * user_phone : 18696855784
     * user_sex : 男
     * user_weight : 0
     */

    private int user_account;
    private String user_birthday;
    private String user_create_time;
    private int user_height;
    private int user_id;
    private String user_name;
    private String user_phone;
    private String user_sex;
    private int user_weight;
    private String api_token;

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public int getUser_account() {
        return user_account;
    }

    public void setUser_account(int user_account) {
        this.user_account = user_account;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_create_time() {
        return user_create_time;
    }

    public void setUser_create_time(String user_create_time) {
        this.user_create_time = user_create_time;
    }

    public int getUser_height() {
        return user_height;
    }

    public void setUser_height(int user_height) {
        this.user_height = user_height;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_weight() {
        return user_weight;
    }

    public void setUser_weight(int user_weight) {
        this.user_weight = user_weight;
    }
}

package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.bean
 * @class describe
 * @date 2018/3/18 23:58
 */

public class VersionBean {

    private int version_id;
    private int version_code;
    private String version_codes;
    private String version_desc;
    private String version_file_path;
    private long version_time;
    public int getVersion_id() {
        return version_id;
    }
    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }
    public int getVersion_code() {
        return version_code;
    }
    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }
    public String getVersion_codes() {
        return version_codes;
    }
    public void setVersion_codes(String version_codes) {
        this.version_codes = version_codes;
    }
    public String getVersion_desc() {
        return version_desc;
    }
    public void setVersion_desc(String version_desc) {
        this.version_desc = version_desc;
    }
    public String getVersion_file_path() {
        return version_file_path;
    }
    public void setVersion_file_path(String version_file_path) {
        this.version_file_path = version_file_path;
    }
    public long getVersion_time() {
        return version_time;
    }
    public void setVersion_time(long version_time) {
        this.version_time = version_time;
    }

}

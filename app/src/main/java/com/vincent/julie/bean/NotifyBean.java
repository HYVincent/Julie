package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.bean
 * @class describe
 * @date 2018/3/24 16:59
 */

public class NotifyBean  {

    /**
     * 通知title
     */
    private String Title;
    /**
     * 通知内容
     */
    private String content;
    /**
     * 通知类型  0  物业通知  1 天气状况  2 紧急号码 设置
     */
    private int type;
    /**
     * 通知等级 1 正常 2 警告 3 危险
     */
    private int level;

    //时间戳
    private int time;
    //状态  0 未读 1 已读
    private int status;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

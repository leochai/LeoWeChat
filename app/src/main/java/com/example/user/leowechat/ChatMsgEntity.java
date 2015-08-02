package com.example.user.leowechat;

/**
 * Created by user on 2015/7/24.
 */
public class ChatMsgEntity {
    private String name;//名字
    private String date;//时间
    private String message;//消息
    private boolean isComMeg = true;//是否收到的消息

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getMsgType() {
        return isComMeg;
    }

    public void setMsgType(boolean isComMsg) {
        this.isComMeg = isComMsg;
    }

    public ChatMsgEntity() {
    }

    public ChatMsgEntity(String name, String date, String text, boolean isComMsg) {
        super();
        this.name = name;
        this.date = date;
        this.message = text;
        this.isComMeg = isComMsg;
    }

}


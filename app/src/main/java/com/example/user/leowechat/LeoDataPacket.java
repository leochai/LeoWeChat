package com.example.user.leowechat;

import java.net.InetAddress;

/**
 * Created by Chai on 2015/8/9.
 */
public class LeoDataPacket {
    private InetAddress myIP;
    private String strConversation;

    public String getConversation() {
        return strConversation;
    }

    public InetAddress getMyIP() {
        return myIP;
    }

    public int getLength() {
        int i = 0;
        try {
            i = strConversation.getBytes("utf-8").length;
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

    public LeoDataPacket(InetAddress myIP, String strConversation) {
        super();
        this.myIP = myIP;
        this.strConversation = strConversation;
    }
}

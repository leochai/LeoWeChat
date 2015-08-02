package com.example.user.leowechat;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * Created by user on 2015/7/29.
 */
public class udpSend extends Thread {

    Handler handler;
    DatagramSocket ds;


    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    try {
                        InetAddress ip = InetAddress.getByName("192.168.1.103");
                        ds = new DatagramSocket(30000, InetAddress.getByName("192.168.1.102"));
                        DatagramPacket dp = new DatagramPacket(msg.obj.toString().getBytes(), msg.obj.toString().length(), ip, 30001);
                        ds.send(dp);
                        ds.close();
                    } catch (Exception e) {
                        ds.close();
                    }
                }
            }
        };
        Looper.loop();
    }
}
package com.example.user.leowechat;

import android.os.Handler;
import android.os.Message;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by user on 2015/7/30.
 */
public class udpListen extends Thread {

    Handler uiHandler;
    byte[] data = new byte[2048];
    DatagramSocket ds;

    public udpListen(Handler handler) {
        uiHandler = handler;
    }

    public void run() {
        try {
            ds = new DatagramSocket(30001);
            DatagramPacket dp = new DatagramPacket(data, 2048);

            while (true) {
                ds.receive(dp);
                Message msg = new Message();

                msg.what = 0x222;

                msg.obj = new String(data);
                uiHandler.sendMessage(msg);

            }
        } catch (Exception e) {
            ds.close();
        }
    }
}

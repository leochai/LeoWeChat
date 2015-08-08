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

    public static int DATALEN = 2048;
    Handler uiHandler;
    byte[] data = new byte[DATALEN];
    DatagramSocket ds;

    public udpListen(Handler handler) {
        uiHandler = handler;
    }

    public void run() {
        try {
            ds = new DatagramSocket(30001);
            DatagramPacket dp = new DatagramPacket(data, DATALEN);

            while (true) {
                ds.receive(dp);
                Message msg = new Message();

                msg.what = 0x222;

                msg.obj = new String(data, "gbk");
                uiHandler.sendMessage(msg);

                for (int i = 0; i < DATALEN; i++) {
                    data[i] = 0;
                }

            }
        } catch (Exception e) {
            ds.close();
        }
    }
}

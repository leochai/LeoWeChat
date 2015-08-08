package com.example.user.leowechat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LeoWeChat extends AppCompatActivity {

    ListView lstShow;
    EditText edtInput;
    //聊天内容的适配器
    private ChatMsgEntityAdapter mAdapter;
    //聊天的内容
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();

    udpSend udpsend;
    udpListen listen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leo_we_chat);

        lstShow = (ListView) findViewById(R.id.lstShow);
        edtInput = (EditText) findViewById(R.id.edtInput);
        //initData();

        mAdapter = new ChatMsgEntityAdapter(this, mDataArrays);
        lstShow.setAdapter(mAdapter);

        Handler uiHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x222) {
                    String strCome = msg.obj.toString();

                    ChatMsgEntity entity = new ChatMsgEntity();
                    entity.setDate(getDate());
                    entity.setName("小欣欣");
                    entity.setMsgType(true);
                    entity.setMessage(strCome);

                    updateList(entity);
                }
            }
        };

        udpsend = new udpSend();
        new Thread(udpsend).start();
        listen = new udpListen(uiHandler);
        new Thread(listen).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leo_we_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        String strInput = edtInput.getText().toString();
        if (strInput.length() > 0) {

            Message msg = new Message();
            msg.what = 0x111;
            msg.obj = strInput;
            udpsend.handler.sendMessage(msg);

            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(getDate());
            entity.setName("我");
            entity.setMsgType(false);
            entity.setMessage(strInput);

            updateList(entity);
            edtInput.setText("");
        }
    }

    private String getDate() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String min = String.valueOf(c.get(Calendar.MINUTE));
        return (year + "-" + month + "-" + day + " " + hour + ":" + min);
    }

    private void updateList(ChatMsgEntity entity) {

        mDataArrays.add(entity);
        mAdapter.notifyDataSetChanged();

        lstShow.setSelection(lstShow.getCount() - 1);
    }
}



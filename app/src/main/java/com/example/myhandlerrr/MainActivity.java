package com.example.myhandlerrr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private Button btn;
    private TextView textView;
    private MyHandler myHandler;
    private MyTrhead myTrhead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.trheadbtn);
        textView = (TextView)findViewById(R.id.thradtext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTrhead = new MyTrhead();
                myTrhead.start();
            }
        });

        myHandler = new MyHandler();

    }

    class MyTrhead extends Thread{

        @Override
        public void run(){

            for(int i = 40; i<100; i++){

                Message message = myHandler.obtainMessage();
                message.what= i;
                myHandler.sendMessage(message);

                try{
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg.what<50){
                textView.setText("값은:: " + msg.what);
            }
            else if(50<=msg.what)
                textView.setText("위험 값이 :: "+msg.what);

        }
    }
}
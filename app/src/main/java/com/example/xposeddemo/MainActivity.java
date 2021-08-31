package com.example.xposeddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    TextView showMsg;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMsg=findViewById(R.id.show_msg);
        TextView tv=findViewById(R.id.change);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg.setText(getS()+"");
            }
        });
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String changeMsg() {
        s="1";
        Log.v("tag",""+s);
//        showMsg.setText(s);
        return s;
    }

}
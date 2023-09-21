package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
Button start,rule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        start=findViewById(R.id.enter);
        rule=findViewById(R.id.button2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity2.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }
    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("說明");  //設置標題
        builder.setIcon(R.drawable.player); //標題前面那個小圖示
        builder.setMessage("1.玩家選擇想出的拳並按下確定鈕\n2.猜贏時，玩家可以擲骰子以骰子點數乘以五倍為攻擊力去攻擊boss\n3.平手時，玩家重新再選擇出的拳\n4.猜輸時，玩家血量減少1點(輸的機率較大)\n5.此遊戲總有10關，每過一關boss的血量都會增加5滴");//提示訊息


        //確定 取消 一般 這三種按鈕就看你怎麼發揮你想置入的功能囉
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {
    Button over,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        over=findViewById(R.id.end2);
        over.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    finish();
                }
        });
        back=findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity4.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
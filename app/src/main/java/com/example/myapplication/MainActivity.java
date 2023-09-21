package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.MediaPlayer;
import java.util.Collections;
import java.util.Random;
import android.os.Handler;
import android.graphics.drawable.AnimationDrawable;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    int []srp = {R.drawable.scissors, R.drawable.rock, R.drawable.paper};
    int []dice= {R.drawable.no1,R.drawable.no2,R.drawable.no3,R.drawable.no4,R.drawable.no5,R.drawable.no6,};
    int []back={R.drawable.vs,R.drawable.vs2,R.drawable.vs3,R.drawable.vs4,R.drawable.vs5};
    ImageView enemycard,player,boss,background;
    ImageButton diceBtn,rockBtn,paperBtn,scissorsBtn;
    Button play;
    private MediaPlayer music;
    TextView playercount,bosscount,levelcount;
    int srpplayer,dicepoint,srppoint,bossheart=5,playerheart=25,bossheartbackup=5,level=0;
    int []bossface={R.drawable.level1, R.drawable.level2, R.drawable.level3,R.drawable.level4, R.drawable.level5, R.drawable.level6,R.drawable.level7,R.drawable.level8,R.drawable.level9,R.drawable.level10};
    int []hurtanime={R.drawable.boss1_hurt,R.drawable.boss2_hurt,R.drawable.boss3_hurt,R.drawable.boss4_hurt,R.drawable.boss5_hurt,R.drawable.boss6_hurt,R.drawable.boss7_hurt,R.drawable.boss8_hurt,R.drawable.boss9_hurt,R.drawable.boss10_hurt,};
    Handler handler=new Handler();
    Handler handler1=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = new MediaPlayer();
        try {
            music = MediaPlayer.create(this, R.raw.bgm);
            music.setOnCompletionListener(listener);
            music.setVolume(0.5f,0.5f);
            music.prepare();
        } catch (Exception ex) {
            Log.d("音樂問題", "onStart():"+ex.getMessage());
        }
        music.start();
        diceBtn = findViewById(R.id.dice);
        rockBtn = findViewById(R.id.rock);
        paperBtn = findViewById(R.id.paper);
        scissorsBtn = findViewById(R.id.scissors);
        boss=findViewById(R.id.boss);
        player=findViewById(R.id.player);
        play=findViewById(R.id.confirm);
        enemycard= findViewById(R.id.enemy);
        background=findViewById(R.id.background);
        playercount=(TextView)findViewById(R.id.playerheart);
        playercount.setText("玩家血量:"+Integer.toString(playerheart));
        bosscount=(TextView)findViewById(R.id.bossheart);
        bosscount.setText("敵人血量:" +Integer.toString(bossheart));
        levelcount=(TextView)findViewById(R.id.level);
        levelcount.setText("level"+Integer.toString(level+1));
        diceBtn.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
        scissorsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               srpplayer=0;
               scissorsBtn.setImageResource(R.drawable.scissors_pick);//被選過
               rockBtn.setImageResource(R.drawable.rock);
               paperBtn.setImageResource(R.drawable.paper);
               play.setVisibility(View.VISIBLE);
            }
        });
        rockBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                srpplayer=1;
                rockBtn.setImageResource(R.drawable.rock_pick);//被選過
                scissorsBtn.setImageResource(R.drawable.scissors);
                paperBtn.setImageResource(R.drawable.paper);
                play.setVisibility(View.VISIBLE);
            }
        });
        paperBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                srpplayer=2;
                paperBtn.setImageResource(R.drawable.paper_pick);//被選過
                rockBtn.setImageResource(R.drawable.rock);//保持原樣
                scissorsBtn.setImageResource(R.drawable.scissors);
                play.setVisibility(View.VISIBLE);
            }
        });
        play.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               enemycard.setImageResource(R.drawable.randomsrp);
               AnimationDrawable a=(AnimationDrawable)enemycard.getDrawable();
               a.start();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Random srp1 = new Random();
                       srppoint = srp1.nextInt(3);
                       enemycard.setImageResource(srp[srppoint]);
                       if (srpplayer == srppoint) {
                           Toast.makeText(getApplicationContext(), "再猜一次", Toast.LENGTH_SHORT).show();
                           paperBtn.setImageResource(R.drawable.paper);
                           rockBtn.setImageResource(R.drawable.rock);
                           scissorsBtn.setImageResource(R.drawable.scissors);
                           play.setVisibility(View.INVISIBLE);
                       } else if ((srpplayer == 0 && srppoint == 2) || (srpplayer == 1 && srppoint == 0) || (srpplayer == 2 && srppoint == 1)) {
                           Toast.makeText(getApplicationContext(), "擲骰子攻擊敵人", Toast.LENGTH_SHORT).show();
                           diceBtn.setVisibility(View.VISIBLE);
                           play.setVisibility(View.INVISIBLE);
                           paperBtn.setImageResource(R.drawable.paper);
                           rockBtn.setImageResource(R.drawable.rock);
                           scissorsBtn.setImageResource(R.drawable.scissors);
                           paperBtn.setEnabled(false);
                           rockBtn.setEnabled(false);
                           scissorsBtn.setEnabled(false);
                       } else {
                           play.setVisibility(View.INVISIBLE);
                           paperBtn.setImageResource(R.drawable.paper);
                           rockBtn.setImageResource(R.drawable.rock);
                           scissorsBtn.setImageResource(R.drawable.scissors);
                           playerheart--;
                           player.setImageResource(R.drawable.playerhurt);
                           AnimationDrawable a=(AnimationDrawable)player.getDrawable();
                           a.start();
                           playercount.setText("玩家血量:"+Integer.toString(playerheart));
                           if (playerheart==0){
                               //雄大圖
                               handler1.postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       Intent intent = new Intent();
                                       intent.setClass(MainActivity.this,MainActivity4.class);
                                       startActivity(intent);
                                       finish();
                                       //傳到主畫面
                                   }
                               },2000);
                           }
                       }
                   }
               },900);
           }
        });
        diceBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                diceBtn.setImageResource(R.drawable.randomdice);
                AnimationDrawable a=(AnimationDrawable)diceBtn.getDrawable();
                a.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random dices =new Random();
                        dicepoint=dices.nextInt(6);
                        diceBtn.setImageResource(dice[dicepoint]);
                        player.setImageResource(R.drawable.playerattack);
                        AnimationDrawable a=(AnimationDrawable)player.getDrawable();
                        a.start();
                        boss.setImageResource(hurtanime[level]);
                        AnimationDrawable b=(AnimationDrawable)boss.getDrawable();
                        b.start();
                        bossheart=bossheart-(dicepoint+1)*5;
                        bosscount.setText("敵人血量:"+Integer.toString(bossheart));
                        if(bossheart<=0&&level==9){
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this,MainActivity3.class);
                                    startActivity(intent);
                                    finish();//破關
                        }
                        else if (bossheart<=0){
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    newgame();
                                }
                            },1000);
                        }

                    }
                },1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        diceBtn.setVisibility(View.INVISIBLE);
                        paperBtn.setEnabled(true);
                        rockBtn.setEnabled(true);
                        scissorsBtn.setEnabled(true);
                    }
                },2000);
            }
        });
    }
    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            try {
                music.stop();
                music.prepare();
            } catch (Exception ex) {
                Log.d("音樂問題", "onStart():"+ex.getMessage());
            }
        }
    };
    private void newgame() {
        level++;
        bossheartbackup+=5;
        bossheart=bossheartbackup;
        bosscount.setText("敵人血量:" +Integer.toString(bossheart));
        background.setImageResource(back[level%5]);
        boss.setImageResource(bossface[level]);
        levelcount.setText("level"+Integer.toString(level+1));
    }
}
package com.jia.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.jia.jsplayer.danmu.DanmuView;
import com.jia.jsplayer.listener.OnVideoControlListener;
import com.jia.jsplayer.utils.DisplayUtils;
import com.jia.jsplayer.video.JsPlayer;

import java.util.Random;

public class DanmuActivity extends AppCompatActivity {

    private JsPlayer jsplayer_danmu;

    private Button bt_add_danmu;

    private String path = "http://baobab.wdjcdn.com/1455782903700jy.mp4";

    public String DANMU[] = {"腌疙瘩，炸麻叶", "一种鸡蛋蒸虾酱", "鲜味妙不可言", "撒了芝麻的吊炉烧饼，焦香四溢", "西红柿鸡蛋面", "那浓郁深沉的酱油味仍然让我无比想念", "即使是二姨炒的土豆片", "蒸馍馍"};

    public int COLOR[] = {Color.BLUE, Color.WHITE, Color.YELLOW, Color.RED};

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_danmu);
        random = new Random();

        jsplayer_danmu = (JsPlayer) findViewById(R.id.jsplayer_danmu);
        bt_add_danmu = (Button) findViewById(R.id.bt_add_danmu);

        jsplayer_danmu.setOnVideoControlListener(new OnVideoControlListener() {
            @Override
            public void onStartPlay() {
                jsplayer_danmu.startPlay();
            }

            @Override
            public void onBack() {

            }

            @Override
            public void onFullScreen() {
                DisplayUtils.toggleScreenOrientation(DanmuActivity.this);
            }

            @Override
            public void onRetry(int errorStatus) {

            }
        });

        jsplayer_danmu.setPath(new VideoInfo("极品艺术", path));

        jsplayer_danmu.setDanMuAdapter(new MyDanmuAdapter(this));
        jsplayer_danmu.setDanMuSpeed(DanmuView.NORMAL_SPEED);


        bt_add_danmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDanmuModel danmuEntity = new MyDanmuModel();
                danmuEntity.setType(0);
                danmuEntity.setContent(DANMU[random.nextInt(8)]);
                danmuEntity.setTextColor(COLOR[random.nextInt(4)]);
                jsplayer_danmu.getDanmu().addDanmu(danmuEntity);
            }
        });
    }
}
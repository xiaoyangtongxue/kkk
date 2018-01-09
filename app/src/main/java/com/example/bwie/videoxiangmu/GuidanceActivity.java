package com.example.bwie.videoxiangmu;


import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 导航页
 */

public class GuidanceActivity extends View.BaseActivity {

    @BindView(R.id.guidance)
    ImageView guidance;

    private Boolean A=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        ButterKnife.bind(this);

        initchen();

        AnimationSet animationSet = new AnimationSet(false);
        Animation animation = new ScaleAnimation(1f, 1.1f, 1f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animationSet.addAnimation(animation);
        guidance.setAnimation(animationSet);
        animationSet.start();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(A==true) {
                    Intent intent = new Intent(GuidanceActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);


        guidance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuidanceActivity.this, MainActivity.class);
                startActivity(intent);
                A=false;
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        A=false;
    }
}

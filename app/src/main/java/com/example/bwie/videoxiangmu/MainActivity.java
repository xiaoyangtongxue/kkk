package com.example.bwie.videoxiangmu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xsj.movie.Fragments.ChoicenessFragment;
import com.example.xsj.movie.Fragments.ClassifyFragment;
import com.example.xsj.movie.Fragments.MineFragment;
import com.example.xsj.movie.Fragments.SpecialFragment;
import com.example.xsj.movie.View.BaseActivity;
import com.example.xsj.movie.View.CollectActivity;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.shoucang)
    LinearLayout shoucang;
    @BindView(R.id.xiazai)
    LinearLayout xiazai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initchen();

        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(40, 40)
                .setFontSize(8)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("精选", R.mipmap.found, ChoicenessFragment.class)
                .addTabItem("专题", R.mipmap.special, SpecialFragment.class)
                .addTabItem("发现", R.mipmap.fancy, ClassifyFragment.class)
                .addTabItem("我的", R.mipmap.my, MineFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                })
                .setTabBarBackgroundResource(R.mipmap.bottom_bg)
                .setBackgroundResource(R.mipmap.bg_blue);
    }

    @OnClick({R.id.shoucang, R.id.xiazai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shoucang:
                Intent intent = new Intent(this, CollectActivity.class);
                startActivity(intent);
                break;
            case R.id.xiazai:
                Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

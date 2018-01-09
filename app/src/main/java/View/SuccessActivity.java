package View;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bwie.videoxiangmu.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import Beans.BusBean;
import Beans.MovieBean;
import Beans.MovieBusBean;
import Fragments.EvaluateFragment;
import Fragments.SynopsisFragment;
import Presenter.HttpPresenter;
import Utils.DBUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import db.VideoBean;
import gen.VideoBeanDao;

public class SuccessActivity extends FragmentActivity implements Iview {

    @BindView(R.id.suc_tv)
    TextView sucTv;
    @BindView(R.id.jianjie)
    TextView jianjie;
    @BindView(R.id.pinglun)
    TextView pinglun;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.shoucang)
    ImageView shoucang;
    private HttpPresenter presenter;
    private Boolean isCheck=false;
    private VideoBeanDao dao;
    private String userid;
    private String username;
    private MovieBean movieBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        DBUtils dbUtils = new DBUtils(SuccessActivity.this);
        dao = dbUtils.getVideoBeanDao();

        //获得传过来的值
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        username = intent.getStringExtra("username");
        sucTv.setText("影名");
        Log.e("TAG",userid+":"+username);

        if (userid != null) {
            presenter = new HttpPresenter(this);
            HashMap<String, String> map = new HashMap<>();
            map.put("mediaId", userid);
            presenter.getMap(map, "详情");
        }

        //设置颜色默认
        jianjie.setTextColor(Color.YELLOW);
        pinglun.setTextColor(Color.WHITE);
        view1.setBackgroundColor(Color.YELLOW);
        view2.setBackgroundColor(Color.WHITE);


        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new SynopsisFragment();
                        break;
                    case 1:
                        fragment = new EvaluateFragment(userid);
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        jianjie.setTextColor(Color.YELLOW);
                        pinglun.setTextColor(Color.WHITE);
                        view1.setBackgroundColor(Color.YELLOW);
                        view2.setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        jianjie.setTextColor(Color.WHITE);
                        pinglun.setTextColor(Color.YELLOW);
                        view2.setBackgroundColor(Color.YELLOW);
                        view1.setBackgroundColor(Color.WHITE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //数据库查询
        if(userid !=null){
            List<VideoBean> list = dao.loadAll();
            for (int i=0;i<list.size();i++) {
                if(userid.equals(list.get(i).getMovieid())){
                    isCheck=true;
                    shoucang.setImageResource(R.mipmap.collection_select);
                    return;
                }else{
                    isCheck=false;
                    shoucang.setImageResource(R.mipmap.collection);
                }
            }
        }

    }

    //点击事件
    @OnClick({R.id.jianjie, R.id.pinglun, R.id.image,R.id.shoucang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jianjie:
                jianjie.setTextColor(Color.YELLOW);
                pinglun.setTextColor(Color.WHITE);
                view1.setBackgroundColor(Color.YELLOW);
                view2.setBackgroundColor(Color.WHITE);
                viewpager.setCurrentItem(0);
                break;
            case R.id.pinglun:
                jianjie.setTextColor(Color.WHITE);
                pinglun.setTextColor(Color.YELLOW);
                view2.setBackgroundColor(Color.YELLOW);
                view1.setBackgroundColor(Color.WHITE);
                viewpager.setCurrentItem(1);
                break;

            case R.id.image:
                finish();
                break;

        }
    }


    //请求数据成功
    @Override
    public void OnSuccess(Object o, String tag) {
        if (tag.equals("详情") && !TextUtils.isEmpty(o + "")) {
            movieBean = (MovieBean) o;

            sucTv.setText(movieBean.getRet().getTitle());

            if (movieBean != null) {
                //EventBus发送
                BusBean busBean = new BusBean("0", movieBean);
                EventBus.getDefault().post(busBean);
            }

            String hdurl = movieBean.getRet().getHDURL();
            Log.e("TAGSSSS", "视频" + hdurl.toString());

            //初始化配置
            Uri uri = Uri.parse(hdurl);
            //设置视频路径
            videoView.setVideoURI(uri);
            //设置视频管理器
            MediaController controller = new MediaController(this);
            //关联管理器
            videoView.setMediaController(controller);
            controller.setAnchorView(videoView);
            //开始播放视频
            videoView.start();
            //播放完成回调
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(SuccessActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
                }
            });

        }

        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isCheck == true){
                    shoucang.setImageResource(R.mipmap.collection_select);
                    isCheck=false;

                    Boolean A=true;

                    if(userid !=null){
                        List<VideoBean> list = dao.loadAll();
                        for (int i=0;i<list.size();i++) {
                            if(userid.equals(list.get(i).getMovieid())){
                                Toast.makeText(SuccessActivity.this,"已经添加了",Toast.LENGTH_SHORT).show();
                                A=false;
                                return;
                            }
                        }
                    }

                    if(A){
                        //添加数据库
                        VideoBean videoBean = new VideoBean();
                        videoBean.setMovieid(userid);
                        videoBean.setName(movieBean.getRet().getTitle());
                        videoBean.setMoviepic(movieBean.getRet().getPic());
                        long insert = dao.insert(videoBean);
                        Log.e("TAG","添加了"+insert);
                    }

                }else{
                    shoucang.setImageResource(R.mipmap.collection);
                    isCheck=true;
                }
            }
        });


    }

    //接收到pager发送的消息
    @Subscribe
    public void onEventMainThread(BusBean busBean) {

        if (busBean != null) {
            String id = busBean.getId();
            if (id.equals("1")) {
                Log.e("TAG", "点击后的数据" + busBean.getO());
                final MovieBusBean movie = (MovieBusBean) busBean.getO();

                userid = movie.getMovieid();

                //数据库查询
                if(movie.getMovieid() !=null){
                    List<VideoBean> list = dao.loadAll();
                    for (int i=0;i<list.size();i++) {
                        if(movie.getMovieid().equals(list.get(i).getMovieid())){
                            isCheck=true;
                            shoucang.setImageResource(R.mipmap.collection_select);
                            return;
                        }else{
                            isCheck=false;
                            shoucang.setImageResource(R.mipmap.collection);
                        }
                    }
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("mediaId", movie.getMovieid());
                presenter.getMap(map, "详情");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);//反注册EventBus
        presenter.disattach();

        if (videoView != null) {
            if (videoView.isPlaying()) {
                videoView.stopPlayback();
                videoView = null;
            }
        }
        finish();
    }
}

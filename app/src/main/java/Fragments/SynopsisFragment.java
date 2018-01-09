package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bwie.videoxiangmu.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import AdApters.ListMovieAdapter;
import Beans.BusBean;
import Beans.MovieBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by xsj on 2017/12/14.
 */

public class SynopsisFragment extends Fragment {
    @BindView(R.id.daoyan)
    TextView daoyan;
    @BindView(R.id.yanyuan)
    TextView yanyuan;
    @BindView(R.id.zhankai)
    TextView zhankai;
    @BindView(R.id.moviewjianjie)
    TextView moviewjianjie;
    @BindView(R.id.jianjiemovie)
    RecyclerView jianjiemovie;
    Unbinder unbinder;

    private Boolean A = false;
    private MovieBean.RetBean ret;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.synopsislayout, null);

        unbinder = ButterKnife.bind(this, view);
        //注册EventBus
        EventBus.getDefault().register(this);

        moviewjianjie.setCursorVisible(true);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        jianjiemovie.setLayoutManager(manager);

        return view;
    }

    //接收
    @Subscribe
    public void onEventMainThread( BusBean busbean) {
        if (busbean != null) {
            String id = busbean.getId();
            if(id.equals("0")) {
                MovieBean movieBean = (MovieBean) busbean.getO();
                ret = movieBean.getRet();
                daoyan.setText("导演  ：  " + ret.getDirector());
                yanyuan.setText("主演  ：  " + ret.getActors());

                if(movieBean!=null) {
                    List<MovieBean.RetBean.ListBean> list = ret.getList();
                    if(list!=null){
                        List<MovieBean.RetBean.ListBean.ChildListBean> childList = list.get(0).getChildList();
                        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(getActivity(), childList);
                        jianjiemovie.setAdapter(listMovieAdapter);
                    }
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @OnClick(R.id.zhankai)
    public void onViewClicked() {
        if(A==true){
            A=false;
            moviewjianjie.setText("");
            zhankai.setText("展开");
        }else{
            A=true;
            moviewjianjie.setText(ret.getDescription());
            zhankai.setText("收起");
        }
    }
}

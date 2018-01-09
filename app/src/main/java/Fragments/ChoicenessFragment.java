package Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bwie.videoxiangmu.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import AdApters.HomeRecycleAdapter;
import Beans.HomeBean;
import Presenter.HttpPresenter;
import Utils.AlphaTitleScrollView;
import Utils.BannerImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by xsj on 2017/12/14.
 */

public class ChoicenessFragment extends Fragment implements View.Iview {

    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.recycles)
    RecyclerView recycle;
    @BindView(R.id.show)
    RelativeLayout show;
    @BindView(R.id.as)
    AlphaTitleScrollView as;
    @BindView(R.id.et_sousuo)
    LinearLayout etSousuo;

    private HttpPresenter presenter;
    private List<HomeBean.RetBean.ListBean.ChildListBean> childList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choicenesslayout, null);

        unbinder = ButterKnife.bind(this, view);

        HashMap<String, String> map = new HashMap<>();
        presenter = new HttpPresenter(this);
        presenter.getMap(map, "首页");


        as.set(new AlphaTitleScrollView.onScrollChangedListener() {
            @Override
            public void Changed(int l, int t, int oldl, int oldt) {
                if (t > 150) {
                    show.setVisibility(View.VISIBLE);
                } else {
                    show.setVisibility(View.INVISIBLE);
                }
            }
        });

        return view;
    }

    //请求成功
    @Override
    public void OnSuccess(Object o, String tag) {

        if (tag.equals("首页")) {
            HomeBean homeBean = (HomeBean) o;
            HomeBean.RetBean ret = homeBean.getRet();
            final List<HomeBean.RetBean.ListBean> list = ret.getList();

            //首页轮播图
            getBanner(list);

            final List<HomeBean.RetBean.ListBean.ChildListBean> childList = list.get(0).getChildList();
            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recycle.setLayoutManager(manager);
            HomeRecycleAdapter adapter = new HomeRecycleAdapter(getActivity(), childList);
            adapter.setOnRecycleViewListener(new HomeRecycleAdapter.OnRecycleViewListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    Intent intent = new Intent(getActivity(), View.SuccessActivity.class);
                    intent.putExtra("username", childList.get(postion).getTitle());
                    intent.putExtra("userid", childList.get(postion).getDataId());
                    startActivity(intent);
                }
            });
            recycle.setAdapter(adapter);

        }
    }

    //banner轮播图
    private void getBanner(List<HomeBean.RetBean.ListBean> list) {
        //首页Banner的显示
        ArrayList<String> imlist = new ArrayList<>();

        childList = list.get(0).getChildList();
        for (int x = 0; x < childList.size(); x++) {
            String pic = childList.get(x).getPic();
            imlist.add(pic);
        }

        banner.setImageLoader(new BannerImageLoader()); //自己建一个类

        banner.setImages(imlist); //自己建一个list集合放图片

        banner.setDelayTime(3000);//几秒切换一次图片

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), View.SuccessActivity.class);
                intent.putExtra("username", childList.get(position).getTitle());
                intent.putExtra("userid", childList.get(position).getDataId());
                startActivity(intent);
            }
        });
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        presenter.disattach();
    }

    @OnClick(R.id.et_sousuo)
    public void onViewClicked() {
        Toast.makeText(getActivity(),"sss",Toast.LENGTH_SHORT).show();
    }
}

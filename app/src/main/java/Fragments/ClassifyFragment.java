package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.videoxiangmu.R;

import java.util.HashMap;
import java.util.List;

import AdApters.TanTanRecycle;
import Beans.HomeBean;
import Presenter.HttpPresenter;

/**
 * Created by xsj on 2017/12/14.
 */

public class ClassifyFragment extends Fragment implements View.Iview {

    private HttpPresenter presenter;
    private RecyclerView recycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifylayout, null);

        recycle = view.findViewById(R.id.recycleer);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        recycle.setLayoutManager(linearLayoutManager);

        HashMap<String, String> map = new HashMap<>();
        presenter = new HttpPresenter(this);

        presenter.getMap(map, "首页");

        return view;
    }


    //数据请求成功
    @Override
    public void OnSuccess(Object o, String tag) {
        if (tag.equals("首页")) {
            HomeBean homeBean = (HomeBean) o;
            HomeBean.RetBean ret = homeBean.getRet();
            List<HomeBean.RetBean.ListBean.ChildListBean> childList = ret.getList().get(0).getChildList();
            TanTanRecycle tanTanRecycle = new TanTanRecycle(childList, getActivity());

            recycle.setLayoutManager(new OverLayCardLayoutManager());
            CardConfig.initConfig(getActivity());
            ItemTouchHelper.Callback callback = new RenRenCallback(recycle, tanTanRecycle, childList);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recycle);

            recycle.setAdapter(tanTanRecycle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.disattach();
    }
}

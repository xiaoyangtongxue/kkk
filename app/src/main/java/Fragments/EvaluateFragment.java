package Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.videoxiangmu.R;

import java.util.HashMap;
import java.util.List;

import AdApters.PingLunAdapter;
import Beans.PingLunBean;
import Presenter.HttpPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xsj on 2017/12/14.
 */

public class EvaluateFragment extends Fragment implements View.Iview {
    @BindView(R.id.rectcle)
    RecyclerView rectcle;
    Unbinder unbinder;
    private String uid;
    private HttpPresenter presenter;

    public EvaluateFragment(String uid) {
        this.uid = uid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.evaluatelayout, null);
        unbinder = ButterKnife.bind(this, view);

        if (uid!=null&& !TextUtils.isEmpty(uid)) {
            presenter = new HttpPresenter(this);
            HashMap<String, String> map = new HashMap<>();
            map.put("mediaId", uid);

            presenter.getMap(map, "评论");
        }

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rectcle.setLayoutManager(manager);

        return view;
    }

    //数据请求成功
    @Override
    public void OnSuccess(Object o, String tag) {
        if (tag.equals("评论")) {


            if(o!=null){
            PingLunBean pingLunBean = (PingLunBean) o;
            List<PingLunBean.RetBean.ListBean> list = pingLunBean.getRet().getList();
            PingLunAdapter pingLunAdapter = new PingLunAdapter(list, getActivity());
            rectcle.setAdapter(pingLunAdapter);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.disattach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

package Presenter;

import android.util.Log;

import java.util.Map;

import Beans.HomeBean;
import Beans.MovieBean;
import Beans.PingLunBean;
import Model.HttpModel;
import View.Iview;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by xsj on 2017/12/14.
 */

public class HttpPresenter  implements  Pview,MoviePview,PingLunPview  {

    private Iview iv;
    private DisposableSubscriber<PingLunBean> p3;
    private DisposableSubscriber<MovieBean> p2;
    private DisposableSubscriber<HomeBean> p1;

    public HttpPresenter(Iview iv) {
        this.iv = iv;
    }

    public void getMap(Map<String,String> map,String tag){
        HttpModel httpModel = new HttpModel(this,this,this);
        httpModel.getApi(map,tag);
        Log.e("TAG","getMap");
    }


    @Override
    public void getData(Flowable<HomeBean> flowable, final String tag) {
        Log.e("TAG","执行到getData");
        p1 = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        iv.OnSuccess(homeBean, tag);
                        Log.e("TAG", "执行到" + homeBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("TAG", "请求失败：" + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getMovieData(Flowable<MovieBean> flowable, final String tag) {
        p2 = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MovieBean>() {
                    @Override
                    public void onNext(MovieBean movieBean) {
                        iv.OnSuccess(movieBean,tag);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getMoviePingLun(Flowable<PingLunBean> flowable, final String tag) {

        p3 = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<PingLunBean>() {
                    @Override
                    public void onNext(PingLunBean pingLunBean) {
                        iv.OnSuccess(pingLunBean, tag);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public  void disattach(){
        if(iv!=null){
            iv=null;
        }

        if(p1!=null){
            if(p1.isDisposed()){
                p1.dispose();
            }
        }

        if(p2!=null){
            if(p2.isDisposed()){
                p2.dispose();
            }
        }

        if(p3!=null){
            if(p3.isDisposed()){
                p3.dispose();
            }
        }
    }

}

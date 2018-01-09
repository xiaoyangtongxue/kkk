package Presenter;


import Beans.PingLunBean;
import io.reactivex.Flowable;

/**
 * Created by xsj on 2017/12/14.
 */

public interface PingLunPview {
    void getMoviePingLun(Flowable<PingLunBean> flowable, String tag);
}

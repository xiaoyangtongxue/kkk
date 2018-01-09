package Presenter;


import Beans.HomeBean;
import io.reactivex.Flowable;

/**
 * Created by xsj on 2017/12/14.
 */

public interface Pview {
    void getData(Flowable<HomeBean> flowable, String tag);
}

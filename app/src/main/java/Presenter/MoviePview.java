package Presenter;


import Beans.MovieBean;
import io.reactivex.Flowable;

/**
 * Created by xsj on 2017/12/14.
 */

public interface MoviePview {
    void getMovieData(Flowable<MovieBean> flowable, String tag);
}

package Utils;


import java.util.Map;

import Beans.HomeBean;
import Beans.MovieBean;
import Beans.PingLunBean;
import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by xsj on 2017/12/14.
 */

public interface ApiNet {

    //首页
    @POST("homePageApi/homePage.do")
    Flowable<HomeBean> getDataAll();

    //首页
    @POST("videoDetailApi/videoDetail.do")
    Flowable<MovieBean> getMoviewData(@QueryMap Map<String, String> map);

    //评论
    @POST("Commentary/getCommentList.do")
    Flowable<PingLunBean> getMoviewPingLun(@QueryMap Map<String, String> map);
}

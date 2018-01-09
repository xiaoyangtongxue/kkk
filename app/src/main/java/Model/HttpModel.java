package Model;

import android.util.Log;

import java.util.Map;

import Beans.HomeBean;
import Beans.MovieBean;
import Beans.PingLunBean;
import Presenter.MoviePview;
import Presenter.PingLunPview;
import Presenter.Pview;
import Utils.ApiNet;
import Utils.HttpUtils;
import io.reactivex.Flowable;

/**
 * Created by xsj on 2017/12/14.
 */

public class HttpModel {
    private Pview pv;
    private MoviePview mp;
    private PingLunPview pp;

    public HttpModel(Pview pv, MoviePview mp, PingLunPview pp) {
        this.pv = pv;
        this.mp = mp;
        this.pp = pp;
    }

    public void getApi(Map<String,String> map, String tag){
        ApiNet apiNet = HttpUtils.getInstance().getApiNet();

        if(tag.equals("首页")){
            Flowable<HomeBean> dataAll =apiNet.getDataAll();
            pv.getData(dataAll,tag);
            Log.e("TAG","Model:"+dataAll);
        }

        if(tag.equals("详情")){
            Flowable<MovieBean> moviewData = apiNet.getMoviewData(map);
            mp.getMovieData(moviewData,tag);
        }

        if(tag.equals("评论")){
            Flowable<PingLunBean> moviewPingLun = apiNet.getMoviewPingLun(map);
            pp.getMoviePingLun(moviewPingLun,tag);
        }
    }
}

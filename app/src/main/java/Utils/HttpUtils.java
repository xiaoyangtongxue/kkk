package Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xsj on 2017/12/14.
 */

public class HttpUtils {

    private  static  volatile  HttpUtils instance;
    private final ApiNet apiNet;

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    public HttpUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.svipmovie.com/front/")
                .client(new OkHttpClient.Builder().build())
                .build();
        apiNet = retrofit.create(ApiNet.class);
    }

    public ApiNet getApiNet(){
        return  apiNet;
    }
}

package Utils;

import android.app.Application;


import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Created by xsj on 2017/12/14.
 */

public class MyAppliction extends Application{
    @Override
    public void onCreate() {
        Fresco.initialize(this);
        super.onCreate();
    }

}

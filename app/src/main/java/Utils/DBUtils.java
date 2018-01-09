package Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.xsj.movie.gen.DaoMaster;
import com.example.xsj.movie.gen.DaoSession;
import com.example.xsj.movie.gen.VideoBeanDao;

/**
 * Created by xsj on 2017/12/30.
 */

public class DBUtils {

    private final VideoBeanDao videoBeanDao;

    public DBUtils(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "user", null);
        // 获取数据库操作对象
        SQLiteDatabase database = helper.getWritableDatabase();
        //获取DaoMaster对象
        DaoMaster daoMaster = new DaoMaster(database);
        // 获取DaoSession对象
        DaoSession session = daoMaster.newSession();

        // 拿到要操作的对象
        videoBeanDao = session.getVideoBeanDao();
    }


    public  VideoBeanDao getVideoBeanDao(){
        return videoBeanDao;
    }

}

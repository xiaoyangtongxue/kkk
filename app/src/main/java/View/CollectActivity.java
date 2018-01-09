package View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bwie.videoxiangmu.R;

import java.util.List;

import AdApters.CollectAdapter;
import Utils.DBUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import db.VideoBean;
import gen.VideoBeanDao;

;

public class CollectActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.qingchu)
    TextView qingchu;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private VideoBeanDao dao;
    private CollectAdapter collectAdapter;
    private List<VideoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        //数据库的创建
        DBUtils dbUtils = new DBUtils(CollectActivity.this);
        dao = dbUtils.getVideoBeanDao();

        //查询数据库
        list = dao.loadAll();
        if(list.size()>0){
            collectAdapter = new CollectAdapter(this, list);
            GridLayoutManager manager = new GridLayoutManager(this, 3);
            recycle.setLayoutManager(manager);
            recycle.setAdapter(collectAdapter);
        }
    }

    @OnClick({R.id.image, R.id.qingchu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image:
                finish();
                break;
            case R.id.qingchu:
                dao.deleteAll();
                list=null;
                collectAdapter.notifyDataSetChanged();
                break;
        }
    }
}

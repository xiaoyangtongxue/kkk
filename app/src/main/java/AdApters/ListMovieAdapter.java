package AdApters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import Beans.BusBean;
import Beans.MovieBean;
import Beans.MovieBusBean;

/**
 * Created by xsj on 2017/12/15.
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder>{

    private Context context;
    private  List<MovieBean.RetBean.ListBean.ChildListBean> list ;

    public ListMovieAdapter(Context context,  List<MovieBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.listmovie, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.im.setImageURI(list.get(position).getPic());
        holder.tv.setText(list.get(position).getTitle());

        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieBusBean movieBusBean = new MovieBusBean(list.get(position).getTitle(), list.get(position).getDataId(), list.get(position).getPic());
                BusBean busBean = new BusBean("1", movieBusBean);
                EventBus.getDefault().post(busBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //优化extends RecyclerView.ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView im;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.list_movie);
            tv=itemView.findViewById(R.id.list_movie_name);
        }
    }
}

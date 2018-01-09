package AdApters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import db.VideoBean;


/**
 * Created by xsj on 2017/12/15.
 */

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder>{

    private Context context;
    private List<VideoBean> list;

    public CollectAdapter(Context context, List<VideoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.dbmovie, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.im.setImageURI(list.get(position).getMoviepic());
        holder.tv.setText(list.get(position).getName());

        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, View.SuccessActivity.class);
                intent.putExtra("username", list.get(position).getName());
                intent.putExtra("userid", list.get(position).getMovieid());
                context.startActivity(intent);
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

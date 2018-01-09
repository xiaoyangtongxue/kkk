package AdApters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Beans.PingLunBean;

/**
 * Created by xsj on 2017/12/15.
 */

public class PingLunAdapter extends RecyclerView.Adapter<PingLunAdapter.ViewHolder> {

    private List<PingLunBean.RetBean.ListBean> list;
    private Context context;

    public PingLunAdapter(List<PingLunBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.pinglunlayout, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String userPic = list.get(position).getUserPic();
        if(userPic!=null) {
            holder.im.setImageURI(userPic);
        }else{
            holder.im.setImageURI(Uri.parse("res://com.example.xsj.movie/" + R.mipmap.photo));
        }

        holder.name.setText(list.get(position).getPhoneNumber());
        holder.time.setText(list.get(position).getTime());
        holder.ping.setText(list.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView time,ping;
        private final SimpleDraweeView im;

        public ViewHolder(View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            ping=itemView.findViewById(R.id.ping);
        }
    }
}

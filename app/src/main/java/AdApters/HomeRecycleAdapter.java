package AdApters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Beans.HomeBean;

/**
 * Created by xsj on 2017/12/14.
 */

public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.ViewHolder>{

    private Context context;
    private List<HomeBean.RetBean.ListBean.ChildListBean> list;


    //定义一个接口
    public interface OnRecycleViewListener{
        void onItemClick(View view, int postion);//单击事件
    }
    //封装私有变量
    private OnRecycleViewListener onRecycleViewListener;

    public void setOnRecycleViewListener(OnRecycleViewListener onItemClickListen){
        this.onRecycleViewListener = onItemClickListen;
    }

    public HomeRecycleAdapter(Context context, List<HomeBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.movieitem, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.im.setImageURI(list.get(position).getPic());
        holder.tv.setText(list.get(position).getTitle());

        holder.im.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            onRecycleViewListener.onItemClick(holder.itemView,position);
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
            im = itemView.findViewById(R.id.home_im);
            tv=itemView.findViewById(R.id.home_tv);
        }
    }
}

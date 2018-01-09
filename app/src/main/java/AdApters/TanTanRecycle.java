package AdApters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Beans.HomeBean;

/**
 * Created by xsj on 2017/12/15.
 */

public class TanTanRecycle extends RecyclerView.Adapter<TanTanRecycle.ViewHolder>{

    private List<HomeBean.RetBean.ListBean.ChildListBean> list;
    private Context context;

    public TanTanRecycle(List<HomeBean.RetBean.ListBean.ChildListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.tantanlayout, null);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String pic = list.get(position).getPic();
        Log.e("TAG","ss"+pic.toString());
        Uri parse = Uri.parse(pic);
        holder.simpleDraweeView.setImageURI(parse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private  SimpleDraweeView simpleDraweeView;

        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.tantanim);
        }
    }
}

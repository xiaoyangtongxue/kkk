package Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;


//ScrollView监听
public class AlphaTitleScrollView extends ScrollView {

    private onScrollChangedListener listener;

    public interface onScrollChangedListener{
        void Changed(int l, int t, int oldl, int oldt);
    }

    public void set(onScrollChangedListener listener){
        this.listener = listener;
    }

    public AlphaTitleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AlphaTitleScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaTitleScrollView(Context context) {
        this(context, null);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if(listener!=null) {
            listener.Changed(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
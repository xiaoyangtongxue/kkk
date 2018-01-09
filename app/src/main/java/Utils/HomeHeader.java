package Utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


/**
 * Created by xsj on 2017/12/14.
 */

public class HomeHeader extends LinearLayout {
    public HomeHeader(Context context) {
        this(context,null);
    }

    public HomeHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = View.inflate(context, R.layout.homeheaderlayout, null);
    }
}

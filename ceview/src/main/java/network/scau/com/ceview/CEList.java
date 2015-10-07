package network.scau.com.ceview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/9/24 0024.
 */
public class CEList extends ListView {



    @Override
    public void setSelection(int position) {
        setSelectionFromTop(position,0);

    }

    public CEList(Context context) {
        super(context);
    }

    public CEList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CEList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }


}

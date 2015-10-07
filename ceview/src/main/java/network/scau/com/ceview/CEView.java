package network.scau.com.ceview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/9/26 0026.
 */
public class CEView extends RelativeLayout{

    private Context context;
    private CEList ceList;
    private CharactersGuideView cgv;
    private List<Cell> datas;
    private CEAdapter ceAdapter;
    private List<String> index;



    public CEView(Context context) {
        super(context);
        this.context = context;
        init();
    }
    public CEView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CEView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void setDatasandIndex(List<Cell> datas,List<String> index) {
        this.datas = datas;
        this.index = index;

        initAdapter();
    }

    private void initAdapter() {
        if(ceAdapter==null) {
            ceAdapter = new CEAdapter(context, datas);
        }
        ceList.setAdapter(ceAdapter);
        ceList.setSelection(0);

        cgv.setItemIndex(index);
        cgv.setOnTouchAssortListener(new CharactersGuideView.OnTouchAssortListener() {


            View layoutView = LayoutInflater.from(context)
                    .inflate(R.layout.popup_tag, null);
            TextView text = (TextView) layoutView.findViewById(R.id.id_tv_popuptag);
            PopupWindow popupWindow;

            @Override
            public void onTouchAssortListener(String s) {
//                char c = s.toString().charAt(0);
                Integer charIndex = ceAdapter.getCharIndex(s);
                if (charIndex != -1) {
                    ceList.setSelection(charIndex);
                }
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(layoutView,
                            250, 250,
                            false);
                    // 显示在Activity的根视图中心
                    popupWindow.showAtLocation(((Activity) context).getWindow().getDecorView(),
                            Gravity.CENTER, 0, 0);
                }
                text.setText(s);
            }

            @Override
            public void onTouchAssortUP() {
                if (popupWindow != null)
                    popupWindow.dismiss();
                popupWindow = null;
            }
        });
    }

    public void setAverage(boolean average){
        cgv.setAverge(average);
    }

    public boolean isAverage(){
        return  cgv.isAverge();
    }

    public void setGuideTextSize(int textSize) {
        cgv.setTextSize(textSize);
    }

    public void setContextColor(int contextColor) {
        ceAdapter.setCcontextColor(contextColor);
    }

    public void setCharecterColor(int charecterColor) {
        ceAdapter.setCharecterColor(charecterColor);
    }

    public void setContextTextColor(int contextTextColor) {
        ceAdapter.setContextTextColor(contextTextColor);
    }

    public void setCharacterTextColor(int characterTextColor) {
        ceAdapter.setCharacterTextColor(characterTextColor);
    }

    public void setNormalGuideViewBackgroundColor(int backgroundColor) {
        cgv.setNormalBbackgroundColor(backgroundColor);
    }

    public void setNormalGuideViewTextColor(int textColor) {
        cgv.setNormalTextColor(textColor);
    }

    public void setSelectedGuideViewBackgroundColor(int backgroundColor) {
        cgv.setSelectedBbackgroundColor(backgroundColor);
    }

    public void setSelectedGuideViewTextColor(int textColor) {
        cgv.setSelectedTextColor(textColor);
    }

    public void setIndexBlock(int num){
        cgv.setBlock(num);
    }

    private void init() {
        RelativeLayout.inflate(context, R.layout.ceview, CEView.this);

        ceList = (CEList) findViewById(R.id.id_list);
        cgv = (CharactersGuideView) findViewById(R.id.cgv);

        index = new ArrayList<String>();
        datas = new ArrayList<Cell>();



    }




}

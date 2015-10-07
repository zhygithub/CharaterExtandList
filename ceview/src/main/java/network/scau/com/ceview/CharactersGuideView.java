package network.scau.com.ceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import java.util.List;

/**
 * Created by Administrator on 2015/9/25 0025.
 */
public class CharactersGuideView extends Button {



    //当前View的宽度
    private int mViewWidth;

    //当前View的高度
    private int mViewHeight;

    //当前的触摸状态
    private  boolean mTouched = false;

    //画笔
    private Paint mPaint = new Paint();

    //绘制的间隔
    int interval ;

    private List<String> mIndex2 ;



//    private String[] mIndex =new String[]{"#","A","B","C","D","E",
//            "F","G","H","I","J",
//            "K","L","M","N","O",
//            "P","Q","R","S","T",
//            "U","V","W","X","Y","Z"};

    //设置选择的索引
    private int selectIndex = -1;
    public  static String TAG = "CharactersGuideView";

    //设置是否平均分配高度
    private boolean averge = false;

    //设置分成的块数
    private  int block =  26;

    //设置字体大小
    private int textSize;

    //默认文字颜色为黑色
    private int normalTextColor = 0xff000000;
    //默认背景色为白色
    private int normalBbackgroundColor = 0xffffffff;

    //点击时背景色
    private int selectedBbackgroundColor = Color.GRAY;

    //选中的文字颜色
    private int selectedTextColor = Color.BLUE;

    // 字母监听接口
    public interface OnTouchAssortListener {
        public void onTouchAssortListener(String s);
        public void onTouchAssortUP();
    }

    // 字母监听器
    private OnTouchAssortListener onTouch;


    public void setAverge(boolean averge) {
        this.averge = averge;
    }

    public boolean isAverge() {
        return averge;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getBlock() {
        return block;
    }

    public void setOnTouchAssortListener(OnTouchAssortListener onTouch) {
        this.onTouch = onTouch;
    }

    public CharactersGuideView(Context context) {
        super(context);
    }

    public CharactersGuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharactersGuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setItemIndex(List<String> mIndex2) {
        this.mIndex2 = mIndex2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //将测量后的宽高返回给父控件
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int height;
        //获取当前的测量模式
        int mode = MeasureSpec.getMode(heightMeasureSpec);

        //获得初步的测量值
        int size = MeasureSpec.getSize(heightMeasureSpec);

        //如果测量模式为精确模式
        if(mode== MeasureSpec.EXACTLY){
            height = size;
        }else{
            //不是精确模式的话得自己结合paddin
            int desire = size + getPaddingTop() + getPaddingBottom() ;
            if(mode == MeasureSpec.AT_MOST){
                height = Math.min(desire,size) ;
            }else {
                height = desire ;
            }
        }

        mViewHeight = height ;
//        textSize = height/block;
        return height ;
    }

    private int measureWidth(int widthMeasureSpec) {
        int width;
        //获取当前的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        //获得初步的测量值
        int size = MeasureSpec.getSize(widthMeasureSpec);

        //如果测量模式为精确模式
        if(mode== MeasureSpec.EXACTLY){
            width = size;
        }else{
            //不是精确模式的话得自己结合paddin
            int desire = size + getPaddingLeft() + getPaddingRight() ;
            if(mode == MeasureSpec.AT_MOST){
                width = Math.min(desire,size) ;
            }else {
                width = desire ;
            }
        }

        mViewWidth = width ;
        return width ;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(averge){
            interval = mViewHeight / block;
        }else{
            interval = textSize + 10;
        }

        //设置背景色
        setBackgroundColor(normalBbackgroundColor);
        if(mTouched){
            setBackgroundColor(selectedBbackgroundColor);
        }

        for (int i = 0 ;i< mIndex2.size() ;i ++){
            //设置抗锯齿
            mPaint.setAntiAlias(true);

            // 默认粗体
            mPaint.setTypeface(Typeface.DEFAULT_BOLD);

            // 字体颜色
            mPaint.setColor(normalTextColor);
            //字体大小

            mPaint.setTextSize(textSize);

            if (i == selectIndex) {

                // 被选择的字母改变颜色和粗体
                mPaint.setColor(selectedTextColor);
                //粗体
                mPaint.setFakeBoldText(true);
            }

            //绘制的字母的X坐标
            float x = mViewWidth / 2 - mPaint.measureText(mIndex2.get(i)) / 2;
            //绘制的字母的y坐标
            float y = interval * (i+0.5f);
            canvas.drawText(mIndex2.get(i), x, y+10, mPaint);
            mPaint.reset();
        }
    }

    public void setNormalBbackgroundColor(int normalBbackgroundColor) {
        this.normalBbackgroundColor = normalBbackgroundColor;
    }

    public void setNormalTextColor(int normalTextColor) {
        this.normalTextColor = normalTextColor;
    }

    public void setSelectedBbackgroundColor(int selectedBbackgroundColor) {
        this.selectedBbackgroundColor = selectedBbackgroundColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        float y = event.getY();
        int index;
//        int index = (int) (y / mViewHeight * mIndex2.size());


        index = (int) (( y/interval));

        if(index > mIndex2.size()){
            index = mIndex2.size();
        }

        if (index >= 0 && index < mIndex2.size()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    // 如果滑动改变
                    if (selectIndex != index) {
                        selectIndex = index;
                        if (onTouch != null) {
                            onTouch.onTouchAssortListener(mIndex2.get(selectIndex));
                        }

                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    selectIndex = index;
                    mTouched = true;
                    if (onTouch != null) {
                        onTouch.onTouchAssortListener(mIndex2.get(selectIndex));
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    if (onTouch != null) {
                        onTouch.onTouchAssortUP();
                    }
                    mTouched = false;
                    selectIndex = -1;
                    break;
            }
        } else {
            selectIndex = -1;
            if (onTouch != null) {
                onTouch.onTouchAssortUP();
            }
        }
        invalidate();

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}

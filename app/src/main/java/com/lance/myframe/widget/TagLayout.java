package com.lance.myframe.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.lance.myframe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TagLayout extends ViewGroup implements View.OnClickListener {

    private final int DEFAULT_CAN_SELECTED = 5;

    private List<TagLine> allLines = new ArrayList<>(0);

    private SparseArray checkArray = new SparseArray(0);

    private TagLine mCurrentLine;

    private final int DEFAULT_BLANK_SPACE = 6;

    private TagLayoutAdapter mAdapter;

    private int mUsableWidth = 0;

    private int mUsableHeight = 0;

    private int horizontalSpace = DEFAULT_BLANK_SPACE;

    private int verticalSpace = DEFAULT_BLANK_SPACE;

    private int tagCanSelectedNum = DEFAULT_CAN_SELECTED;

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.TagLayout);
        final int N = array.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.TagLayout_horizontal_space:
                    horizontalSpace = array.getDimensionPixelSize(R.styleable.TagLayout_horizontal_space,DEFAULT_BLANK_SPACE);
                    break;
                case R.styleable.TagLayout_vertical_space:
                    verticalSpace = array.getDimensionPixelSize(R.styleable.TagLayout_vertical_space,DEFAULT_BLANK_SPACE);
                    break;
                case R.styleable.TagLayout_can_selected:
                    tagCanSelectedNum = array.getDimensionPixelSize(R.styleable.TagLayout_vertical_space,DEFAULT_CAN_SELECTED);
                    break;

            }
        }
        array.recycle();

    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = getPaddingTop();
        int left = getPaddingLeft();

        for (TagLine line : allLines) {
            line.onLayout(top, left);
            //计算下一行的起始位置:  top= 原始起点+当前行高+水平行间距 , （left不变）
            top += line.getLineHeight() + verticalSpace;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mTmpLineWidth = 0;
        mCurrentLine = newLine();

        mUsableWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        mUsableHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

        for (int i = 0 ; i < getChildCount() ; i++){

            View childView = getChildAt(i);

            if (childView.getVisibility()== View.GONE)
                continue;

            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mUsableWidth, MeasureSpec.AT_MOST);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(mUsableHeight, MeasureSpec.AT_MOST);

            childView.measure(childWidthMeasureSpec,childHeightMeasureSpec);

            int childMeasureWidth = childView.getMeasuredWidth();

            mTmpLineWidth += childMeasureWidth;

            if (mTmpLineWidth<=mUsableWidth){
                mCurrentLine.addView(childView);
                mTmpLineWidth += horizontalSpace;
                if (mTmpLineWidth>=mUsableWidth)
                    mCurrentLine = newLine();

            }else if(mCurrentLine.getViewCount()==0){
                mCurrentLine.addView(childView);
                mCurrentLine = newLine();
                mTmpLineWidth = 0;
            }else{
                mCurrentLine = newLine();
                mCurrentLine.addView(childView);
                mTmpLineWidth=childMeasureWidth+horizontalSpace;
            }
        }

        int lastWidth = MeasureSpec.getSize(widthMeasureSpec);
        int lastHeight = getPaddingBottom()+getPaddingTop();

        for (TagLine line : allLines){
            lastHeight+=line.lineHeight;
        }

        lastHeight+=verticalSpace*(allLines.size()-1);
        setMeasuredDimension(lastWidth,resolveSize(lastHeight,heightMeasureSpec));

    }

    @Override
    public void onClick(View v) {
        TagItemLayout layout = null;
        if (v instanceof TagItemLayout)
            layout = (TagItemLayout) v;
        if (layout !=null){
            if (layout.isChecked()){
                layout.setChecked(false);
                checkArray.remove(indexOfChild(layout));
            }else{
                if (checkArray.size()<tagCanSelectedNum){
                    int position = indexOfChild(layout);
                    checkArray.put(indexOfChild(layout), mAdapter.getItem(position));
                    layout.setChecked(true);
                }
            }
        }
    }

    public List<Object> getSelectedData(){
        List<Object> result = new ArrayList<>(0);
        for (int i = 0; i<checkArray.size();i++){
            result.add(checkArray.valueAt(i));
        }
        return result;
    }

    private TagLine newLine(){
        TagLine line = new TagLine();
        allLines.add(line);
        return line;
    }

    public void setAdapter(TagLayoutAdapter adapter){

        if (adapter==null)
            return;

        mAdapter = adapter;

        for (int i = 0;i<adapter.getCount();i++){
            View v= adapter.getView(i,this);
            v.setOnClickListener(this);
            addView(v);
        }
    }

    private class TagLine{

        private List<View> views = new ArrayList<>(0);

        private int lineHeight;

        public void addView(View v){
            views.add(v);
        }

        public void onLayout(int top,int left){
            for (int i = 0;i<views.size();i++){
                View childView = views.get(i);
                childView.layout(left,top,left+childView.getMeasuredWidth(),top+getLineHeight());
                childView.setTag(i);
                left +=childView.getMeasuredWidth()+horizontalSpace;
            }
        }

        public int getLineHeight() {
            //行高为最高的孩子
            if (lineHeight == 0) {
                for (View childView : views) {
                    lineHeight = lineHeight < childView.getMeasuredHeight() ? childView.getMeasuredHeight() : lineHeight;
                }
            }
            return lineHeight;
        }

        public int getViewCount(){
            return views.size();
        }
    }

    public interface TagLayoutAdapter<T extends Object>{

        int getCount();

        View getView(int position, ViewGroup parent);

        T getItem(int postion);

    }
}

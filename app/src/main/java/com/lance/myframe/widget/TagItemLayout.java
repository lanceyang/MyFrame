package com.lance.myframe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/9/19.
 */
public class TagItemLayout extends FrameLayout implements Checkable {

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    private boolean isChecked;

    public TagItemLayout(Context context) {
        super(context);
    }

    public TagItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
    @Override
    public void setChecked(boolean checked) {
        if (isChecked&checked)
            return;
        isChecked = checked;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}

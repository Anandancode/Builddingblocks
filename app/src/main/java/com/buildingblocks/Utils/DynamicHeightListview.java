package com.buildingblocks.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by CAS61 on 10/10/2017.
 */
public class DynamicHeightListview extends ListView {

    private android.view.ViewGroup.LayoutParams params;
    private int old_count = 0;

    public DynamicHeightListview(Context context) {
        super(context);
    }

    public DynamicHeightListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DynamicHeightListview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getCount() != old_count) {
            old_count = getCount();
            params = getLayoutParams();
            ListAdapter listAdapter = getAdapter();
            int totalHeight = 0;
            int desiredWidth = MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST);
            for (int i = 0; i < getCount(); i++) {
                View listItem = listAdapter.getView(i, null, this);
                listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();
            }
            params.height = totalHeight + (getDividerHeight() * (listAdapter.getCount() - 1));
            setLayoutParams(params);
        }
    }
}

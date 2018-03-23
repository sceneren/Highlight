package com.zhy.highlight.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.zhy.highlight.R;

/**
 * 提示信息，有"知道了" 按钮
 *
 * @author yline 2018/3/21 -- 17:04
 * @version 1.0.0
 */
public class HintKnowView extends RelativeLayout {
    public HintKnowView(Context context) {
        super(context);
        initView();
    }

    public HintKnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_hint_known, this, true);
    }

    public void setOnKnowClickListener(OnClickListener listener) {
        findViewById(R.id.view_hint_known_click).setOnClickListener(listener);
    }
}

package com.zhy.highlight.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.zhy.highlight.R;

/**
 * 提示信息为："账户信息移动到这里啦~ 点击即可查看"
 *
 * @author yline 2018/3/21 -- 16:59
 * @version 1.0.0
 */
public class HintAccountView extends RelativeLayout {
    public HintAccountView(Context context) {
        super(context);
        initView();
    }

    public HintAccountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_hint_account, this, true);
    }
}

package com.zhy.highlight;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.zhy.highlight.view.HintKnowView;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.helper.HintMargin;
import zhy.com.highlight.helper.LightShape;
import zhy.com.highlight.helper.SingleViewInfo;

public class TestFragment extends BaseFragment {
    private HighLight mHighLight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));

        return imageView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showKnownAll(view);
    }

    private void showKnownAll(final View view) {
        Log.v("xxx-", "showKnownAll");

        mHighLight = new HighLight(getActivity());

        // 控件左边的案例
        HintKnowView leftHintView = new HintKnowView(getActivity());
        SingleViewInfo leftViewInfo = mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), leftHintView, HintMargin.create(HintMargin.LEFT));

        // 控件右边的案例
        HintKnowView rightHintView = new HintKnowView(getActivity());
        SingleViewInfo rightViewInfo = mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), rightHintView, HintMargin.create(HintMargin.RIGHT));

        // 控件顶部的案例
        HintKnowView topHintView = new HintKnowView(getActivity());
        SingleViewInfo topViewInfo = mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), topHintView, HintMargin.create(HintMargin.TOP));

        // 控件底部的案例
        HintKnowView bottomHintView = new HintKnowView(getActivity());
        SingleViewInfo bottomViewInfo = mHighLight.attachViewInfo(view, LightShape.create(LightShape.RECT), bottomHintView, HintMargin.create(HintMargin.BOTTOM));

        // 单个点击事件，这里只实现了一个，因此只有点击左边案例的那个，才会消失
        leftHintView.setOnKnowClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SDKManager.toast("clicked and remove HightLight view by yourself");
                mHighLight.remove();
            }
        });

        // 展示出来
        mHighLight.show(leftViewInfo, rightViewInfo, topViewInfo, bottomViewInfo);
    }
}

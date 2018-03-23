package com.zhy.highlight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yline.application.SDKManager;
import com.zhy.highlight.view.HintAccountView;
import com.zhy.highlight.view.HintKnowView;

import java.util.ArrayList;
import java.util.List;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.helper.HintMargin;
import zhy.com.highlight.helper.LightShape;
import zhy.com.highlight.helper.SingleViewInfo;

/**
 * 主页面
 *
 * @author yline 2018/3/22 -- 19:43
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity {
    private HighLight mHighLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 显示我知道了提示高亮布局
     */
    public void showKnownTipView(View view) {
        LightShape circleShape = LightShape.create(LightShape.CIRCLE);
        circleShape.setBlurRadius(0);

        List<SingleViewInfo> viewInfoList = new ArrayList<>();

        mHighLight = new HighLight(MainActivity.this);
        mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.LEFT), viewInfoList);
        mHighLight.attachViewInfo(R.id.btn_light, circleShape, new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.RIGHT), viewInfoList);
        mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.TOP), viewInfoList);
        mHighLight.attachViewInfo(view, LightShape.create(LightShape.RECT), new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM), viewInfoList);
        mHighLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked and remove HightLight view by yourself", Toast.LENGTH_SHORT).show();
                mHighLight.remove();
            }
        });

        mHighLight.show(viewInfoList);
    }

    /**
     * 显示 next模式 我知道了提示高亮布局
     */
    public void showNextKnownTipView(View view) {
        List<SingleViewInfo> viewInfoList = new ArrayList<>();

        mHighLight = new HighLight(MainActivity.this);

        mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.LEFT), viewInfoList);//矩形去除圆角
        mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.OVAL), new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.RIGHT), viewInfoList);
        mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.TOP), viewInfoList);
        mHighLight.attachViewInfo(view, LightShape.create(LightShape.OVAL), new HintKnowView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM), viewInfoList);

        mHighLight.show(viewInfoList);
    }

    public void showTipView(View view) {
        List<SingleViewInfo> viewInfoList = new ArrayList<>();

        mHighLight = new HighLight(MainActivity.this);

        mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.LEFT), viewInfoList);
        mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.RIGHT), viewInfoList);
        mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.TOP), viewInfoList);
        mHighLight.attachViewInfo(view, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM), viewInfoList);

        mHighLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHighLight.remove();
            }
        });

        mHighLight.show(viewInfoList);
    }

    /**
     * 显示next模式提示布局
     */
    public void showNextTipView(final View view) {
        List<SingleViewInfo> viewInfoList = new ArrayList<>();

        mHighLight = new HighLight(MainActivity.this);
        mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.LEFT), viewInfoList);
        mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.RIGHT), viewInfoList);

        mHighLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SDKManager.toast("clicked and show next tip view by yourself");
                List<SingleViewInfo> viewInfoList = new ArrayList<>();

                mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.TOP), viewInfoList);
                mHighLight.attachViewInfo(view, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM), viewInfoList);

                mHighLight.show(viewInfoList);
                mHighLight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHighLight.remove();
                    }
                });
            }
        });

        mHighLight.show(viewInfoList);
    }

    public void clickKnown(View view) {
        mHighLight.remove();
    }
}

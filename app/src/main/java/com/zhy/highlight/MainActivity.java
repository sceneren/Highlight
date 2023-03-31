package com.zhy.highlight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zhy.highlight.view.HintAccountView;
import com.zhy.highlight.view.HintKnowView;

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

        // 带“知道”按钮，一次出现所有
        findViewById(R.id.btn_show_known_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKnownAll(v);
            }
        });

        // 带“知道”按钮，分3次出现，个数为：1,2,1
        findViewById(R.id.btn_show_known_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKnowTips(v);
            }
        });

        // 一次性，显示普通所有布局；模式为1+2+1
        findViewById(R.id.btn_show_normal_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalTipsAll(v);
            }
        });

        // 分三步，显示普通布局；模式为1+2+1
        findViewById(R.id.btn_show_normal_tips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalTips(v);
            }
        });

        // 分三步，显示普通布局；模式为1+1+1+1
        findViewById(R.id.btn_show_normal_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalOther(v);
            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new TestFragment()).commit();
    }

    /**********************************  一次性，显示“知道” 所有布局 ***********************************************/
    private void showKnownAll(final View view) {
        mHighLight = new HighLight(MainActivity.this);

        // 控件左边的案例
        HintKnowView leftHintView = new HintKnowView(this);
        SingleViewInfo leftViewInfo = mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), leftHintView, HintMargin.create(HintMargin.LEFT));

        // 控件右边的案例
        HintKnowView rightHintView = new HintKnowView(this);
        SingleViewInfo rightViewInfo = mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), rightHintView, HintMargin.create(HintMargin.RIGHT));

        // 控件顶部的案例
        HintKnowView topHintView = new HintKnowView(this);
        SingleViewInfo topViewInfo = mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), topHintView, HintMargin.create(HintMargin.TOP));

        // 控件底部的案例
        HintKnowView bottomHintView = new HintKnowView(this);
        SingleViewInfo bottomViewInfo = mHighLight.attachViewInfo(view, LightShape.create(LightShape.RECT), bottomHintView, HintMargin.create(HintMargin.BOTTOM));

        // 单个点击事件，这里只实现了一个，因此只有点击左边案例的那个，才会消失
        leftHintView.setOnKnowClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked and remove HightLight view by yourself", Toast.LENGTH_SHORT).show();
                mHighLight.remove();
            }
        });

        // 展示出来
        mHighLight.show(leftViewInfo, rightViewInfo, topViewInfo, bottomViewInfo);
    }

    /**********************************  分三步，显示“知道”布局；模式为1+2+1 ***********************************************/
    private void showKnowTips(View view) {
        mHighLight = new HighLight(MainActivity.this);

        // 控件左边的案例
        HintKnowView leftHintView = new HintKnowView(this);
        SingleViewInfo leftViewInfo = mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), leftHintView, HintMargin.create(HintMargin.LEFT));


        mHighLight.show(leftViewInfo);
        leftHintView.setOnKnowClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKnowTipsTwo(mHighLight); // 展示第二个界面
            }
        });
    }

    private void showKnowTipsTwo(final HighLight highLight) {
        // 控件右边的案例
        HintKnowView rightHintView = new HintKnowView(MainActivity.this);
        SingleViewInfo rightViewInfo = mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), rightHintView, HintMargin.create(HintMargin.RIGHT));

        // 控件顶部的案例
        HintKnowView topHintView = new HintKnowView(MainActivity.this);
        SingleViewInfo topViewInfo = mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), topHintView, HintMargin.create(HintMargin.TOP));

        highLight.show(rightViewInfo, topViewInfo);
        rightHintView.setOnKnowClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKnowTipsThree(highLight);
            }
        });
    }

    private void showKnowTipsThree(final HighLight highLight) {
        // 控件底部的案例
        HintKnowView bottomHintView = new HintKnowView(this);
        SingleViewInfo bottomViewInfo = mHighLight.attachViewInfo(R.id.btn_show_known_tip, LightShape.create(LightShape.RECT), bottomHintView, HintMargin.create(HintMargin.BOTTOM));

        highLight.show(bottomViewInfo);
        bottomHintView.setOnKnowClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highLight.remove();
            }
        });
    }

    /**********************************  一次性，显示普通所有布局 ***********************************************/
    private void showNormalTipsAll(View view) {
        mHighLight = new HighLight(MainActivity.this);

        SingleViewInfo leftViewInfo = mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.LEFT));
        SingleViewInfo rightViewInfo = mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.RIGHT));
        SingleViewInfo topViewInfo = mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.TOP));
        SingleViewInfo bottomViewInfo = mHighLight.attachViewInfo(view, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM));

        mHighLight.show(leftViewInfo, rightViewInfo, topViewInfo, bottomViewInfo);
        mHighLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHighLight.remove();
            }
        });
    }

    /**********************************  分三步，显示普通布局；模式为1+2+1 ***********************************************/
    private void showNormalTips(final View view) {
        mHighLight = new HighLight(MainActivity.this);

        SingleViewInfo leftViewInfo = mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.LEFT));

        mHighLight.show(leftViewInfo);
        mHighLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalTipsTwo(mHighLight);
            }
        });
    }

    private void showNormalTipsTwo(final HighLight highLight) {
        SingleViewInfo rightViewInfo = mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.RIGHT));
        SingleViewInfo topViewInfo = mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.TOP));

        highLight.show(rightViewInfo, topViewInfo);
        highLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalTipsThree(highLight);
            }
        });
    }

    private void showNormalTipsThree(final HighLight highLight) {
        SingleViewInfo bottomViewInfo = mHighLight.attachViewInfo(R.id.btn_show_normal_tips, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM));
        highLight.show(bottomViewInfo);
        highLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highLight.remove();
            }
        });
    }

    /**********************************  分三步，显示普通布局；模式为1+1+1+1 ***********************************************/
    private int mOtherStep;

    private void showNormalOther(final View view) {
        mHighLight = new HighLight(MainActivity.this);

        SingleViewInfo leftViewInfo = mHighLight.attachViewInfo(R.id.btn_rightLight, LightShape.create(LightShape.RECT), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.LEFT));

        mOtherStep = 0;
        mHighLight.show(leftViewInfo);
        mHighLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mOtherStep) {
                    case 0: // 点击第 1 张，准备加载第 2 张
                        mOtherStep++;
                        SingleViewInfo rightViewInfo = mHighLight.attachViewInfo(R.id.btn_light, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.RIGHT));
                        mHighLight.show(rightViewInfo);
                        break;
                    case 1: // 点击第 2 张，准备加载第 3 张
                        mOtherStep++;
                        SingleViewInfo topViewInfo = mHighLight.attachViewInfo(R.id.btn_bottomLight, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.TOP));
                        mHighLight.show(topViewInfo);
                        break;
                    case 2: // 点击第 3 张，准备加载第 4 张
                        mOtherStep++;
                        SingleViewInfo bottomViewInfo = mHighLight.attachViewInfo(view, LightShape.create(LightShape.CIRCLE), new HintAccountView(MainActivity.this), HintMargin.create(HintMargin.BOTTOM));
                        mHighLight.show(bottomViewInfo);
                        break;
                    default: // 4 张结束，移除
                        mHighLight.remove();
                        break;
                }
            }
        });
    }
}

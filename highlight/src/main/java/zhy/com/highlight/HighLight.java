package zhy.com.highlight;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.util.Arrays;
import java.util.List;

import zhy.com.highlight.helper.HintMargin;
import zhy.com.highlight.helper.LightShape;
import zhy.com.highlight.helper.SingleViewInfo;
import zhy.com.highlight.view.HighLightView;

/**
 * 添加高亮的帮助类
 *
 * @author yline 2018/3/22 -- 19:34
 * @version 1.0.0
 */
public class HighLight {
    private View mDecorView; // 最底层的父控件
    private HighLightView mHighLightView;

    private boolean isShowing;// 是否正在显示
    private View.OnClickListener mOnClickListener; // 为了防止点击事件穿透，默认给 HighLightView设置一个点击事件

    private View.OnClickListener mOnLayoutFinishedListener;

    public HighLight(@NonNull Activity activity) {
        mDecorView = activity.getWindow().getDecorView();

        mHighLightView = new HighLightView(activity);
        mHighLightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnClickListener) {
                    mOnClickListener.onClick(v);
                }
            }
        });

        mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mDecorView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mDecorView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                if (null != mOnLayoutFinishedListener) {
                    mOnLayoutFinishedListener.onClick(null);
                }
            }
        });
    }

    /**
     * 添加 单条，待添加的信息
     *
     * @param viewId     被高亮的控件
     * @param lightShape 高亮的形状
     * @param hintView   提示信息的控件
     * @param hintRect   提示信息的位置信息
     * @return 单条信息，之后放入show中使用
     */
    public SingleViewInfo attachViewInfo(int viewId, LightShape lightShape, @NonNull View hintView, HintMargin hintRect) {
        View lightView = mDecorView.findViewById(viewId);
        return attachViewInfo(lightView, lightShape, hintView, hintRect);
    }

    /**
     * 添加 单条，待添加的信息
     *
     * @param lightView  被高亮的控件
     * @param lightShape 高亮的形状
     * @param hintView   提示信息的控件
     * @param hintRect   提示信息的位置信息
     * @return 单条信息，之后放入show中使用
     */
    public SingleViewInfo attachViewInfo(@NonNull View lightView, LightShape lightShape, @NonNull View hintView, HintMargin hintRect) {
        return new SingleViewInfo(mDecorView, lightView, lightShape, hintView, hintRect);
    }

    /**
     * 展示数据，直接展示就行
     *
     * @param viewArray 传入的数据
     */
    public void show(SingleViewInfo... viewArray) {
        show(Arrays.asList(viewArray));
    }

    /**
     * 展示数据，直接展示就行
     *
     * @param viewInfoList 传入的数据
     */
    public void show(List<SingleViewInfo> viewInfoList) {
        // 内容为空
        if (null == viewInfoList || viewInfoList.isEmpty()) {
            return;
        }

        if (null != mHighLightView) {
            if (!isShowing) {
                if (mDecorView instanceof FrameLayout) {
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ((ViewGroup) mDecorView).addView(mHighLightView, ((ViewGroup) mDecorView).getChildCount(), lp);

                    isShowing = true;

                    mHighLightView.addViewForTip(viewInfoList);
                    mHighLightView.invalidate();
                }
            } else {
                mHighLightView.addViewForTip(viewInfoList);
                mHighLightView.invalidate();
            }
        }
    }

    /**
     * 移除数据
     */
    public void remove() {
        if (mDecorView instanceof FrameLayout) { // isShowing
            ((FrameLayout) mDecorView).removeView(mHighLightView);
            isShowing = false;

            mHighLightView = null;
        }
    }

    public void setMaskColor(int maskColor) {
        if (null != mHighLightView) {
            mHighLightView.setMaskColor(maskColor);
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public void setOnTouchListener(View.OnTouchListener listener) {
        if (null != mHighLightView) {
            mHighLightView.setOnTouchListener(listener);
        }
    }

    public void setOnLayoutFinishedListener(View.OnClickListener listener) {
        this.mOnLayoutFinishedListener = listener;
    }
}

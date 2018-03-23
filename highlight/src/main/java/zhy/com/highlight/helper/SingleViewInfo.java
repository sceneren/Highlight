package zhy.com.highlight.helper;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.view.View;

import zhy.com.highlight.view.HighLightView;

/**
 * 储存单个，高亮部分+提示信息，的信息
 *
 * @author yline 2018/3/22 -- 10:02
 * @version 1.0.0
 */
public class SingleViewInfo {
    private View mDecorView; // 最底层父布局

    private View mHintView; // 提示信息，控件
    private HintMargin mHintMarginInfo; // 确定，提示信息，在屏幕中的位置

    private View mLightView; // 高亮的控件
    private RectF mLightRect; // 高亮的控件，在屏幕中的位置
    private LightShape mLightShape; // 高亮的形状

    public SingleViewInfo(View parentView, View lightView, LightShape lightShape, View hintView, HintMargin hintRect) {
        this.mDecorView = parentView;
        this.mLightView = lightView;
        this.mLightShape = (null == lightShape ? LightShape.create(LightShape.RECT) : lightShape);

        this.mHintView = hintView;
        this.mHintMarginInfo = (null == hintRect ? HintMargin.create(HintMargin.BOTTOM) : hintRect);

        updateLightRect();
    }

    /**
     * 添加提示信息
     *
     * @param highLightView 提示信息的控件
     */
    public void attachHintView(@NonNull HighLightView highLightView) {
        mHintMarginInfo.resetLayoutParams(mHintView);
        highLightView.addView(mHintView);
    }

    /**
     * 对提示信息，重新布局
     *
     * @param view 提示信息的控件
     */
    public void onLayout(@NonNull View view) {
        updateLightRect();
        mHintMarginInfo.resetLayoutParams(view);
    }

    /**
     * 绘制高亮部分
     *
     * @param canvas 被绘制的画布
     */
    public void drawLightShape(Canvas canvas) {
        mLightShape.draw(canvas, mLightRect);
    }

    /**
     * 更新，高亮+提示信息，位置
     */
    private void updateLightRect() {
        if (null != mLightView && null != mDecorView && null != mHintView) {
            mLightRect = new RectF(getRectOfScreen(mDecorView, mLightView));

            int parentWidth = mDecorView.getWidth();
            int parentHeight = mDecorView.getHeight();

            int hintWidth = mHintView.getWidth();
            int hintHeight = mHintView.getHeight();

            mHintMarginInfo.initMarginInfo(mLightRect, parentWidth, parentHeight, hintWidth, hintHeight);
        }
    }

    /**
     * 计算子控件，在屏幕中的位置
     *
     * @param parentView 最底层父控件
     * @param childView  子控件
     * @return 位置信息
     */
    private static Rect getRectOfScreen(@NonNull View parentView, @NonNull View childView) {
        Rect resultRect = new Rect();

        View tempView = childView;
        while (tempView != parentView) {
            resultRect.left += tempView.getLeft();
            resultRect.top += tempView.getTop();

            tempView = (View) tempView.getParent();
            // TODO 这里可以处理异常情况;
        }

        resultRect.right = resultRect.left + childView.getMeasuredWidth();
        resultRect.bottom = resultRect.top + childView.getMeasuredHeight();
        return resultRect;
    }
}

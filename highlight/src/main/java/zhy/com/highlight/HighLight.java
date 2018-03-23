package zhy.com.highlight;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
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

    public HighLight(@NonNull Activity activity) {
        mDecorView = activity.getWindow().getDecorView();

        mHighLightView = new HighLightView(activity);
    }

    public void attachViewInfo(int viewId, LightShape lightShape, @NonNull View hintView, HintMargin hintRect, List<SingleViewInfo> viewInfoList) {
        View lightView = mDecorView.findViewById(viewId);
        attachViewInfo(lightView, lightShape, hintView, hintRect, viewInfoList);
    }

    public void attachViewInfo(@NonNull View lightView, LightShape lightShape, @NonNull View hintView, HintMargin hintRect, List<SingleViewInfo> viewInfoList) {
        viewInfoList.add(new SingleViewInfo(mDecorView, lightView, lightShape, hintView, hintRect));
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
                }
            } else {
                mHighLightView.addViewForTip(viewInfoList);
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
        if (null != mHighLightView) {
            mHighLightView.setOnClickListener(listener);
        }
    }
}

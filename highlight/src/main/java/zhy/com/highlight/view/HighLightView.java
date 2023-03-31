package zhy.com.highlight.view;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.NonNull;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.List;

import zhy.com.highlight.helper.SingleViewInfo;

/**
 * 整块屏幕的载体
 *
 * @author yline 2018/3/22 -- 19:16
 * @version 1.0.0
 */
public class HighLightView extends FrameLayout {
     private int mMaskColor = 0xCC000000; // 背景颜色
//    private int mMaskColor = 0xbb222222; // 背景颜色

    private boolean isNextShow; // 是否添加了数据，准备下次绘制

    private List<SingleViewInfo> mViewInfoList; // 每次绘制的内容

    public HighLightView(Context context) {
        super(context);
        setWillNotDraw(false);
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 关闭硬件加速
    }

    public void setMaskColor(int maskColor) {
        this.mMaskColor = maskColor;
    }

    /**
     * 添加数据
     *
     * @param viewInfoList 将要绘制在图层的  图样
     */
    public void addViewForTip(@NonNull List<SingleViewInfo> viewInfoList) {
        this.mViewInfoList = viewInfoList;
        this.isNextShow = true;

        removeAllViews();
        for (SingleViewInfo viewInfo : viewInfoList) {
            viewInfo.attachHintView(this);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Log.v("xxx-", "onLayout");
        if (changed || isNextShow) {
            updateTipPos();

            isNextShow = false;
        }
    }

    /**
     * 将新添加的子类，更新位置
     */
    private void updateTipPos() {
        SingleViewInfo viewInfo;
        for (int i = 0; i < getChildCount(); i++) {
            viewInfo = mViewInfoList.get(i);
            viewInfo.onLayout(getChildAt(i));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas); // 内部啥也没有调用
        Log.v("xxx-", "onDraw");

        // 准备工作
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        // 绘制背景色
        canvas.drawColor(mMaskColor);

        // 绘制高亮形状
        if (null != mViewInfoList) {
            for (SingleViewInfo viewInfo : mViewInfoList) {
                viewInfo.drawLightShape(canvas);
            }
        }

        canvas.restoreToCount(saved);
    }
}

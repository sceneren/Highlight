package zhy.com.highlight.helper;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.IntRange;

/**
 * 高亮的形状
 * 默认提供了三种：“圆形”，“椭圆形”，“矩形”
 * <p>
 * 自己实现，就是，自定义形状
 *
 * @author yline 2018/3/20 -- 11:12
 * @version 1.0.0
 */
public abstract class LightShape {
    public static final int CIRCLE = 0; // 圆形
    public static final int OVAL = 1; // 椭圆
    public static final int RECT = 2; // 矩形

    Paint mPaint; // 绘制空洞的画笔

    public LightShape() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));
    }

    /**
     * 默认提供创建 高亮形状的实现
     *
     * @param type {LightShape.CIRCLE, LightShape.CIRCLE, LightShape.CIRCLE}
     * @return 对应的实现类
     */
    public static LightShape create(@IntRange(from = 0, to = 2) final int type) {
        switch (type) {
            case CIRCLE:
                return new LightShapeImpl.Circle();
            case OVAL:
                return new LightShapeImpl.Oval();
            case RECT:
                return new LightShapeImpl.Rect();
            default:
                return new LightShapeImpl.Rect();
        }
    }

    /**
     * 设置 模糊半径 默认15
     *
     * @param blurRadius 高斯模糊半径
     */
    public void setBlurRadius(int blurRadius) {
        if (blurRadius > 0) {
            mPaint.setMaskFilter(new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID));
        } else {
            mPaint.setMaskFilter(null);
        }
    }

    /**
     * 绘制相应形状的高亮区域
     *
     * @param canvas     等待被绘制的画布
     * @param lightRectF 位置信息
     */
    public abstract void draw(Canvas canvas, RectF lightRectF);
}

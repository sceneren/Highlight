package zhy.com.highlight.helper;

import android.graphics.RectF;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 确定，提示信息，在屏幕中的位置
 * 默认提供了“正下方”，“正上方”，“正左侧”，“正右侧”四种方式
 * <p>
 * 设置，和高亮部分，的距离；有两种方式：重写 和 设置自定义View的padding量
 *
 * @author yline 2018/3/21 -- 10:34
 * @version 1.0.0
 */
public abstract class HintMargin {
    public static final int BOTTOM = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 3;

    // 等待被赋值的信息
    private int topMargin; // 距离顶部距离
    private int leftMargin; // 距离左侧距离
    private int rightMargin; // 距离右侧距离
    private int bottomMargin; // 距离底部距离

    private int gravity; // 确定，相对的“哪边”，默认相对“左侧+顶部”

    public HintMargin() {
        gravity = Gravity.LEFT | Gravity.TOP;
    }

    /**
     * 给内部调用，初始化内部信息
     *
     * @param rectF        高亮部分，所在屏幕中的位置
     * @param parentWidth  最底层父控件宽度
     * @param parentHeight 最底层父控件高度
     * @param hintWidth    提示信息宽度
     * @param hintHeight   提示信息高度
     */
    public abstract void initMarginInfo(RectF rectF, int parentWidth, int parentHeight, int hintWidth, int hintHeight);

    /**
     * 设置，提示信息，在屏幕中的位置
     *
     * @param view 提示信息的控件
     */
    public void resetLayoutParams(@NonNull View view) {
        FrameLayout.LayoutParams params;

        // 校验params是否为空
        ViewGroup.LayoutParams viewParams = view.getLayoutParams();
        if (null != viewParams && (viewParams instanceof FrameLayout.LayoutParams)) {
            params = (FrameLayout.LayoutParams) viewParams;
        } else {
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        // 设置偏移量
        params.leftMargin = leftMargin;
        params.topMargin = topMargin;
        params.rightMargin = rightMargin;
        params.bottomMargin = bottomMargin;
        params.gravity = gravity;

        view.setLayoutParams(params);
    }

    /**
     * 默认提供创建 提示信息的实现
     *
     * @param type {HintMargin.BOTTOM, HintMargin.LEFT, HintMargin.RIGHT, HintMargin.TOP}
     * @return 对应的实现
     */
    public static HintMargin create(@IntRange(from = 0, to = 3) int type) {
        switch (type) {
            case BOTTOM:
                return new HintMarginImpl.Bottom();
            case LEFT:
                return new HintMarginImpl.Left();
            case RIGHT:
                return new HintMarginImpl.Right();
            case TOP:
                return new HintMarginImpl.Top();
            default:
                return new HintMarginImpl.Bottom();
        }
    }

    /*********************************************** 子类调用的方法 *****************************************************/
    protected void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    protected void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    protected void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    protected void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    protected void setGravity(int gravity) {
        this.gravity = gravity;
    }
}

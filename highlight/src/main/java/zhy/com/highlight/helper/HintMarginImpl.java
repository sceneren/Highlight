package zhy.com.highlight.helper;

import android.graphics.RectF;
import android.view.Gravity;

/**
 * 确定，提示信息，在屏幕中的位置，的，实现类
 *
 * @author yline 2018/3/21 -- 10:53
 * @version 1.0.0
 */
public class HintMarginImpl {
    /**
     * 提示信息，在，高亮控件，正下方
     */
    static class Bottom extends HintMargin {
        @Override
        public void initMarginInfo(RectF rectF, int parentWidth, int parentHeight, int hintWidth, int hintHeight) {
            int leftMargin = (int) (rectF.centerX() - hintWidth / 2);
            setLeftMargin(leftMargin);

            int topMargin = (int) rectF.bottom;
            setTopMargin(topMargin);

            setGravity(Gravity.LEFT | Gravity.TOP);
        }
    }

    /**
     * 提示信息，在，高亮控件，左侧
     */
    static class Left extends HintMargin {
        @Override
        public void initMarginInfo(RectF rectF, int parentWidth, int parentHeight, int hintWidth, int hintHeight) {
            int rightMargin = (int) (parentWidth - rectF.left);
            setRightMargin(rightMargin);

            int topMargin = (int) (rectF.centerY() - hintHeight / 2);
            setTopMargin(topMargin);

            setGravity(Gravity.RIGHT | Gravity.TOP);
        }
    }

    /**
     * 提示信息，在，高亮控件，右侧
     */
    static class Right extends HintMargin {
        @Override
        public void initMarginInfo(RectF rectF, int parentWidth, int parentHeight, int hintWidth, int hintHeight) {
            int leftMargin = (int) rectF.right;
            setLeftMargin(leftMargin);

            int topMargin = (int) (rectF.centerY() - hintHeight / 2);
            setTopMargin(topMargin);

            setGravity(Gravity.LEFT | Gravity.TOP);
        }
    }

    /**
     * 提示信息，在，高亮控件，上方
     */
    static class Top extends HintMargin {
        @Override
        public void initMarginInfo(RectF rectF, int parentWidth, int parentHeight, int hintWidth, int hintHeight) {
            int leftMargin = (int) (rectF.centerX() - hintWidth / 2);
            setLeftMargin(leftMargin);

            int bottomMargin = (int) (parentHeight - rectF.top);
            setBottomMargin(bottomMargin);

            setGravity(Gravity.LEFT | Gravity.BOTTOM);
        }
    }
}

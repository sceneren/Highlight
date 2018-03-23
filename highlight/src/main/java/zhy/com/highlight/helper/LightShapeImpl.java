package zhy.com.highlight.helper;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * 各个形状实现类
 *
 * @author yline 2018/3/20 -- 13:47
 * @version 1.0.0
 */
public class LightShapeImpl {
    static class Rect extends LightShape {
        private final int mRadiusX = 6; // x方向，圆角半径
        private final int mRadiusY = 6; // y方向，圆角半径

        @Override
        public void draw(Canvas canvas, RectF rectF) {
            canvas.drawRoundRect(rectF, mRadiusX, mRadiusY, mPaint);
        }
    }

    static class Circle extends LightShape {
        @Override
        public void draw(Canvas canvas, RectF rectF) {
            canvas.drawCircle(rectF.left + (rectF.width() / 2), rectF.top + (rectF.height() / 2),
                    Math.max(rectF.width(), rectF.height()) / 2, mPaint);
        }
    }

    static class Oval extends LightShape {
        @Override
        public void draw(Canvas canvas, RectF rectF) {
            canvas.drawOval(rectF, mPaint);
        }
    }
}

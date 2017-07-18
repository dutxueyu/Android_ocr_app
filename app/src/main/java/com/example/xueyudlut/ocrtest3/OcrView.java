package com.example.xueyudlut.ocrtest3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xueyudlut on 2017/7/14.
 */
public class OcrView extends View {
    //    声明Paint对象
    private Paint mPaint = null;
    private int StrokeWidth = 5;
    private boolean IsUsed = false;
    public Rect rect = new Rect(0, 0, 0, 0);//手动绘制矩形

    public OcrView(Context context) {
        super(context);
        //构建对象
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置无锯齿
        mPaint.setAntiAlias(true);
//        canvas.drawARGB(25, 255, 227, 0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(StrokeWidth);
        // mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(100);
        // 绘制绿色实心矩形
        // canvas.drawRect(100, 200, 400, 200 + 400, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!IsUsed) return false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rect.right += StrokeWidth;
                rect.bottom += StrokeWidth;
                invalidate(rect);
                rect.left = x;
                rect.top = y;
                rect.right = rect.left;
                rect.bottom = rect.top;

            case MotionEvent.ACTION_MOVE:
                Rect old =
                        new Rect(rect.left, rect.top, rect.right + StrokeWidth, rect.bottom + StrokeWidth);
                rect.right = x;
                rect.bottom = y;
                old.union(x, y);
                invalidate(old);
                break;

            case MotionEvent.ACTION_UP:
                IsUsed = false;
                break;
            default:
                break;
        }
        return IsUsed;  //未启用时不处理触摸信息
        // return  true;//处理了触摸信息，消息不再传递
    }

    public void SelectRect() {
        IsUsed = true;
    }
}



package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    private Paint mPaint;
    private int[] datas = {100,200,300,300,200};
    private String[] names = {"a","aa","aaa","aaaa","aaaaa"};
    private int width = 100;
    private int space = 20;
    private int coordLengthX = 800;
    private int coordLengthY = 500;
    private int originX = 100;
    private int originY = 100 + coordLengthY;
    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        canvas.translate(originX,originY);

        mPaint.setColor(Color.WHITE);
        canvas.drawLine(0,0,0,-coordLengthY,mPaint);
        canvas.drawLine(0,0,coordLengthX,0,mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(40);

        float left = space;
        for (int i=0; i<names.length; i++){
            canvas.drawRect(left, -datas[i],left+width,0,mPaint);
            float textLeft = left + (width - getTextBounds(names[i],mPaint).width())/2;
            canvas.drawText(names[i],textLeft,50,mPaint);

            left += width + space;
        }
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }
    private Rect getTextBounds(String name, Paint paint){
        Rect rect = new Rect();
        paint.getTextBounds(name,0,name.length(),rect);
        return rect;
    }
}

package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    private static final int RADIUS = 200;
    private static final int LINE_LENGTH = 50;
    private static final int OFFSET = 20;
    private static final int OFFSET_INDEX = 2;
    private int[] datas={10,15,15,25,35};
    private String[] names={"a","b","c","d","e"};
    private int[] colors = {Color.WHITE, Color.YELLOW, Color.BLUE, Color.GRAY, Color.GREEN};
    private int curAngle=0;

    private Paint arcPaint, linePaint, textPaint;
    private RectF mRectF;
    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setAntiAlias(true);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(3);
        linePaint.setColor(Color.WHITE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(30);
        textPaint.setColor(Color.WHITE);

        mRectF = new RectF(-RADIUS,-RADIUS,RADIUS,RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        canvas.translate(getWidth()/2,getHeight()/2);

        for (int i=0; i<names.length; i++){
            float sweepAngle = 360 * datas[i]/100;
            double theta = (curAngle + sweepAngle/2) * Math.PI / 180;

            if (i == OFFSET_INDEX){
                canvas.save();
                canvas.translate((float)(OFFSET * Math.cos(theta)),(float) (OFFSET * Math.sin(theta)));
                drawContent(canvas, curAngle, sweepAngle , theta, i);
                canvas.restore();
            }else {
                drawContent(canvas, curAngle, sweepAngle, theta, i);
            }
            curAngle += sweepAngle;
        }
    }

    private void drawContent(Canvas canvas, int curAngle, float sweepAngle, double theta, int i) {

        arcPaint.setColor(colors[i]);
        canvas.drawArc(mRectF,curAngle,sweepAngle,true,arcPaint);

        float lineStartX = (float) (RADIUS * Math.cos(theta));
        float lineStartY = (float) (RADIUS * Math.sin(theta));
        float lineStopX = (float) ((RADIUS + LINE_LENGTH) * Math.cos(theta));
        float lineStopY = (float) ((RADIUS + LINE_LENGTH) * Math.sin(theta));
        canvas.drawLine(lineStartX,lineStartY,lineStopX,lineStopY,textPaint);

        float lineEndX;
        Rect rect = getTextBounds(names[i],textPaint);
        if (theta > Math.PI/2 && theta <= Math.PI * 3 / 2){
            lineEndX = lineStopX - LINE_LENGTH;
            canvas.drawLine(lineStopX, lineStopY, lineEndX, lineStopY,linePaint);
            canvas.drawText(names[i], lineEndX - rect.width() -10 ,lineStopY + rect.height()/2,textPaint);
        }else {
            lineEndX = lineStopX + LINE_LENGTH;
            canvas.drawLine(lineStopX, lineStopY, lineEndX, lineStopY,linePaint);
            canvas.drawText(names[i], lineEndX + 10 ,lineStopY + rect.height()/2,textPaint);

        }
    }

    private Rect getTextBounds(String name, Paint textPaint) {
        Rect rect = new Rect();
        textPaint.getTextBounds(name, 0, name.length(), rect);
        return rect;
    }
}

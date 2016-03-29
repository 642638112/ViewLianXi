package com.viewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zml on 2016/3/27.
 */
public class CircleProgress extends View{
    private int with;
    private  int heigt;
    private Context context;
    private Paint circlepaint;
    private float mCircleXY;
    private float mRadius;

    private Paint arcpaint;
    private RectF mArcRectF;
    private float mSweepAngle;
    private float mSweepValue = 66;
    TypedArray mTypedArray;

    private Paint mTextPaint;
    private String progress;
    private float textsize;

    private int roundColor;
    private int centercolor;


 public CircleProgress(Context context) {

        super(context);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CircleProgress);
        roundColor=mTypedArray.getColor(R.styleable.CircleProgress_roundColor, Color.RED);
        centercolor=mTypedArray.getColor(R.styleable.CircleProgress_centercolor, Color.RED);

    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CircleProgress);
        roundColor=mTypedArray.getColor(R.styleable.CircleProgress_roundColor, Color.RED);
        centercolor=mTypedArray.getColor(R.styleable.CircleProgress_centercolor, Color.RED);
        mTypedArray.recycle();

    }
    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        with = MeasureSpec.getSize(widthMeasureSpec);
        heigt = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(with, heigt);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);   float length = 0;
        if (heigt >= with) {
            length = with;
        } else {
            length = heigt;
        }
        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);
        circlepaint=new Paint();
        circlepaint.setAntiAlias(true);
        circlepaint.setColor(centercolor);

        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9));
        mSweepAngle = (mSweepValue / 100.f) * 360f;
        arcpaint=new Paint();
        arcpaint.setAntiAlias(true);
        arcpaint.setColor(roundColor);
        arcpaint.setStrokeWidth((float) (length * 0.1));
        arcpaint.setStyle(Paint.Style.STROKE);

        progress = getShowText();
        textsize = getShowTextSize();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(textsize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        // 绘制圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, circlepaint);
        // 绘制弧线
        canvas.drawArc(mArcRectF, 270, mSweepAngle, false, arcpaint);
        canvas.drawText(progress, 0, progress.length(),
                mCircleXY, mCircleXY + (textsize / 4), mTextPaint);
    }

    public float getShowTextSize() {
        this.invalidate();
        return 50;
    }
    public void setShowTextSize(int x) {
        this.invalidate();
        textsize=x;

    }

    public String getShowText() {
        this.invalidate();
        return progress;
    }
    public void setShowText(int x) {
        this.invalidate();
        progress=x+"";

    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }

        this.invalidate();

    }


}

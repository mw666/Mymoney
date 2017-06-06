package com.tuofu.lenovo.mymoney.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tuofu.lenovo.mymoney.R;

/**
 * Created by lenovo on 2017/4/18.
 */

public class RoundProgress extends View {

    private int circlecolor;
    private int secondcolor;
    private int textcolor;
    private float stokewidth;
    private float textsize;
 int progress=50;
    public RoundProgress(Context context) {
        this(context,null);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        circlecolor = typedArray.getColor(R.styleable.RoundProgress_ciclecolor, Color.RED);
        secondcolor = typedArray.getColor(R.styleable.RoundProgress_secondcolor, Color.BLUE);
        textcolor = typedArray.getColor(R.styleable.RoundProgress_textcolor, Color.RED);
        textsize = typedArray.getDimension(R.styleable.RoundProgress_textsize, 15);
        stokewidth = typedArray.getDimension(R.styleable.RoundProgress_stokewidth, 5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stokewidth);
        paint.setColor(circlecolor);
        canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/2-stokewidth/2,paint);
        String s=progress+"%";
        paint.setTextSize(textsize);
        float v = paint.measureText(s);
        paint.setColor(textcolor);
        paint.setStrokeWidth(0);
        canvas.drawText(s,getWidth()/2-v/2,getWidth()/2+textsize/2-stokewidth/2,paint);
        RectF rectf = new RectF( stokewidth/2, stokewidth/2,  getWidth() - stokewidth / 2,getWidth()-stokewidth/2);
        paint.setColor(secondcolor);
        paint.setStrokeWidth(stokewidth);
        canvas.drawArc(rectf,0,progress*360/100,false,paint);
    }
}

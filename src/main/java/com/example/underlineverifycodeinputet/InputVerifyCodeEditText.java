package com.example.underlineverifycodeinputet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jping on 16/7/19.
 */
public class InputVerifyCodeEditText extends EditText implements View.OnClickListener {

    private float paddingHorizontal;
    private float lineSpace;
    private Paint paint;
    private float lineWidth;
    private RectF rectF = new RectF();
    private Rect bounds = new Rect();
    private int mBgColor = Color.TRANSPARENT;
    private int mLineColor = Color.RED;
    public InputVerifyCodeEditText(Context context) {
        super(context);
        init(context);
    }

    public InputVerifyCodeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        paint = new Paint();
        paint.setAntiAlias(true);
        lineWidth = 2;
        setCursorVisible(false);
        setOnClickListener(this);
        setBackgroundResource(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(0, 0, w, h);
        lineSpace = w * 0.2f;
        paddingHorizontal = lineSpace / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(mBgColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rectF, getHeight() / 2, getHeight() / 2, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mLineColor);
        paint.setStrokeWidth(lineWidth);
        for(int i=0;i<4;i++){
            canvas.drawLine((paddingHorizontal+lineSpace)*i,getHeight(),paddingHorizontal*i+(i+1)*lineSpace,getHeight(),paint);
        }
        paint.setStrokeWidth(0);
        char[] chars = getText().toString().toCharArray();
        paint.setTextSize(getTextSize());
        paint.setColor(getTextColors().getDefaultColor());
        for(int i=0;i<chars.length;i++){
            paint.getTextBounds(chars,i,1,bounds);
            canvas.drawText(chars,i,1,lineSpace/3 + (paddingHorizontal + lineSpace) * i,getHeight()/2+bounds.height()/2,paint);
        }

    }

    @Override
    public void onClick(View v) {
        setSelection(getText().length());
    }
}

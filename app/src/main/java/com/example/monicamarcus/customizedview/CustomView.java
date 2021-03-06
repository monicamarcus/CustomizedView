package com.example.monicamarcus.customizedview;

import android.graphics.Canvas;
import android.text.TextPaint;
import android.view.View;
import android.view.MotionEvent;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;

/**
 * Created by monicamarcus on 2/9/17.
 */

public class CustomView extends View {
    private Paint paint;
    private Rect[] rectangle = new Rect[4];
    private TextPaint textPaint;
    String text = "Section ";

    public CustomView(Context context, AttributeSet attrs) {
        super(context,attrs);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        Style style = Style.STROKE;
        paint.setStyle(style);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);
        textPaint.setTextAlign(Align.CENTER);

        initRectangles();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for (int i = 0; i < rectangle.length; i++) {
            canvas.drawRect(rectangle[i], paint);
            int width = rectangle[i].width();
            int j = i + 1;
            int numOfChars = textPaint.breakText(text + j, true, width, null);
            int start = (text.length() - numOfChars) / 2;
            canvas.drawText(text + j, start, start + numOfChars, rectangle[i].exactCenterX(), rectangle[i].exactCenterY(), textPaint);
        }
    }

    private void initRectangles() {
        int left = 6;
        int up = 6;
        int x = 300;
        int y = 300;
        rectangle[0] = new Rect(left, up, x, y);
        rectangle[1] = new Rect(left + x, up, x + x, y);
        rectangle[2] = new Rect(left, up + y, x, y + y);
        rectangle[3] = new Rect(left + x, up + y, x + x, y + y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = Math.round(event.getX());
        int touchY = Math.round(event.getY());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (rectangle[0].contains(touchX,touchY)) {
                Toast.makeText(this.getContext(), "Touched section 1", Toast.LENGTH_LONG).show();
            }
            if (rectangle[1].contains(touchX,touchY)) {
                Toast.makeText(this.getContext(), "Touched section 2", Toast.LENGTH_LONG).show();
            }
            if (rectangle[2].contains(touchX,touchY)) {
                Toast.makeText(this.getContext(), "Touched section 3", Toast.LENGTH_LONG).show();
            }
            if (rectangle[3].contains(touchX,touchY)) {
                Toast.makeText(this.getContext(), "Touched section 4", Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }
}

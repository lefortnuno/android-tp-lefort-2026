package com.example.iao_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.Calendar;

public class CustomAnalogClock extends View {
    private int hour = 0;
    private int minute = 0;
    private Paint paint;

    public CustomAnalogClock(Context context) {
        super(context);
        init();
    }

    public CustomAnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        invalidate(); // Redessine l'horloge
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 10;
        int centerX = width / 2;
        int centerY = height / 2;

        // Dessiner le cercle de l'horloge
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawCircle(centerX, centerY, radius, paint);

        // Dessiner les aiguilles
        drawHand(canvas, centerX, centerY,
                (hour % 12 + minute / 60.0f) * 30, radius * 0.5f, 8, Color.BLACK); // Heure
        drawHand(canvas, centerX, centerY,
                minute * 6, radius * 0.7f, 5, Color.RED); // Minute
    }

    private void drawHand(Canvas canvas, float cx, float cy, float angle,
                          float length, float strokeWidth, int color) {
        angle = (float) Math.toRadians(angle - 90);
        float endX = cx + (float) (Math.cos(angle) * length);
        float endY = cy + (float) (Math.sin(angle) * length);

        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawLine(cx, cy, endX, endY, paint);
    }
}
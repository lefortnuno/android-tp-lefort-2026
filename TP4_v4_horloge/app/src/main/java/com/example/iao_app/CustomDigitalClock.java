package com.example.iao_app;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.Calendar;

public class CustomDigitalClock extends androidx.appcompat.widget.AppCompatTextView {
    private int customHour = 0;
    private int customMinute = 0;

    public CustomDigitalClock(Context context) {
        super(context);
        init();
    }

    public CustomDigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        updateTime();
    }

    public void setTime(int hour, int minute) {
        this.customHour = hour;
        this.customMinute = minute;
        updateTime();
    }

    private void updateTime() {
        String time = String.format("%02d:%02d", customHour, customMinute);
        setText(time);
    }
}
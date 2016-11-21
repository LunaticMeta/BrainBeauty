package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty.CalDecorators;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.jhj0104.brainbeauty.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Created by jhj0104 on 2016-11-21.
 * Use a custom selector
 */
public class MySelectorDecorator implements DayViewDecorator {

    private final Drawable drawable;

    public MySelectorDecorator(Activity context) {
        drawable = context.getDrawable(R.drawable.heart);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}

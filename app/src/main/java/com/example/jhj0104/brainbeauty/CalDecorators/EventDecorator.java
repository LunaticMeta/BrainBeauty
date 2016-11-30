package com.example.jhj0104.brainbeauty.CalDecorators;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.HashSet;
import java.util.List;

/**
 * Created by jhj0104 on 2016-11-21.
 * Decorate several days with a dot
 */

public class EventDecorator implements DayViewDecorator {
    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator(int color, List<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
    }


}

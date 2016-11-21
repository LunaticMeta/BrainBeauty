package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty.CalDecorators.EventDecorator;
import com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty.CalDecorators.HighlightWeekendsDecorator;
import com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty.CalDecorators.OneDayDecorator;
import com.example.jhj0104.brainbeauty.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by jhj0104 on 2016-11-18.
 * Shows off the most basic usage
 */

public class S_calendar extends AppCompatActivity  implements OnDateSelectedListener, OnMonthChangedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    TextView textView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private long lastTimeBackPressed;

    MaterialCalendarView widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_calendar);

        textView = (TextView) findViewById(R.id.txtDate);
        widget = (MaterialCalendarView)findViewById(R.id.calendarView);

        widget.setOnDateChangedListener(this);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        Calendar instance = Calendar.getInstance();
        widget.setSelectedDate(instance.getTime());

        widget.addDecorators(
                //new MySelectorDecorator(this),
                new HighlightWeekendsDecorator(),
                oneDayDecorator
        );

        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());

    }


    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate()).replace(" ","");
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        //If you change a decorate, you need to invalidate decorators
        String myDate = getSelectedDatesString();
        textView.setText(getSelectedDatesString());
        oneDayDecorator.setDate(date.getDate());
        widget.invalidateDecorators();

        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            S_data data = new S_data(myDate, ".");
            Intent myIntent = new Intent(S_calendar.this, S_list.class);
            myIntent.putExtra("Date",data);
            S_calendar.this.startActivity(myIntent);
            finish();
            /*
                Intent intent = new Intent(getApplicationContext(), S_listUpdate.class);
                intent.putExtra("data",data);
                startActivity(intent);
            * */
            return;
        }
        Toast.makeText(getBaseContext(), "한번 더 누르면 '할일/한일'로 넘어갑니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions
        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    /**
     * Simulate an API call to show how to add decorators
     */
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -2);
            ArrayList<CalendarDay> dates = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);
                calendar.add(Calendar.DATE, 5);
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            widget.addDecorator(new EventDecorator(Color.parseColor("#1DE9B6"), calendarDays));
            // = parseInt("#1DE9B6")
        }
    }
}

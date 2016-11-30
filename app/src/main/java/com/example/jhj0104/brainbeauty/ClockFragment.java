package com.example.jhj0104.brainbeauty;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.CalDecorators.EventDecorator;
import com.example.jhj0104.brainbeauty.CalDecorators.HighlightWeekendsDecorator;
import com.example.jhj0104.brainbeauty.CalDecorators.OneDayDecorator;
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
 * Created by jhj0104 on 2016-11-30.
 */

public class ClockFragment extends DialogFragment implements OnDateSelectedListener, OnMonthChangedListener {
    public ClockFragment(){}
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private final OneDayDecorator oneDayDecorator2 = new OneDayDecorator();
    private long lastTimeBackPressed;

    MaterialCalendarView widget;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_s_calendar2, container, false);
        mBuilder.setView(mLayoutInflater.inflate(R.layout.activity_s_calendar2, null));
        mBuilder.setTitle("DIALOG TITLE");
        mBuilder.setMessage("DIALOG MESSAGE");

        widget = (MaterialCalendarView) view.findViewById(R.id.calendarView2);

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


//        return mBuilder.create();
        return view;

    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate()).replace(" ","");
    }

    String twice = null;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        //If you change a decorate, you need to invalidate decorators
        String myDate = getSelectedDatesString();
//        textView.setText(getSelectedDatesString());
        oneDayDecorator.setDate(date.getDate());
        oneDayDecorator2.setDate(date.getDate());
        widget.invalidateDecorators();

        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            if(twice.equals(myDate)) {
                S_data data = new S_data(myDate, ".");
                Intent myIntent = new Intent(getActivity(), S_main.class);
                myIntent.putExtra("Date", data);
                ClockFragment.this.startActivity(myIntent);
                getActivity().finish();
                twice = null;
                return;
            }
        }
        twice = myDate;
        Toast.makeText(getActivity(), "한번 더 누르면 '할일/한일'로 넘어갑니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions
//        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    /**
     * Simulate an API call to show how to add decorators
     */
    public class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -4);
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

            if (getActivity().isFinishing()) {
                return;
            }
            widget.addDecorator(new EventDecorator(Color.parseColor("#1DE9B6"), calendarDays));
            // = parseInt("#1DE9B6")
        }
    }

}

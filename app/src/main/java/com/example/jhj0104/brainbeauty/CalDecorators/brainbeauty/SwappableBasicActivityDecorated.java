package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty;

/**
 * Created by jhj0104 on 2016-11-21.
 * Shows off the most basic usage
 */

public class SwappableBasicActivityDecorated{

}
//
//public class SwappableBasicActivityDecorated extends AppCompatActivity implements OnDateSelectedListener {
//
//    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
//
//    @Bind(R.id.calendarView)
//    MaterialCalendarView widget;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_basic_modes);
//        ButterKnife.bind(this);
//
//        widget.setOnDateChangedListener(this);
//        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
//
//
//        Calendar instance = Calendar.getInstance();
//        widget.setSelectedDate(instance.getTime());
//
//        Calendar instance1 = Calendar.getInstance();
//        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);
//
//        Calendar instance2 = Calendar.getInstance();
//        instance2.set(instance2.get(Calendar.YEAR), Calendar.DECEMBER, 31);
//
//        widget.state().edit()
//                .setMinimumDate(instance1.getTime())
//                .setMaximumDate(instance2.getTime())
//                .commit();
//
//        widget.addDecorators(
//                new MySelectorDecorator(this),
//                new HighlightWeekendsDecorator(),
//                oneDayDecorator
//        );
//    }
//
//    @Override
//    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
//        //If you change a decorate, you need to invalidate decorators
//        oneDayDecorator.setDate(date.getDate());
//        widget.invalidateDecorators();
//    }
//
//    @OnClick(R.id.button_weeks)
//    public void onSetWeekMode() {
//        widget.state().edit()
//                .setCalendarDisplayMode(CalendarMode.WEEKS)
//                .commit();
//    }
//
//    @OnClick(R.id.button_months)
//    public void onSetMonthMode() {
//        widget.state().edit()
//                .setCalendarDisplayMode(CalendarMode.MONTHS)
//                .commit();
//    }
//}
package com.surya432.skripsi.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surya432.skripsi.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class MyPlanFragment extends Fragment {
    private static final String TAG = MyPlanFragment.class.getSimpleName();


    public MyPlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    //Convert Date to Calendar
    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_plan, container, false);
        ButterKnife.bind(this, view);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int week_no = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week_no);
        Date monday = cal.getTime();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week_no + 1);
        Date sunday = cal.getTime();
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(dateToCalendar(monday), dateToCalendar(sunday))
                .datesNumberOnScreen(5)
                .defaultSelectedDate(dateToCalendar(cal2.getTime()))
                .build();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Log.d(TAG, "onCreateView: "+dateFormat.format(cal2.getTime()));
        String strDate = dateFormat.format(cal2.getTime());
        Snackbar.make(getActivity().findViewById(android.R.id.content)
                , strDate, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(date.getTime());
                Snackbar.make(getActivity().findViewById(android.R.id.content)
                        , strDate, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(date.getTime());
                Snackbar.make(getActivity().findViewById(android.R.id.content)
                        , strDate, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });

        return view;
    }


}

package com.community_life.db_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.community_life.R;

public class CalendarActivity extends AppCompatActivity
{
    public CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                String date = dayOfMonth + "/" + month + "/" + year;

                Intent intent = new Intent(CalendarActivity.this, InsertActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}

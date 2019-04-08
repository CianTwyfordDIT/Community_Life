package com.community_life.db_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

import com.community_life.R;

public class TimeActivity extends AppCompatActivity
{
    public TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_layout);

        timePicker = findViewById(R.id.timePickerView);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
        {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
            {
                String time =  hourOfDay + ":" + minute;

                Intent intent = new Intent(TimeActivity.this, InsertActivity.class);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });

    }
}

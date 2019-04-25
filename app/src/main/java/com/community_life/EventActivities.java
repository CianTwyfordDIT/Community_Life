package com.community_life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.community_life.SQLLite_DB.Community_Life_Local;

public class EventActivities extends AppCompatActivity
{
    ToggleButton toggleButton;
    public Community_Life_Local db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
        Intent intent = getIntent();
        intent.getStringExtra("c");
        db = new Community_Life_Local(this);
        db.open();
        db.close();
    }

    public void onCustomToggleClick(View view)
    {
        toggleButton = findViewById(R.id.toggle);

        if(((ToggleButton)view).isChecked())
        {
            //db.insertFaveEvent();
            Toast.makeText(this, "Added To Favourites", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Removed From Favourites", Toast.LENGTH_SHORT).show();
        }
    }

}

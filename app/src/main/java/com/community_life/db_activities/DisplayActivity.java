package com.community_life.db_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.community_life.MainActivity;
import com.community_life.R;

public class DisplayActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    }

    // Go back to the MainActivity.java
    public void goToMainPage(View v)
    {
        Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Go to InsertActivity and
    // insert a new event/activity into database
    public void goToInsertPage(View v)
    {
        Intent intent = new Intent(DisplayActivity.this, InsertActivity.class);
        startActivity(intent);
    }
}

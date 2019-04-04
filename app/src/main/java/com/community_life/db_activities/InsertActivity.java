package com.community_life.db_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.community_life.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.Date;

public class InsertActivity extends AppCompatActivity
{
    EditText nameText, descText; // timeText, dateText;
    //Spinner catSpinner;
    Button insertButton;

    /*// Get the spinner from activity_insert.xml
    Spinner dropdown = findViewById(R.id.spinner);
    // Create a list of items for the spinner
    String[] items = getResources().getStringArray(R.array.category);
    // Create an adapter to describe how the items are displayed
    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);*/

    DatabaseReference databaseReference;
    Events events;
    // Main
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        nameText = findViewById(R.id.nameText);
        descText = findViewById(R.id.descText);
      //  timeText = findViewById(R.id.timeText);
      //  dateText = findViewById(R.id.dateText);

        /*catSpinner = findViewById(R.id.spinner);*/

        insertButton = findViewById(R.id.insert_button);
        // Set the spinners adapter to the previously created one
       // dropdown.setAdapter(spinnerAdapter);

        events = new Events();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");

        insertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                events.setName(nameText.getText().toString().trim());
                events.setDescription(descText.getText().toString().trim());
              //  events.setDate((Date) dateText.getText());
          //      events.setTime((Time) timeText.getText());

                databaseReference.push().setValue(events);
                Toast.makeText(InsertActivity.this, "data inserted biatch", Toast.LENGTH_SHORT).show();
            }
        });
    } // End Main function

    // Go back to DisplayActivity.java
    public void goToBackToDisplayActivity(View v)
    {
        Intent intent = new Intent(InsertActivity.this, DisplayActivity.class);
        startActivity(intent);
    }
} // End InsertActivity

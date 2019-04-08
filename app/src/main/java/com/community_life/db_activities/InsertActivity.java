package com.community_life.db_activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.community_life.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertActivity extends AppCompatActivity
{
    String[] categories = {"Sports", "Music", "Arts & Crafts", "Computers", "Food",
            "Learning", "Outdoors"};

    EditText nameText, descText;

    TextView dateText;
    Button dateButton;

    Spinner spinner;
    ArrayAdapter<String> adapter;

    Button insertButton;

    DatabaseReference databaseReference;
    Events events;

    long max_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        nameText = findViewById(R.id.nameText);
        descText = findViewById(R.id.descText);

        dateText = findViewById(R.id.dateText);
        dateButton = findViewById(R.id.dateButton);
        Intent dateIntent = getIntent();
        String date = dateIntent.getStringExtra("date");
        dateText.setText(date);
        dateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(InsertActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        spinner = findViewById(R.id.category_spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attaching data adapter to spinner
        spinner.setAdapter(adapter);

        //  timeText = findViewById(R.id.timeText);
        insertButton = findViewById(R.id.insert_button);

        events = new Events();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    max_id = dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        insertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                events.setName(nameText.getText().toString().trim());
                events.setDescription(descText.getText().toString().trim());
                String catDesc = String.valueOf(spinner.getSelectedItem());
                events.setCategory_spinner(catDesc.trim());
                String finalDate = dateText.getText().toString();
                events.setDate(finalDate);
                //events.setTime((Time) timeText.getText());

                databaseReference.child(String.valueOf(max_id + 1)).setValue(events);
                Toast.makeText(InsertActivity.this, "data inserted biatch", Toast.LENGTH_SHORT).show();
            }
        });
    } // End of Main function

    // Go back to DisplayActivity.java
    public void goToBackToDisplayActivity(View v)
    {
        Intent intent = new Intent(InsertActivity.this, DisplayActivity.class);
        startActivity(intent);
    }
} // End of InsertActivity

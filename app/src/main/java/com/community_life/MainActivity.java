package com.community_life;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.community_life.db_activities.DisplayActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity
{
    // Name of categories will not change, so they can be kept in this array
    String[] categories = {"Sports", "Music", "Arts & Crafts", "Computers", "Food",
            "Learning", "Outdoors"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Passing the view you are in  activity_main
        setListAdapter(new MyAdapter(this, R.layout.category_layout, categories));
    }

    // Click on a category
    public void onListItemClick(ListView list, View v, int position, long id)
    {
        Intent intent = new Intent(MainActivity.this, EventActivities.class);
        intent.putExtra("c", position);
        startActivity(intent);
    }

    // Display activities the user is attending
    public void goToDisplayActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
        startActivity(intent);
    }

    public class MyAdapter extends ArrayAdapter
    {
        public MyAdapter(Context context, int textViewResourceId, String[] objects)
        {
            // values passed in constructor go into the superclass
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // inflates list item based on whats inside the biggest item in the list
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.category_layout, parent,false);
            // Row formatting
            // Retrieve your TextView from your category_layout.xml using:
            TextView label = row.findViewById(R.id.category_title);

            // Retrieve your ImageView from your category_layout.xml
            ImageView myImage = row.findViewById(R.id.category_image);

            // Now format the TextView and ImageView with the current value of the array
            label.setText(categories[position]);
            if(categories[position].equals("Sports"))
            {
                myImage.setImageResource(R.drawable.sports);
            }
            else if(categories[position].equals("Music"))
            {
                myImage.setImageResource(R.drawable.music);
            }
            else if(categories[position].equals("Arts & Crafts"))
            {
                myImage.setImageResource(R.drawable.crafts);
            }
            else if(categories[position].equals("Computers"))
            {
                myImage.setImageResource(R.drawable.computers);
            }
            else if(categories[position].equals("Food"))
            {
                myImage.setImageResource(R.drawable.food);
            }
            else if(categories[position].equals("Learning"))
            {
                myImage.setImageResource(R.drawable.learning);
            }
            else
            {
                myImage.setImageResource(R.drawable.outdoors);
            }
            return row;
        }
    }
}

/* This page includes the RecyclerView functionality
   needed for the main page */
package com.community_life.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.community_life.EventActivities;
import com.community_life.R;
import pl.droidsonroids.gif.GifImageView;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>
{
    // Name of categories will not change, so they can be kept in this array
    private String[] categories = {"Sports", "Music", "Arts & Crafts", "Computers", "Food",
                               "Learning", "Outdoors"};

    // May change gifs to video clips, video clips can be put in a similar array
    // They will not need to be changed
    private int[] gifs = {R.drawable.sports, R.drawable.music, R.drawable.crafts,
                            R.drawable.computers, R.drawable.food, R.drawable.learning,
                            R.drawable.outdoors };

    class ViewHolder extends RecyclerView.ViewHolder
    {
        // Layout and Views retrieved from category_layout.xml
        LinearLayout linearLayout;
        GifImageView categoryImageView;
        private TextView categoryName;

        private ViewHolder(View itemView)
        {
            super(itemView);
            // Access objects by their IDs in category_layout.xml
            categoryImageView = itemView.findViewById(R.id.category_image);
            categoryName = itemView.findViewById(R.id.c_item_title);
            linearLayout = itemView.findViewById(R.id.category_layout) ;

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    /* When an item in the RecyclerView is clicked,
                       user will be brought to the EventActivities page */
                    Intent intent = new Intent(v.getContext(), EventActivities.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        // Setting the name and gif(for now) of each category
        viewHolder.categoryName.setText(categories[i]);
        viewHolder.categoryImageView.setImageResource(gifs[i]);

        // Changing the colours for each CardView
        // Sports
        if(i == 0)
        {
            viewHolder.linearLayout.setBackgroundColor(Color.rgb(184, 255, 124));
        }

        // Music
        else if(i == 1)
        {
            viewHolder.linearLayout.setBackgroundColor(Color.rgb(255, 139, 139));
        }
        // Arts and Crafts
        else if(i == 2)
        {
            viewHolder.linearLayout.setBackgroundColor(Color.rgb(135, 239, 252));
        }
        // Computers
        else if(i == 3)
        {
            viewHolder.linearLayout.setBackgroundColor(Color.rgb(247, 135, 252));
        }
        // Food
        else if(i == 4)
        {
            viewHolder.linearLayout.setBackgroundColor(Color.rgb(233, 242, 31));
        }
        // Learning
        else if(i == 5)
        {
            viewHolder.linearLayout.setBackgroundColor(Color.rgb(251, 172, 99));
        }
        // Outdoors
        else if(i == 6)
        {
            viewHolder.linearLayout.setBackgroundColor(Color.rgb(88, 250, 192));
        }
    }

    @Override
    public int getItemCount()
    {
        return categories.length;
    }
}

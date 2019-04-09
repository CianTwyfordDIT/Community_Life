package com.community_life.db_activities;

import java.sql.Time;
import java.util.Date;

public class Events
{
    private String name;
    private String description;
    private String time;
    private String date;
    private String category_spinner;
   // private String eventImage;

   // public String getEventImage() {
   //     return eventImage;
   // }

    //public void setEventImage(String eventImage) {
  //      this.eventImage = eventImage;
  //  }

    public Events()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getCategory_spinner() {
        return category_spinner;
    }

    public void setCategory_spinner(String category_spinner) {
        this.category_spinner = category_spinner;
    }
}

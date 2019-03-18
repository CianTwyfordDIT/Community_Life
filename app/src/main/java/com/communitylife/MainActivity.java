package com.communitylife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity
{
    private TextView categoryTextView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryTextView = findViewById(R.id.category_text_view);
        requestQueue = Volley.newRequestQueue(this);
        jsonCategoryParse();
    }

    private void jsonCategoryParse()
    {
        // The url is a link to the Eventbrite API, using the token as a key to access
        // the JSON data stored there
        String url = "https://www.eventbriteapi.com/v3/categories/?token=THQEALMYMY7MCTFKH5PO";

        // Sending a http request to retrieve the JSON data
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("categories");

                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject category = jsonArray.getJSONObject(i);
                                // As we only need the short_name information,
                                // we will just retrieve this and no other data
                                // by parsing through the data
                                String categoryName = category.getString("short_name");
                                // Display the category name
                                categoryTextView.append(categoryName + "\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    } // END jsonCategoryParse()
} // END Main


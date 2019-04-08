package com.community_life.db_activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.community_life.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class InsertActivity extends AppCompatActivity
{
    String[] categories = {"Sports", "Music", "Arts & Crafts", "Computers", "Food",
            "Learning", "Outdoors"};

    // NAME, DESCRIPTION
    EditText nameText, descText;

    // TIME
    TextView timeText;
    Button timeButton;

    // DATE
    TextView dateText;
    Button dateButton;

    // CATEGORY
    Spinner spinner;
    ArrayAdapter<String> adapter;

    //IMAGE
    Resources resources;
    String encodedImage;
    Bitmap bitmap;
    public Button btnChoose, btnUpload;
    public ImageView imageView;
    FirebaseStorage storage;
    StorageReference storageReference;

    // ADD ACTIVITY
    Button insertButton;

    DatabaseReference databaseReference;
    Events events;

    long max_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        // NAME
        nameText = findViewById(R.id.nameText);

        // DESCRIPTION
        descText = findViewById(R.id.descText);

        // TIME
        timeText = findViewById(R.id.timeText);
        timeButton = findViewById(R.id.timeButton);
        Intent timeIntent = getIntent();
        String time = timeIntent.getStringExtra("time");
        timeText.setText(time);
        timeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(InsertActivity.this, TimeActivity.class);
                startActivity(intent);
            }
        });

        // DATE
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

        // CATEGORY
        spinner = findViewById(R.id.category_spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attaching data adapter to spinner
        spinner.setAdapter(adapter);

        // IMAGE
        // resources = this.getResources();
        // bitmap= BitmapFactory.decodeResource(resources , R.drawable.logo);
        btnUpload = findViewById(R.id.btnUpload);
        btnChoose = findViewById(R.id.btnChoose);
        imageView = findViewById(R.id.pictureImage);
        btnChoose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // chooseImage();
                encodedImage = encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
            }
        });
       /*
       btnUpload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // uploadImage();
            }
        });*/
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // ADD ACTIVITY
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
                // NAME
                events.setName(nameText.getText().toString().trim());

                // DESCRIPTION
                events.setDescription(descText.getText().toString().trim());

                // TIME
                String finalTime = timeText.getText().toString();
                events.setTime(finalTime);

                // DATE
                String finalDate = dateText.getText().toString();
                events.setDate(finalDate);

                //IMAGE
                events.setEventImage(encodedImage.trim());

                // CATEGORY
                String catDesc = String.valueOf(spinner.getSelectedItem());
                events.setCategory_spinner(catDesc.trim());

                databaseReference.child(String.valueOf(max_id + 1)).setValue(events);
                Toast.makeText(InsertActivity.this, "Activity Added!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InsertActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });
    } // End of Main function

   /* private void uploadImage()
    {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(InsertActivity.this, "Uploaded",
                                            Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(InsertActivity.this, "Failed "+e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    } */

    /* private void chooseImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
            {
                filePath = data.getData();
                try
                {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    // End of chooseImage() functionality */

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    // Go back to DisplayActivity.java
    public void goToBackToDisplayActivity(View v)
    {
        Intent intent = new Intent(InsertActivity.this, DisplayActivity.class);
        startActivity(intent);
    }
}

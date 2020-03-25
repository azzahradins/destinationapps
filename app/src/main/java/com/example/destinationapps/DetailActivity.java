package com.example.destinationapps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.destinationapps.models.Places;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {
    public static final String PLACES_KEY = "places";
    public static final String INDEX_KEY = "index";

    ImageView ivHeader;
    TextView tvName, tvDescription;

    private int index;
    private Places places;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivHeader = findViewById(R.id.image_places);
        tvName = findViewById(R.id.text_places);
        tvDescription = findViewById(R.id.text_description);

        // TODO 1: SET HELPER BAR USING PLACES NAMES
        // TODO 2: ADD SHARE IMPLICIT, EDIT, AND DELETE PLACES
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            places = extras.getParcelable(PLACES_KEY);
            index = extras.getInt(INDEX_KEY, 0);
            tvName.setText(places.getTitle());
            tvDescription.setText(places.getDescription());
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), places.getImage());
            } catch (IOException e) {
                Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                Log.e("Image Insert", e.getMessage());
            }
            ivHeader.setImageBitmap(bitmap);
        }
    }
}

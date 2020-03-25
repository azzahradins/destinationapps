package com.example.destinationapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.destinationapps.models.Places;

import java.io.IOException;

public class AddPlaces extends AppCompatActivity {
    private static final String PLACES_KEY = "places";
    private static final String INDEX_KEY = "index";
    EditText etTitle, etDescription;
    ImageView ivImages;
    Uri uriImages = null;
    private int index;
    private Places places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);
        etTitle = findViewById(R.id.input_places);
        etDescription = findViewById(R.id.input_decription);
        ivImages = findViewById(R.id.image_preview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            places = extras.getParcelable(PLACES_KEY);
            index = extras.getInt(INDEX_KEY, 0);
            etTitle.setText(places.getTitle());
            etDescription.setText(places.getDescription());
        }
    }

    public void SubmitPlaces(View view) {
        if(checkLogin() == 0) {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            places.setTitle(title);
            places.setDescription(description);
            places.setImage(this.uriImages);

            Intent intent = new Intent();
            intent.putExtra(PLACES_KEY, places);
            intent.putExtra(INDEX_KEY, index);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public int checkLogin(){
        int error = 0;
        if(etTitle.getText().toString().isEmpty()){
            error++;
            etTitle.setError("Add some title please");
        }
        if(etDescription.getText().toString().isEmpty()){
            error++;
            etDescription.setError("Add some description please");
        }
        return error;
    }

    public void addImages(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        if(requestCode == 1){
            try {
                if(data!=null){
                    uriImages = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriImages);
                    ivImages.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                Log.e("Image Insert", e.getMessage());
            }
        }
    }
}

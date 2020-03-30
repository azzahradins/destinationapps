package com.example.destinationapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.destinationapps.models.Author;
import com.example.destinationapps.models.Places;
import com.example.destinationapps.models.Session;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {
    public static final String PLACES_KEY = "places";
    public static final String INDEX_KEY = "index";
    private static final int UPDATE_REQUEST =  1;

    ImageView ivHeader;
    TextView tvName, tvDescription;

    private int index;
    private Places places;
    private Author author;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivHeader = findViewById(R.id.image_places);
        tvName = findViewById(R.id.text_places);
        tvDescription = findViewById(R.id.text_description);
        author = Application.getAuthor();
        session = Application.getSession();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            places = extras.getParcelable(PLACES_KEY);
            index = extras.getInt(INDEX_KEY, 0);
            tvName.setText(places.getTitle() + ", " + places.getCity());
            getSupportActionBar().setTitle(places.getTitle());
            tvDescription.setText(places.getDescription());
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), places.getImage());
            } catch (IOException e) {
                Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
            }
            ivHeader.setImageBitmap(bitmap);
        }
        Log.d("INFO INDEX", "berapa ini ? " + index);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, AddPlaces.class);
            intent.putExtra(PLACES_KEY, places);
            intent.putExtra(INDEX_KEY, index);
            startActivityForResult(intent, UPDATE_REQUEST);
            return true;
        }else if(id == R.id.action_delete){
            deleteDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteDialog(){
        AlertDialog optionDialog = new AlertDialog.Builder(this).create();
        optionDialog.setTitle("Delete Confirmation");
        optionDialog.setMessage("Are you sure want to delete this places?");
        optionDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I do.",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //kalau mau dihapus
                        if(session.isLoggedIn()){
                            author.deletePlaces(index);
                            onBackPressed();
                        }else{
                            Toast.makeText(DetailActivity.this, "Access Restricted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        optionDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", //kalau tidak jadi dihapus
                (DialogInterface.OnClickListener) null);
        optionDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Places places = data.getParcelableExtra(PLACES_KEY);
            if (requestCode == UPDATE_REQUEST) {
                author.updatePlaces(index, places);
                tvName.setText(places.getTitle() + ", " + places.getCity());
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

    public void SharePlaces(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, check this out: " + places.getTitle() + "?");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }
}

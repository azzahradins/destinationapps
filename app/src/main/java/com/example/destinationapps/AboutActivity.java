package com.example.destinationapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AboutActivity extends AppCompatActivity {
    ImageView logo, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        logo = findViewById(R.id.logo_github);
        profile = findViewById(R.id.image_profile);

        Picasso.get().load("https://scontent.fsub9-1.fna.fbcdn.net/v/t1.0-9/78367282_1287349531460289_514015836239822848_o.jpg?_nc_cat=108&_nc_sid=174925&_nc_eui2=AeHa8tWwfFNRJl292LJrDXA6U78aGIl6Sxii9IIcXjmKBhyDw4qrVRwlqvwEDzq2WnmYVd_EDTp0xdnNhaK5Rq5BpUXQ3-IRVTKUy79lkZh-vg&_nc_ohc=gaLix90vr6cAX88bMoV&_nc_ht=scontent.fsub9-1.fna&oh=c580fc8072d10ce3a9c7c21b3c0f3eb7&oe=5EA77F95")
                .into(profile);
        Picasso.get().load("https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png").into(logo);
    }
}

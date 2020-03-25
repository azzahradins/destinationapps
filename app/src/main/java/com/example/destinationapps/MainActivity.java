package com.example.destinationapps;

import android.content.Intent;
import android.os.Bundle;

import com.example.destinationapps.adapters.PlacesAdapter;
import com.example.destinationapps.models.Author;
import com.example.destinationapps.models.Places;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class MainActivity extends AppCompatActivity implements PlacesAdapter.OnItemPlacesListener {

    private static final int INSERT_REQUEST = 2;
    private static final String PLACES_KEY = "places";
    private static final String INDEX_KEY = "index";

    private RecyclerView rvPlaces;
    private PlacesAdapter adapter;
    private Author author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvPlaces = findViewById(R.id.rv_destination);
        author = Application.getAuthor();
        adapter = new PlacesAdapter(author.getPlaces(), this);
        rvPlaces.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPlaces.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPlaces.class);
                intent.putExtra(PLACES_KEY, new Places());
                startActivityForResult(intent, INSERT_REQUEST);
            }
        });
    }

    @Override
    public void onPlacesClicked(int index, Places item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(PLACES_KEY, item);
        intent.putExtra(INDEX_KEY, index);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Places places = data.getParcelableExtra(PLACES_KEY);
            if (requestCode == INSERT_REQUEST) {
                author.addPlaces(places);
            }
            adapter.notifyDataSetChanged();
        }
    }
}

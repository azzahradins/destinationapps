package com.example.destinationapps;

import android.content.Intent;
import android.os.Bundle;

import com.example.destinationapps.adapters.PlacesAdapter;
import com.example.destinationapps.models.Author;
import com.example.destinationapps.models.Places;
import com.example.destinationapps.models.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PlacesAdapter.OnItemPlacesListener {

    private static final int INSERT_REQUEST = 2;
    private static final String PLACES_KEY = "places";
    private static final String INDEX_KEY = "index";

    private RecyclerView rvPlaces;
    private PlacesAdapter adapter;
    private Author author;
    Session session;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvPlaces = findViewById(R.id.rv_destination);
        session = Application.getSession();
        author = Application.getAuthor();

        filterCity();

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

    private void filterCity() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                String city = spinner.getSelectedItem().toString();
                if(city.equals("All")){
                    adapter = new PlacesAdapter(author.getPlaces(), MainActivity.this);
                    rvPlaces.setAdapter(adapter);
                }else{
                    adapter = new PlacesAdapter(author.getPlacesCity(spinner.getSelectedItem().toString()),
                            MainActivity.this);
                    rvPlaces.setAdapter(adapter);
                }

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                rvPlaces.setLayoutManager(layoutManager);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onPlacesClicked(int index, Places item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(PLACES_KEY, item);
        intent.putExtra(INDEX_KEY, index);
        startActivity(intent);
        finish();
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

package com.example.destinationapps.adapters;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.destinationapps.R;
import com.example.destinationapps.models.Places;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private List<Places> placesList;
    private OnItemPlacesListener listener;

    public PlacesAdapter(List<Places> placesList, OnItemPlacesListener listener) {
        this.placesList = placesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places, parent, false);//belum bikin hancok lali
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Places places = placesList.get(position);
        holder.bind(position, places);
    }

    @Override
    public int getItemCount() {
        return (placesList!=null)? placesList.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageGreeting;
        TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageGreeting = itemView.findViewById(R.id.image);
            textTitle = itemView.findViewById(R.id.title);
        }

        public void bind(final int index, final Places places){
            textTitle.setText(places.getTitle());
            Picasso.get().load(places.getImage()).into(imageGreeting);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPlacesClicked(index, places);
                }
            });
        }
    }

    public interface OnItemPlacesListener{
        void onPlacesClicked(int index, Places item);
    }
}

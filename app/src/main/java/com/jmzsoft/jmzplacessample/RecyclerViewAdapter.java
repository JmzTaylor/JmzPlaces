package com.jmzsoft.jmzplacessample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmzsoft.jmzplaces.MyPlaces;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2;
        RatingBar rating;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    private ArrayList<MyPlaces> myPlaces;

    RecyclerViewAdapter(ArrayList<MyPlaces> arrayList) {
        myPlaces = arrayList;
    }

    @Override
    public int getItemCount() {
        return myPlaces.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutrow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyPlaces object = myPlaces.get(position);

        String firstText = object.getName();
        String secondText = object.getAddress();
        String rating = object.getRating();

        holder.text1.setText(firstText);
        holder.text2.setText(secondText);
        holder.rating.setRating(Float.parseFloat(rating));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}

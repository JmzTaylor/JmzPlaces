package com.jmzsoft.jmzplacessample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmzsoft.jmzplaces.JmzPlaces;
import com.jmzsoft.jmzplaces.MyPlaces;
import com.jmzsoft.jmzplaces.OnPlacesListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnPlacesListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new JmzPlaces(this,"restaurant", "");
        new JmzPlaces.AsyncTaskRunner(this).execute();
    }

    @Override
    public void onPlacesCompleted(ArrayList<MyPlaces> myPlaces) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(myPlaces);
        runOnUiThread(() -> recyclerView.setAdapter(adapter));
    }
}

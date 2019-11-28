package com.jmzsoft.jmzplaces;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;

import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JmzPlaces {

    private static String mApiKey;
    private static Context mContext;
    private static String mPlaceType;

    public JmzPlaces(Context _mContext, String _mPlaceType, String _mApiKey) {
        mApiKey = _mApiKey;
        mContext = _mContext;
        mPlaceType = _mPlaceType;
    }

    public static class AsyncTaskRunner extends AsyncTask<Void, Integer, Void> {

        private final OnPlacesListener mListener;
        ProgressDialog progressDialog;
        ArrayList<MyPlaces> myPlaces = new ArrayList<>();

        public AsyncTaskRunner(OnPlacesListener listener) {
            mListener = listener;
        }

        @Override
        protected Void doInBackground(Void... params) {
            MyLocation myLocation = getLocation(mContext);
            if (myLocation != null) {
                String mUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                        "location=" + myLocation.getLatitude() + "," + myLocation.getLongitude() +
                        "&rankby=distance" +
                        "&keyword=" + mPlaceType +
                        "&sensor=true" +
                        "&key=" + mApiKey;

                try {
                    StringBuilder result = new StringBuilder();
                    HttpURLConnection urlConnection = (HttpURLConnection) new URL(mUrl).openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    JSONObject jObject  = new JSONObject(result.toString());
                    JSONArray resarray = jObject.getJSONArray("results");
                    if (resarray.length() != 0) {
                        int len = resarray.length();
                        for (int j = 0; j < len; j++) {
                            try {
                                String name = resarray.getJSONObject(j).getString("name");
                                String address = resarray.getJSONObject(j).getString("vicinity");
                                String rating = resarray.getJSONObject(j).getString("rating");
                                myPlaces.add(new MyPlaces(name, address, rating));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (mListener != null) {
                        mListener.onPlacesCompleted(myPlaces);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            progressDialog.dismiss();
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(mContext,
                    "Loading",
                    "Finding Places");
        }
    }

    private static MyLocation getLocation(Context mContext) {
        LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
        if (lm != null) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

        }
        if (location != null) {
            return new MyLocation(location.getLatitude(), location.getLongitude());
        } else {
            return null;
        }

    }
}

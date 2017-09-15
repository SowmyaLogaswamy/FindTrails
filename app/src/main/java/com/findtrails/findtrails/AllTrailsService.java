package com.findtrails.findtrails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 9/15/17.
 */

public class AllTrailsService {

    public static void exploreTrails(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.All_TRAILS_BASE_URL).newBuilder();

        urlBuilder
                .addQueryParameter(Constants.ALL_TRAILS_LOCATION_QUERY_PARAMETER, location)
                .addQueryParameter(Constants.ALL_TRAILS_ACTIVITY_QUERY_PARAMETER, Constants.ALL_TRAILS_ACTIVITY)
                .addQueryParameter(Constants.ALL_TRAILS_KEY_QUERY_PARAMETER, Constants.ALL_TRAILS_KEY );

        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);


    }

   public static ArrayList<Trail> processResults(Response response) {
     ArrayList<Trail> trails = new ArrayList<> ();

       try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject allTrailsJSON = new JSONObject(jsonData);
                JSONArray placesJSON = allTrailsJSON.getJSONArray("places");

                for (int i = 0; i < placesJSON.length(); i++) {
                    JSONObject trailJSON = placesJSON.getJSONObject(i);

                    String cityName = trailJSON.getString("city");

                    String stateName = trailJSON.getString("state");

                    String nameOfPlace = trailJSON.getString("name");

                    String directionsName = trailJSON.getString("directions");

                    Trail trail = new Trail(cityName, stateName, nameOfPlace, directionsName);
                    trails.add(trail);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trails;
    }
}

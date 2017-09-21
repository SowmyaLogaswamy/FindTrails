package com.findtrails.findtrails.services;

import com.findtrails.findtrails.Constants;
import com.findtrails.findtrails.models.Trail;

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

                    String city = trailJSON.getString("city");

                    String state = trailJSON.getString("state");

                    String trailName = trailJSON.getString("name");

                    String directions = trailJSON.getString("directions");

                    String description = trailJSON.optString("description");

                    JSONArray activitiesJSON = trailJSON.getJSONArray("activities");
                    JSONObject trailsJSON = activitiesJSON.getJSONObject(0);
//                      for(int j = 0 ; j< activitiesJSON.length(); j++)



                          String url = trailsJSON.getString("url");


                    Trail trail = new Trail(city, state, trailName, directions, description, url);
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

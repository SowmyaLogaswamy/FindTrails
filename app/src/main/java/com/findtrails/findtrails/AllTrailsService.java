package com.findtrails.findtrails;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}

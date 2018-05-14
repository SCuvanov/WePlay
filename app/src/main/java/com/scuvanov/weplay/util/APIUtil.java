package com.scuvanov.weplay.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 3/7/2018.
 */

public class APIUtil {
    public static final String API_KEY = "df0301913ee9372f9f61519e91fabe4c";
    private static final String GAME_FIELDS = "id, name, slug, url, created_at, updated_at, summary, rating, rating_count";
    private static final String GENRE_FIELDS = "id, name";
    private static final String PLATFORM_FIELDS = "id, name";

    public static void getGenres(final Context context){
        APIWrapper wrapper = new APIWrapper(context, API_KEY);

        Parameters params = new Parameters();
        params.addFields(GENRE_FIELDS);
        params.addLimit("50");

        wrapper.genres(params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Gson gson = new Gson();
                Genre[] genres = gson.fromJson(jsonArray.toString(), Genre[].class);
                //Insert DB
                Genre.insertAll(context, genres);
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.e("GENRES ERROR:", volleyError.toString());
            }
        });
    }

    public static void getPlatforms(final Context context){
        List<Platform> platforms = new ArrayList<Platform>();
        APIWrapper wrapper = new APIWrapper(context, API_KEY);

        Parameters params = new Parameters();
        params.addFields(PLATFORM_FIELDS);
        params.addLimit("50");

        wrapper.platforms(params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Gson gson = new Gson();
                Platform[] platforms = gson.fromJson(jsonArray.toString(), Platform[].class);
                Platform.insertAll(context, platforms);
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

    public static List<Game> getGames(Context context, String title, String genre, int rangeLower, int rangeUpper, String platform, String esrb){
        APIWrapper wrapper = new APIWrapper(context, API_KEY);
        Parameters params = new Parameters();
        params.addFields(GAME_FIELDS);

        if(!StringUtils.isBlank(title)){
            //params.addFilter();
        }
        if(!StringUtils.isBlank(platform)){
            int platform_id = 0;
            params.addFilter("[platforms][eq]=" + platform_id);
        }
        if(!StringUtils.isBlank(esrb)){
            int esrb_id = 0;
            params.addFilter("[esrb][eq]=" + esrb_id);
        }

        wrapper.games(params, new onSuccessCallback(){
            @Override
            public void onSuccess(JSONArray result) {
                Log.e("RESPONSE: ", result.toString());
            }

            @Override
            public void onError(VolleyError error) {
                // Do something on error
                Log.e("RESPONSE: ", error.toString());
            }
        });

        return null;
    }

}
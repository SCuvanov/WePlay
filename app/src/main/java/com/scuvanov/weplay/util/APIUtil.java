package com.scuvanov.weplay.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;
import com.scuvanov.weplay.Game;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by Sean on 3/7/2018.
 */

public class APIUtil {
    public static final String API_KEY = "df0301913ee9372f9f61519e91fabe4c";
    public static final String API_BASE_URL = "https://api-endpoint.igdb.com/";
    public static final String GAME_URL = API_BASE_URL + "games/?";
    private static final String GAME_FIELDS = "id, name, slug, url, created_at, updated_at, summary, rating, rating_count";

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
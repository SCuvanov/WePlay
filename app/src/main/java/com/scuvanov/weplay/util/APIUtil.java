package com.scuvanov.weplay.util;

import android.content.Context;

import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;
import com.scuvanov.weplay.Game;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Sean on 3/7/2018.
 */

public class APIUtil {
    public static final String API_KEY = "df0301913ee9372f9f61519e91fabe4c";
    public static final String API_BASE_URL = "https://api-endpoint.igdb.com/";
    public static final String GAME_URL = API_BASE_URL + "games/?";

    public static List<Game> getGames(Context context, String title, String genre, int rangeLower, int rangeUpper, String platform, String esrb){
        APIWrapper wrapper = new APIWrapper(context, API_KEY);
        Parameters params = new Parameters();
        params.addFields("*");

        if(!StringUtils.isBlank(title)){
            //params.addFilter();
        }
        if(!StringUtils.isBlank(platform)){}
        if(!StringUtils.isBlank(esrb)){}

        //TODO: Do something with Ranges

        //TODO: Call the API





        return null;
    }

}
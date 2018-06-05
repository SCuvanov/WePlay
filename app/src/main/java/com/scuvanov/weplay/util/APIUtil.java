package com.scuvanov.weplay.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;
import com.scuvanov.weplay.application.WePlayApplication;
import com.scuvanov.weplay.entity.Company;
import com.scuvanov.weplay.entity.Esrb;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;
import com.scuvanov.weplay.repository.CompanyRepository;
import com.scuvanov.weplay.repository.EsrbRepository;
import com.scuvanov.weplay.repository.GameRepository;
import com.scuvanov.weplay.repository.GenreRepository;
import com.scuvanov.weplay.repository.PlatformRepository;
import com.scuvanov.weplay.repository.RepositoryFactory;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import java.util.Arrays;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Sean on 3/7/2018.
 */

public class APIUtil {
    public static final String API_KEY = "df0301913ee9372f9f61519e91fabe4c";
    private static final String GENRE_FIELDS = "id, name";
    private static final String PLATFORM_FIELDS = "id, name";
    private static final String GAME_FIELDS = "id, name, url, rating, rating_count, summary, genres, platforms, cover, esrb, developers, screenshots";

    public static void getGenres(Context context) {
        APIWrapper wrapper = new APIWrapper(context, API_KEY);

        Parameters params = new Parameters();
        params.addFields(GENRE_FIELDS);
        params.addLimit("50");

        wrapper.genres(params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Gson gson = new Gson();
                Genre[] genres = gson.fromJson(jsonArray.toString(), Genre[].class);
                GenreRepository genreRepository = RepositoryFactory.getGenreRepository();
                genreRepository.insertAll(genres);
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage());
            }
        });
    }

    public static void getPlatforms(Context context, String offset) {
        APIWrapper wrapper = new APIWrapper(context, API_KEY);

        Parameters params = new Parameters();
        params.addFields(PLATFORM_FIELDS);
        params.addLimit("50");
        if (!StringUtils.isBlank(offset)) params.addOffset(offset);

        wrapper.platforms(params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Gson gson = new Gson();
                Platform[] platforms = gson.fromJson(jsonArray.toString(), Platform[].class);
                PlatformRepository platformRepository = RepositoryFactory.getPlatformRepository();
                platformRepository.insertAll(platforms);
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage());
            }
        });
    }

    public static void getEsrbs(Context context) {
        int[] ids = new int[]{1, 2, 3, 4, 5, 6, 7};
        String[] values = new String[]{"RP", "EC", "E", "E10+", "T", "M", "AO"};
        Esrb[] esrbs = new Esrb[7];

        for (int i = 0; i < ids.length; i++) {
            Esrb esrb = new Esrb(ids[i], values[i]);
            esrbs[i] = esrb;
        }

        EsrbRepository esrbRepository = RepositoryFactory.getEsrbRepository();
        esrbRepository.insertAll(esrbs);
    }

    public static void getGames(String title) {
        APIWrapper wrapper = new APIWrapper(WePlayApplication.getContext(), API_KEY);

        Parameters params = new Parameters();
        params.addFields(GAME_FIELDS);

        if (!StringUtils.isBlank(title)) {
            params.addSearch(title);
        }

        wrapper.games(params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Gson gson = new Gson();
                Game[] games = gson.fromJson(jsonArray.toString(), Game[].class);
                GameRepository gameRepository = RepositoryFactory.getGameRepository();
                gameRepository.deleteAndInsertAll(games);
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage());
            }
        });
    }


    public static void getCompanies(int[] ids) {
        if (ids == null || ids.length <= 0) return;
        APIWrapper wrapper = new APIWrapper(WePlayApplication.getContext(), API_KEY);

        Parameters params = new Parameters();
        String result = Arrays.toString(ids).replaceAll("\\[|\\]", "");

        params.addFields(result);
        params.addFields("name");

        wrapper.search(APIWrapper.Endpoint.COMPANIES, params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Gson gson = new Gson();
                Company[] companies = gson.fromJson(jsonArray.toString(), Company[].class);
                CompanyRepository companyRepository = RepositoryFactory.getCompanyRepository();
                companyRepository.deleteAndInsertAll(companies);
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage());
            }
        });
    }
}
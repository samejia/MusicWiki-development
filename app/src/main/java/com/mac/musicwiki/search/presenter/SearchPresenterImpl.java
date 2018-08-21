package com.mac.musicwiki.search.presenter;

import com.mac.musicwiki.search.model.SearchVO;
import com.mac.musicwiki.rest.DeezerService;
import com.mac.musicwiki.search.view.SearchView;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SamMejia on 14/12/16.
 */

public class SearchPresenterImpl implements SearchPresenter {
    public static final String BASE_URL = "https://deezerdevs-deezer.p.mashape.com/";
    SearchVO searchVo;
    SearchView view;

    @Inject
    public SearchPresenterImpl(){

    }

    @Override
    public void attachView(SearchView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public SearchVO getAllSearchArtist(String artist) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        DeezerService deezerService = retrofit.create(DeezerService.class);
        Call<SearchVO> callPersons = deezerService.getInformationBySearch(artist);
        callPersons.enqueue(new Callback<SearchVO>() {
            @Override
            public void onResponse(Call<SearchVO> call, Response<SearchVO> response) {
                searchVo = response.body();
                view.createRecycler(searchVo.getData());
            }

            @Override
            public void onFailure(Call<SearchVO> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return searchVo;
    }
}

package com.mac.musicwiki.album.presenter;

import android.content.Context;

import com.mac.musicwiki.album.model.FavoriteVO;
import com.mac.musicwiki.album.view.AlbumView;
import com.mac.musicwiki.database.DatabaseHandler;
import com.mac.musicwiki.rest.DeezerService;
import com.mac.musicwiki.search.model.SearchVO;
import com.mac.musicwiki.search.presenter.SearchPresenter;
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

public class AlbumPresenterImpl implements AlbumPresenter {
    AlbumView view;
    DatabaseHandler db;

    @Inject
    public AlbumPresenterImpl(){

    }

    @Override
    public void attachView(AlbumView view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        view = null;
    }


    @Override
    public void attachDB(DatabaseHandler db) {
        this.db = db;
    }

    @Override
    public boolean addToFavorite(FavoriteVO artist) {
        db.addFavoriteArtist(artist);
        return true;
    }
}

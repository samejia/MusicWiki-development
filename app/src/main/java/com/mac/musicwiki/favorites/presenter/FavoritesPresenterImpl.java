package com.mac.musicwiki.favorites.presenter;

import com.mac.musicwiki.album.model.FavoriteVO;
import com.mac.musicwiki.album.presenter.AlbumPresenter;
import com.mac.musicwiki.album.view.AlbumView;
import com.mac.musicwiki.database.DatabaseHandler;
import com.mac.musicwiki.favorites.view.FavoritesView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by SamMejia on 14/12/16.
 */

public class FavoritesPresenterImpl implements FavoritesPresenter {
    FavoritesView view;
    DatabaseHandler db;

    @Inject
    public FavoritesPresenterImpl(){

    }

    @Override
    public void attachView(FavoritesView view) {
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
    public List<FavoriteVO> getAllFavorites() {
        return db.getAllFavoriteArtist();
    }

    @Override
    public boolean deleteFavorite(FavoriteVO fav) {
        return db.deleteFavArtist(fav);
    }

}

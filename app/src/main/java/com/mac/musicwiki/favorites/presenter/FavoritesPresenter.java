package com.mac.musicwiki.favorites.presenter;

import com.mac.musicwiki.BasePresenter;
import com.mac.musicwiki.album.model.FavoriteVO;
import com.mac.musicwiki.database.DatabaseHandler;
import com.mac.musicwiki.favorites.view.FavoritesView;

import java.util.List;

/**
 * Created by SamMejia on 1/24/17.
 */

public interface FavoritesPresenter extends BasePresenter<FavoritesView> {
    void attachDB(DatabaseHandler db);
    List<FavoriteVO> getAllFavorites();
    boolean deleteFavorite(FavoriteVO fav);
}

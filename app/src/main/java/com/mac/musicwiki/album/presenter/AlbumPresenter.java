package com.mac.musicwiki.album.presenter;

import android.content.Context;

import com.mac.musicwiki.BasePresenter;
import com.mac.musicwiki.album.model.FavoriteVO;
import com.mac.musicwiki.album.view.AlbumView;
import com.mac.musicwiki.database.DatabaseHandler;
import com.mac.musicwiki.search.model.SearchVO;
import com.mac.musicwiki.search.view.SearchView;


/**
 * Created by SamMejia on 14/12/16.
 */

public interface AlbumPresenter extends BasePresenter<AlbumView> {
    void attachDB(DatabaseHandler db);
    boolean addToFavorite(FavoriteVO artist);

}

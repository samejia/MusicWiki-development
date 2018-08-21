package com.mac.musicwiki.favorites.view;


import com.mac.musicwiki.BaseView;
import com.mac.musicwiki.album.model.FavoriteVO;

import java.util.List;

/**
 * Created by SamMejia on 14/12/16.
 */

public interface DeleteFavView extends BaseView {
    boolean deleteFavorite(FavoriteVO fav);


}

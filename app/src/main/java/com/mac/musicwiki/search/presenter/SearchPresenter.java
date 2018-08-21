package com.mac.musicwiki.search.presenter;

import com.mac.musicwiki.BasePresenter;
import com.mac.musicwiki.search.model.SearchVO;
import com.mac.musicwiki.search.view.SearchView;

import java.util.List;


/**
 * Created by SamMejia on 14/12/16.
 */

public interface SearchPresenter extends BasePresenter<SearchView> {
    SearchVO getAllSearchArtist(String artist);

}

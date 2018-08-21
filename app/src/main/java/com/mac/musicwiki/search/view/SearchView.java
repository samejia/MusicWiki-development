package com.mac.musicwiki.search.view;


import com.mac.musicwiki.BaseView;
import com.mac.musicwiki.search.model.Datum;
import com.mac.musicwiki.search.model.SearchVO;

import java.util.List;

/**
 * Created by SamMejia on 14/12/16.
 */

public interface SearchView extends BaseView {
    SearchVO showAllSearchArtist(String artist);
    void createRecycler(List<Datum> data);
    void showProgress();
    void hideProgress();

}

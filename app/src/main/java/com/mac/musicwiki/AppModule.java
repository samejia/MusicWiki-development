package com.mac.musicwiki;

import com.mac.musicwiki.album.presenter.AlbumPresenter;
import com.mac.musicwiki.album.presenter.AlbumPresenterImpl;
import com.mac.musicwiki.favorites.presenter.FavoritesPresenter;
import com.mac.musicwiki.favorites.presenter.FavoritesPresenterImpl;
import com.mac.musicwiki.search.presenter.SearchPresenter;
import com.mac.musicwiki.search.presenter.SearchPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SamMejia on 1/5/17.
 */
@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }



    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter() {
        return new SearchPresenterImpl();
    }

    @Provides
    @Singleton
    AlbumPresenter provideAlbumPresenter() {
        return new AlbumPresenterImpl();
    }

    @Provides
    @Singleton
    FavoritesPresenter provideFavoritesPresenter() {
        return new FavoritesPresenterImpl();
    }

}

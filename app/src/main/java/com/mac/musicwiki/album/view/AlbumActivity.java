package com.mac.musicwiki.album.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mac.musicwiki.App;
import com.mac.musicwiki.R;
import com.mac.musicwiki.album.model.FavoriteVO;
import com.mac.musicwiki.album.presenter.AlbumPresenter;
import com.mac.musicwiki.database.DatabaseHandler;
import com.mac.musicwiki.favorites.view.FavoritesActivity;
import com.mac.musicwiki.search.model.Datum;
import com.mac.musicwiki.search.presenter.SearchPresenter;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.mac.musicwiki.search.view.SearchActivity;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumActivity extends AppCompatActivity implements AlbumView {
    @BindView(R.id.imageAlbum) ImageView imgCover;
    @BindView(R.id.artis) TextView artist;
    @BindView(R.id.album) TextView album;
    @BindView(R.id.rating) RatingBar ratingBar;
    @BindView(R.id.btnCheckin) Button btnCheckin;
    @BindView(R.id.pb) ProgressBar progressBar;
    @Inject
    AlbumPresenter albumPresenter;
    DatabaseHandler db = new DatabaseHandler(this);
    private Datum item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        App.getAppComponent(this).inject(this);
        albumPresenter.attachView(this);
        ButterKnife.bind(this);
        Intent i = getIntent();
        item =  (Datum) i.getSerializableExtra("album");
        artist.setText(item.getArtist().getName());
        album.setText(item.getAlbum().getTitle());
        Glide.with(AlbumActivity.this).load(item.getArtist().getPictureXl()).into(imgCover);
    }

    public FavoriteVO setFavoriteVO(Datum item){
        FavoriteVO vo = new FavoriteVO();
        vo.setName(item.getArtist().getName());
        vo.setAlbum(item.getAlbum().getTitle());
        vo.setRating(ratingBar.getRating());
        vo.setPicture(item.getArtist().getPictureXl());
        vo.setLink(item.getLink());
        return vo;
    }

    public void checkIn(View view) {
        showProgress();
        albumPresenter.attachDB(db);
        addToFavorite(setFavoriteVO(item));
        hideProgress();
    }

    public void openApp(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(item.getLink()));
        startActivity(intent);
    }

    @Override
    public boolean addToFavorite(FavoriteVO artist) {
        albumPresenter.addToFavorite(artist);
        return true;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "The album was save correctly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch(item.getItemId()) {
        case R.id.search:
            i = new Intent(AlbumActivity.this, SearchActivity.class);
            startActivity(i);
            finish();
            return(true);
        case R.id.fav:
            i = new Intent(AlbumActivity.this, FavoritesActivity.class);
            startActivity(i);
            finish();
            return(true);
        case R.id.share:
            share();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    public boolean share(){
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("My favorite album is"+
                        " "+ item.getAlbum().getTitle()+
                        " "+ "played by"+
                        " "+ item.getArtist().getName()+
                        " "+ "my ranking for it is"+
                        " "+ ratingBar.getRating()+
                        " "+ "stars"+
                        " "+ "#MusicWiki"+
                        " "+ "#TheBestApp");
        builder.show();
        return true;
    }
}

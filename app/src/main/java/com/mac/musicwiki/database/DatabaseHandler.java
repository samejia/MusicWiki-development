package com.mac.musicwiki.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mac.musicwiki.album.model.FavoriteVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamMejia on 21/11/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ArtistDB";
    private static final String TABLE_FAVORITE = "favorites";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ALBUM = "album";
    private static final String KEY_PICTURE = "picture";
    private static final String KEY_RATING = "rating";
    private static final String KEY_LINK = "link";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FAVORITE_TABLE = "CREATE TABLE " + TABLE_FAVORITE + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        + KEY_ALBUM + " TEXT," + KEY_PICTURE + " TEXT,"
        + KEY_LINK + " TEXT," + KEY_RATING + " REAL" + ")";
        sqLiteDatabase.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        onCreate(sqLiteDatabase);
    }

    public void addFavoriteArtist(FavoriteVO favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, favorite.getName());
        values.put(KEY_ALBUM, favorite.getAlbum());
        values.put(KEY_PICTURE, favorite.getPicture());
        values.put(KEY_LINK, favorite.getLink());
        values.put(KEY_RATING, favorite.getRating());
        db.insert(TABLE_FAVORITE, null, values);
        db.close();

    }

    public List<FavoriteVO> getAllFavoriteArtist() {
        List<FavoriteVO> favList = new ArrayList<FavoriteVO>();
        String selectQuery = "SELECT * FROM " + TABLE_FAVORITE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        while (cursor.moveToNext()) {
            FavoriteVO fav = new FavoriteVO();
                fav.setId(Integer.parseInt(cursor.getString(0)));
                fav.setName(cursor.getString(1));
                fav.setAlbum(cursor.getString(2));
                fav.setPicture(cursor.getString(3));
                fav.setLink(cursor.getString(4));
                fav.setRating(Float.parseFloat(cursor.getString(5)));
                favList.add(fav);
        }
       return favList;
    }

    public boolean deleteFavArtist(FavoriteVO favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITE, KEY_ID + " = ?", new String[] { String.valueOf(favorite.getId())});
        db.close();
        return true;
    }
}

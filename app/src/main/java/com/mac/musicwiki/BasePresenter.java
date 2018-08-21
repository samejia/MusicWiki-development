package com.mac.musicwiki;

/**
 * Created by SamMejia on 14/12/16.
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}

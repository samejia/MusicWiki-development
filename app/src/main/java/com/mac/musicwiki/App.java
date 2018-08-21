package com.mac.musicwiki;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

/**
 * Created by SamMejia on 1/5/17.
 */

public class App extends Application {
    private AppComponent component;

    @VisibleForTesting
    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        App app = (App) context.getApplicationContext();
        if (app.component == null) {
            app.component = app.createComponent();
        }
        return app.component;
    }
}
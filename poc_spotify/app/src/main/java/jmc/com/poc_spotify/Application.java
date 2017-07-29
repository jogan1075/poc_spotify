package jmc.com.poc_spotify;

import android.app.Activity;

/**
 * Created by jogan1075 on 28-07-17.
 */

public class Application extends android.app.Application {
    private static Application instance;
    private static Activity activity;
    private static String token;



    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Application.token = token;
    }

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        Application.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        this.vollyRequest = VollyRequest.getInstance(ApplicationContextGral.getInstance().getApplicationContext());
        instance = this;
    }

    public static android.content.Context getContext() {
        return instance;
    }

    public static Application getInstance() {
        return instance;
    }
}
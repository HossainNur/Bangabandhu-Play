package com.durbar.bangabandhuplay.utils;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

public class NavigationHelper {
    private static volatile NavigationHelper INSTANCE = null;

    private String title;
    private AppBarLayout appBarLayout;

    private NavigationHelper() {
        this.title = null;
        this.appBarLayout = null;
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    public void setAppBarLayout(AppBarLayout appBarLayout) {
        this.appBarLayout = appBarLayout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static NavigationHelper getINSTANCE() {
        if (INSTANCE == null){
            synchronized (NavigationHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new NavigationHelper();
                }
            }
        }
        return INSTANCE;
    }
}

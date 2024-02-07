package com.durbar.bangabandhuplay.utils;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

public class NavigationHelper {
    private static volatile NavigationHelper INSTANCE = null;

    private String title,shortTitle,description,image,currentFragment;
    private AppBarLayout appBarLayout;

    private NavigationHelper() {
        this.title = null;
        this.shortTitle = null;
        this.description = null;
        this.image = null;
        this.appBarLayout = null;
        this.currentFragment = Constants.HOME_FRAGMENT;
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    public void setAppBarLayout(AppBarLayout appBarLayout) {
        this.appBarLayout = appBarLayout;
    }

    public String getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(String currentFragment) {
        this.currentFragment = currentFragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

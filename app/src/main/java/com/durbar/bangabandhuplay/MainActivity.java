package com.durbar.bangabandhuplay;

import static com.durbar.bangabandhuplay.R.id.familyMemberFragment;
import static com.durbar.bangabandhuplay.R.id.navigation_home;
import static com.durbar.bangabandhuplay.R.id.navigation_movies;
import static com.durbar.bangabandhuplay.R.id.navigation_tvShows;
import static com.durbar.bangabandhuplay.R.id.photoGalleryFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.durbar.bangabandhuplay.databinding.ActivityMainBinding;
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private static ActivityMainBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkAppBarVisibility();   //to check AppBar visibility is Visible or Gone

        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();*/
        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close);
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(binding.homeToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = navHostFragment.getNavController();


     /*   //These two lines can perform drawer menu & bottom navigation automatically. There will be no need to manually setting 'setNavigationItemSelectedListener' done below
        // here we are doing manually below to achieve some of our design and functionality goals which is not possible in automatic process.
        NavigationUI.setupWithNavController(binding.navDrawer, navController);
        NavigationUI.setupWithNavController(binding.navView,navController);
        */

        binding.navDrawer.setNavigationItemSelectedListener(item -> {

            /*Constants.MainToolBarVisibility = false;  //  binding.appTopBarLayout.setVisibility(View.GONE)
            checkAppBarVisibility();*/

           // checkAppBarVisibility();   //  binding.appTopBarLayout.setVisibility(View.GONE)

            binding.navView.getMenu().setGroupCheckable(0,false, true);  //for unselecting/unchecking bottomNavigation item when drawer menu item is clicked

            binding.drawerLayout.closeDrawer(GravityCompat.START);   //close drawer

            if (item.getItemId() == familyMemberFragment) {
                navController.navigate(R.id.familyMemberFragment);

                // this will clear the back stack and send to the declared fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStackImmediate("FamilyMemberFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            if (item.getItemId() == photoGalleryFragment) {
                navController.navigate(R.id.photoGalleryFragment);

                // this will clear the back stack and send to the declared fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStackImmediate("photoGalleryFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            return true;
        });

        binding.navView.setOnNavigationItemSelectedListener( item ->{

            binding.navView.getMenu().setGroupCheckable(0,true, true);  // for enabling selecting/checking bottomNavigation item again when bottom navigation item is clicked again

            Constants.MainToolBarVisibility = true;  // binding.appTopBarLayout.setVisibility(View.VISIBLE)
           // checkAppBarVisibility();

            if (item.getItemId() == navigation_home){
                navController.navigate(R.id.navigation_home);
            }
            if (item.getItemId() == navigation_movies){

                navController.navigate(R.id.navigation_movies);
            }
            if (item.getItemId() == navigation_tvShows){
                navController.navigate(R.id.navigation_tvShows);
            }

            return  true;
        });


        binding.ivSearch.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SearchResultActivity.class));
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click twice to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

     public static void checkAppBarVisibility(){   //As activity is same when we navigate between fragments, ocCreate here will be called once. So, took method outside onCreate, called in onCreate here & in fragments too
        if (Constants.MainToolBarVisibility) {
            binding.appTopBarLayout.setVisibility(View.VISIBLE);
        } else {
            binding.appTopBarLayout.setVisibility(View.GONE);
        }
        // binding.navView.getMenu().setGroupCheckable(0,true, true);   // for enabling selecting/checking bottomNavigation item again when coming back to home fragment

     }
}
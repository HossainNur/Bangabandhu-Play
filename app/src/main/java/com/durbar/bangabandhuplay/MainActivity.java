package com.durbar.bangabandhuplay;

import static com.durbar.bangabandhuplay.R.id.familyMemberFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.durbar.bangabandhuplay.databinding.ActivityMainBinding;
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean doubleBackToExitPressedOnce = false;

    private AppBarConfiguration appBarConfiguration;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        //NavigationUI.setupWithNavController(binding.navView, navController);

        /*appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_movies, R.id.navigation_tvShows, R.id.familyMemberFragment, R.id.photoGalleryFragment)
                //.setDrawerLayout(binding.drawerLayout)
                .setOpenableLayout(binding.drawerLayout)
                .build();*/

       // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navDrawer, navController);
        NavigationUI.setupWithNavController(binding.navView,navController);

        //for unselecting/unchecking bottomNavigation item
        binding.navView.getMenu().setGroupCheckable(0,false, true);

        binding.ivSearch.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SearchResultActivity.class));
        });

        //NAVIGATION DRAWER ITEM SELECTED LISTENER
       /* binding.navDrawer.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == familyMemberFragment) {
                Toast.makeText(this, "family member", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.familyMemberFragment);
            } else {
                Toast.makeText(this, "photo gallery", Toast.LENGTH_SHORT).show();
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });*/

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
}
package com.durbar.bangabandhuplay.ui.player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.durbar.bangabandhuplay.MainActivity;
import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.ActivityPlayerBinding;
import com.durbar.bangabandhuplay.ui.more.MoreActivity;
import com.durbar.bangabandhuplay.ui.tunes.TunesFragment;
import com.durbar.bangabandhuplay.utils.NavigationHelper;
import com.durbar.bangabandhuplay.utils.NetworkUtil;
import com.durbar.bangabandhuplay.utils.TrackSelectionDialog;
import com.durbar.bangabandhuplay.data.model.ott_content.ContentSource;
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity;
import com.durbar.bangabandhuplay.utils.Constants;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerActivity extends AppCompatActivity implements GetRelatedContentsAdapter.CallBack{

    private ActivityPlayerBinding binding;
    private String title;
    private PlayerViewModel viewModel;
    private String uuid, videoPath;
    private PlayerView playerView;
    private ExoPlayer exoPlayer;
    private ImageView btnFullScreen, btnReplay, btnForward, btnBack, btnLockscreen, btnSetting;
    private ConstraintLayout contentTopSection;
    private TextView moviesTitle;
    private boolean isShowingTrackSelectionDialog;
    private NetworkUtil networkUtil;
    boolean isFullScreen = false, isLock = false, ottContent = false, relatedContent = false,isMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        uuid = getIntent().getStringExtra(Constants.CONTENT_UUID);
        networkUtil = NetworkUtil.getInstance(this);
        setContentView(binding.getRoot());

        title = getIntent().getStringExtra(Constants.CONTENT_SECTION_TITLE);
        isMore = getIntent().getBooleanExtra(Constants.CONTENT_IS_MORE,false);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle(title);

        if (!networkUtil.isNetworkAvailable()) {
            NetworkUtil.showNoInternetDialog(this);
        }

        playerView = findViewById(R.id.video_player);
        btnFullScreen = findViewById(R.id.bt_fullscreen);
        btnReplay = findViewById(R.id.exo_replay);
        btnForward = findViewById(R.id.exo_forward);
        btnBack = findViewById(R.id.iv_back);
        moviesTitle = findViewById(R.id.tv_content_title);
        btnLockscreen = findViewById(R.id.exo_lock);
        btnSetting = findViewById(R.id.iv_setting);
        contentTopSection = findViewById(R.id.top_movies_section_container);



        viewModel.fetchContent(uuid).observe(this, singleOttContent -> {
            try {
                if (singleOttContent.getData().getContentData() != null) {

                    ottContent = true;
                    if (singleOttContent.getData().getContentData().getTitle() != null) {
                        binding.moviesTitle.setText(singleOttContent.getData().getContentData().getTitle());
                        moviesTitle.setText(singleOttContent.getData().getContentData().getTitle());
                    }

                    if (singleOttContent.getData().getContentData().getYear() != null) binding.moviesYear.setText(singleOttContent.getData().getContentData().getYear());

                    if (singleOttContent.getData().getContentData().getSynopsisEnglish() != null) binding.moviesDescription.setText(singleOttContent.getData().getContentData().getSynopsisEnglish());

                    if (singleOttContent.getData().getContentData().getCastAndCrews() != null && !singleOttContent.getData().getContentData().getCastAndCrews().isEmpty()){
                        binding.castCrewText.setVisibility(View.VISIBLE);
                        binding.castCrewRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                        binding.castCrewRv.setAdapter(new CastCrewSliderAdapter(singleOttContent.getData().getContentData().getCastAndCrews()));
                    }else binding.castCrewText.setVisibility(View.GONE);

                    if (singleOttContent.getData().getContentData().getContentSource() != null){
                        List<ContentSource> contentSourceList = singleOttContent.getData().getContentData().getContentSource();
                        if (contentSourceList != null && !contentSourceList.isEmpty()) {
                            for (ContentSource c : contentSourceList) {
                                if (c.getSourceType().equalsIgnoreCase("content_path")) {
                                    videoPath = c.getContentSource();
                                }
                            }
                        }
                    }


                    if (videoPath != null && !videoPath.isEmpty()) {
                        initializePlayer(videoPath);
                    }

                    if (singleOttContent.getData().getContentData().getRuntime() != null){
                        long timeSec = Long.parseLong(singleOttContent.getData().getContentData().getRuntime());
                        int hours = (int) timeSec / 3600;
                        int temp = (int) timeSec - hours * 3600;
                        int mins = temp / 60;
                        temp = temp - mins * 60;
                        int secs = temp;

                        String requiredFormat = hours + "h " + mins + "m " + secs + "s";
                        // content runtime
                        binding.tvRunTime.setText(requiredFormat);
                    }

                    if (singleOttContent.getData().getContentData().getReleaseDate() != null) {
                        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date inputDate = inputDateFormat.parse(singleOttContent.getData().getContentData().getReleaseDate());
                            SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                            String outputDateStr = outputDateFormat.format(inputDate);
                            binding.tvReleaseDate.setText(outputDateStr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    if (singleOttContent.getData().getContentData().getGenre() != null) {

                        Gson gson = new Gson();
                        String[] array = gson.fromJson(singleOttContent.getData().getContentData().getGenre(), String[].class);

                        List<String> detailsList = new ArrayList<>();
                        for (String item : array) {
                            detailsList.add(item.trim());
                        }

                        binding.rvDetails.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                        binding.rvDetails.setAdapter(new DetailsSectionAdapter(detailsList));

                    }
                    hideProgressBar();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        viewModel.getRelatedOttContents(uuid).observe(this, singleContentRelatedContents -> {
            try {
                if (singleContentRelatedContents != null && !singleContentRelatedContents.isEmpty()) {
                    relatedContent = true;
                    binding.rvMoreLikeThis.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                    binding.rvMoreLikeThis.setAdapter(new GetRelatedContentsAdapter(singleContentRelatedContents, this,title,this));
                    hideProgressBar();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnReplay.setOnClickListener(view -> {
            if (exoPlayer.getCurrentPosition() == 0) exoPlayer.seekTo(0);
            else {
                exoPlayer.seekTo(exoPlayer.getCurrentPosition() - 10000);
            }
        });

        btnForward.setOnClickListener(view -> {
            exoPlayer.seekTo(exoPlayer.getCurrentPosition() + 10000);
        });

        btnSetting.setOnClickListener(v -> {
            if (!isShowingTrackSelectionDialog && TrackSelectionDialog.willHaveContent(exoPlayer)) {
                isShowingTrackSelectionDialog = true;
                TrackSelectionDialog trackSelectionDialog = TrackSelectionDialog.createForPlayer(exoPlayer,
                        /* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
                trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);
            }

        });

        btnFullScreen.setOnClickListener(view -> {
            if (!isFullScreen) {
                btnFullScreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_fullscreen_exit_24));
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
                params.width = params.MATCH_PARENT;
                params.height = params.MATCH_PARENT;
                playerView.setLayoutParams(params);
                contentTopSection.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                binding.videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
            } else {
                videoFullScreen();
            }
            isFullScreen = !isFullScreen;
        });

        btnBack.setOnClickListener(view -> {
            videoFullScreen();
            isFullScreen = !isFullScreen;
        });

        btnLockscreen.setOnClickListener(view -> {
            //change icon base on toggle lock screen or unlock screen
            if (!isLock) {
                btnLockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_lock_24));
            } else {
                btnLockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_lock_open_24));
            }
            isLock = !isLock;
            //method for toggle will do next
            lockScreen(isLock);
        });
    }

    private void hideProgressBar() {
        if (ottContent && relatedContent) {
            binding.contentSectionContainer.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    void lockScreen(boolean lock) {
        //just hide the control for lock screen and vise versa
        LinearLayout sec_mid = findViewById(R.id.sec_controlvid1);
        LinearLayout sec_bottom = findViewById(R.id.sec_controlvid2);
        if (lock) {
            sec_mid.setVisibility(View.INVISIBLE);
            sec_bottom.setVisibility(View.INVISIBLE);
            btnBack.setVisibility(View.INVISIBLE);
            moviesTitle.setVisibility(View.INVISIBLE);
            btnSetting.setVisibility(View.INVISIBLE);
        } else {
            sec_mid.setVisibility(View.VISIBLE);
            sec_bottom.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
            moviesTitle.setVisibility(View.VISIBLE);
            btnSetting.setVisibility(View.VISIBLE);
            moviesTitle.setVisibility(View.VISIBLE);
        }
    }

    private void videoFullScreen() {
        btnFullScreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_fullscreen_24));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
        params.width = params.MATCH_PARENT;
        params.height = (int) (200 * getApplicationContext().getResources().getDisplayMetrics().density);
        playerView.setLayoutParams(params);
        contentTopSection.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initializePlayer(String videoPath) {
        try {
            Uri videoUri = Uri.parse(videoPath);
            exoPlayer = new ExoPlayer.Builder(this).build();
            binding.videoPlayer.setPlayer(exoPlayer);
            MediaItem mediaItem = MediaItem.fromUri(videoUri);
            exoPlayer.setMediaItem(mediaItem);
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.prepare();
            exoPlayer.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            exoPlayer.pause();
            exoPlayer.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (exoPlayer != null){
            exoPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (exoPlayer != null){
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {


        if (Constants.IS_MORE_CONTENT){
            String id = Constants.getSharedPref(this,Constants.CONTENT_ID);
            String slug = Constants.getSharedPref(this,Constants.CONTENT_SLUG);
            String title = Constants.getSharedPref(this,Constants.CONTENT_SECTION_TITLE);
            if (Constants.IS_MORE_HOME){
                Intent intent = new Intent(this, MoreActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(Constants.CONTENT_ID, id);
                intent.putExtra(Constants.CONTENT_SECTION_TITLE, title);
                intent.putExtra(Constants.CONTENT_IS_HOME,true);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(this, MoreActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(Constants.CONTENT_SECTION_TITLE, title);
                intent.putExtra(Constants.CONTENT_SLUG, slug);
                intent.putExtra(Constants.CONTENT_IS_HOME,false);
                startActivity(intent);
                finish();
            }
            Constants.IS_MORE_CONTENT = false;

        }else if (Constants.IS_TUNES){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.player_container, new TunesFragment());
            fragmentTransaction.addToBackStack(null); // Add this line if you want to allow the user to navigate back to the previous fragment
            fragmentTransaction.commit();
            finish();
        }
        else {
            Constants.IS_FROM_PLAYER = true;
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void passContentData(String uuid) {
        if (uuid != null){
            finish();
            startActivity(new Intent(getApplicationContext(), PlayerActivity.class).putExtra(Constants.CONTENT_UUID, uuid).putExtra(Constants.CONTENT_SECTION_TITLE,title));
        }
    }
}
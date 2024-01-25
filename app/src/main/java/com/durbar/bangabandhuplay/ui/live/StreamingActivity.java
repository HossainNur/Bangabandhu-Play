package com.durbar.bangabandhuplay.ui.live;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.ActivitySearchResultBinding;
import com.durbar.bangabandhuplay.databinding.ActivityStreamingBinding;

public class StreamingActivity extends AppCompatActivity {

    private ActivityStreamingBinding binding;
    private LiveStreamingViewModel viewModel;
    private int userRole = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStreamingBinding.inflate(getLayoutInflater());
        viewModel =  new ViewModelProvider(this).get(LiveStreamingViewModel.class);
        setContentView(binding.getRoot());

        RadioGroup userRadioButton = (RadioGroup) findViewById(R.id.radio_group);
        int checked = userRadioButton.getCheckedRadioButtonId();
        RadioButton audienceButton = (RadioButton) findViewById(R.id.audience_rb);
        userRole = (checked == audienceButton.getId()) ? 0 : 1;

        fetchActiveLive();
    }

    private void fetchActiveLive() {
        viewModel.getLiveStreaming().observe(this,data -> {
            if (data != null && data.getStatus() == 1){
                String channelName = data.getChannelName();
                String token = data.getToken();
                String appId = data.getAppId();
                startActivity(new Intent(getApplicationContext(), VideoActivity.class)
                        .putExtra("UserRole", userRole)
                        .putExtra("appId",appId).putExtra("channel_name",channelName).putExtra("token",token));
            }else {
                Toast.makeText(getApplicationContext(),"No live Right now ",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
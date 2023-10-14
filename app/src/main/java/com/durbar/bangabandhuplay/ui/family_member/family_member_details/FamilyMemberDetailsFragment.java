package com.durbar.bangabandhuplay.ui.family_member.family_member_details;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberDeatilsBinding;
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity;
import com.durbar.bangabandhuplay.utils.NavigationHelper;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

public class FamilyMemberDetailsFragment extends Fragment {

    private String title,shortTitle,description,image;
    private AppBarLayout appBarLayout;
    private FragmentFamilyMemberDeatilsBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberDeatilsBinding.inflate(inflater,container,false);
        image = NavigationHelper.getINSTANCE().getImage();
        title = NavigationHelper.getINSTANCE().getTitle();
        shortTitle = NavigationHelper.getINSTANCE().getShortTitle();
        description = NavigationHelper.getINSTANCE().getDescription();
        appBarLayout = NavigationHelper.getINSTANCE().getAppBarLayout();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        if (title != null) binding.familyDetailsTitle.setText(title);
        if (shortTitle != null) binding.familyDetailsShortTitle.setText(shortTitle);
        if (image != null) Picasso.get().load(image).fit().into(binding.familyDetailsImage);
        if (description != null){
            binding.familyDetailsDesc.setText(Html.fromHtml(description));
        }

        binding.ivSearch.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchResultActivity.class));
        });

        binding.ivBack.setOnClickListener(v -> {
            navController.navigate(R.id.familyMemberFragment);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        appBarLayout.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        appBarLayout.setVisibility(View.VISIBLE);
    }
}
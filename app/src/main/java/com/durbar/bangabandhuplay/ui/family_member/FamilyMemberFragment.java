package com.durbar.bangabandhuplay.ui.family_member;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.durbar.bangabandhuplay.R;
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberBinding;
import com.durbar.bangabandhuplay.utils.NavigationHelper;
import com.durbar.bangabandhuplay.utils.NetworkUtil;

public class FamilyMemberFragment extends Fragment implements FamilyMemberAdapter.CallBack{
    private FragmentFamilyMemberBinding binding;
    private FamilyMemberViewModel viewModel;
    private NavController navController;
    private boolean familyMember = false;
    private NetworkUtil networkUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberBinding.inflate(inflater,container,false);
        viewModel =  new ViewModelProvider(this).get(FamilyMemberViewModel.class);
        networkUtil = NetworkUtil.getInstance(requireActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        if (!networkUtil.isNetworkAvailable()) {
            NetworkUtil.showNoInternetDialog(requireActivity());
        }

        viewModel.fetchFamilyMemberPhotos().observe(requireActivity(),data -> {
            try {
                if (data != null && !data.isEmpty()){
                    familyMember = true;
                    binding.familyMemberRv.setLayoutManager(new GridLayoutManager(requireContext(),3));
                    binding.familyMemberRv.setAdapter(new FamilyMemberAdapter(data,requireContext(),this));
                    hideProgressBar();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    private void hideProgressBar() {
        if (familyMember){
            binding.familyMemberRv.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }

    }


    @Override
    public void familyMemberDetails(String title, String shortTitle, String description, String image) {
        if (title != null && shortTitle != null && description != null && image != null){
            navController.navigate(R.id.familyMemberDeatilsFragment);
            NavigationHelper.getINSTANCE().setTitle(title);
            NavigationHelper.getINSTANCE().setShortTitle(shortTitle);
            NavigationHelper.getINSTANCE().setDescription(description);
            NavigationHelper.getINSTANCE().setImage(image);
        }
    }

}
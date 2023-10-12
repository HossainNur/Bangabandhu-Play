package com.durbar.bangabandhuplay.ui.family_member;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberBinding;
import com.squareup.picasso.Picasso;
public class FamilyMemberFragment extends Fragment implements FamilyMemberAdapter.CallBack{
    private FragmentFamilyMemberBinding binding;
    private FamilyMemberViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberBinding.inflate(inflater,container,false);
        viewModel =  new ViewModelProvider(this).get(FamilyMemberViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.fetchFamilyMemberPhotos().observe(requireActivity(),data -> {
            try {
                if (data != null && !data.isEmpty()){
                    binding.familyMemberRv.setLayoutManager(new GridLayoutManager(requireContext(),3));
                    binding.familyMemberRv.setAdapter(new FamilyMemberAdapter(data,requireContext(),this));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });


    }

    @Override
    public void familyMemberDetails(String title, String shortTitle, String description, String image) {
        binding.familyMemberRv.setVisibility(View.GONE);
        binding.familyDetailsContainer.setVisibility(View.VISIBLE);
        if (title != null) binding.familyDetailsTitle.setText(title);
        if (shortTitle != null) binding.familyDetailsShortTitle.setText(shortTitle);
        if (image != null) Picasso.get().load(image).fit().into(binding.familyDetailsImage);
        if (description != null) binding.familyDetailsDesc.setText(description);
    }
}
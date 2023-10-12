package com.durbar.bangabandhuplay.ui.family_members.family_member_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.durbar.bangabandhuplay.MainActivity
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberDetailsBinding
import com.durbar.bangabandhuplay.utils.Constants
import com.squareup.picasso.Picasso


class FamilyMemberDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFamilyMemberDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        MainActivity.checkAppBarVisibility()

        binding = FragmentFamilyMemberDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBackArrow.setOnClickListener {
            Constants.MainToolBarVisibility = true
            findNavController().navigate(R.id.familyMemberFragment)
        }

        // Hide previous Toolbar
       // (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        // Set the Toolbar as the ActionBar for the fragment's hosting activity
        /*(requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar2FamilyDetails)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Family Member's"*/

       /* (activity as AppCompatActivity).setSupportActionBar(binding.toolbar2FamilyDetails)

        // Customize Toolbar 2
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Family Member's"
            setDisplayHomeAsUpEnabled(true)
            // Customize other attributes or set click listeners as needed.
        }*/

        //retrieving bundle data from FamilyMemberFragment
        val id= arguments?.getInt("ID")
        val imageUrl= arguments?.getString("imageUrl")
        val name= arguments?.getString("name")
        val shortTitle= arguments?.getString("shortTitle")
        val description= arguments?.getString("description")

        Picasso.get().load(imageUrl).into(binding.ivFamilyMemberDetails)
        binding.tvFamilyMemberName.text = name
        binding.tvFamilyMemTitle.text = shortTitle
        binding.tvFamilyMemDescription.text = description

        requireActivity().supportFragmentManager.popBackStackImmediate("FamilyMemberFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)


    }

}
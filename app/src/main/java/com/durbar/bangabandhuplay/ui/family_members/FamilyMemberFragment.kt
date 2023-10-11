package com.durbar.bangabandhuplay.ui.family_members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberBinding


class FamilyMemberFragment : Fragment() {

    private lateinit var binding: FragmentFamilyMemberBinding

    private lateinit var familyMemberViewModel: FamilyMemberViewModel
    private lateinit var adapter: FamilyMemberAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFamilyMemberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       /* binding.rcvFamilyMember.layoutManager = GridLayoutManager(requireContext(),3)
        val imageResIds = listOf(
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,
          com.durbar.bangabandhuplay.R.drawable.dhaka_attack,

        ) // Replace with your image resource IDs
        val adapter = FamilyMemberAdapter(requireContext(), imageResIds)
        binding.rcvFamilyMember.adapter = adapter*/

        getDatAndNavigation()

    }
    private fun getDatAndNavigation(){
        // getting adapter data & sending to another fragment through call back
        val callback : (Int, Int, String, String, String, String) -> Unit = {position, id, imageUrl, name, shortTitle, description  ->

            val bundle= Bundle()   //bundle
            bundle.putInt("ID",id)
            bundle.putString("imageUrl",imageUrl)
            bundle.putString("name",name)
            bundle.putString("shortTitle",shortTitle)
            bundle.putString("description",description)

            findNavController().navigate(R.id.action_familyMemberFragment_to_familyMemberDetailsFragment, bundle)
        }


        familyMemberViewModel = ViewModelProvider(this).get(FamilyMemberViewModel::class.java)

        familyMemberViewModel.getPhotos()?.observe(viewLifecycleOwner) { data ->
            data?.let {
                binding.rcvFamilyMember.layoutManager = GridLayoutManager(requireContext(), 3)
                // adapter = FamilyMemberAdapter(it)
                adapter = FamilyMemberAdapter(it, callback)
                binding.rcvFamilyMember.adapter = adapter

            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
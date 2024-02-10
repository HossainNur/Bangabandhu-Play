package com.durbar.bangabandhuplay.ui.family_member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.family_member.Data
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberBinding
import com.durbar.bangabandhuplay.utils.NavigationHelper
import com.durbar.bangabandhuplay.utils.checkInternet

class FamilyMemberFragment : Fragment(), FamilyMemberAdapter.CallBack {
    private var binding: FragmentFamilyMemberBinding? = null
    private var viewModel: FamilyMemberViewModel? = null
    private var navController: NavController? = null
    private var familyMember = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FamilyMemberViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().checkInternet()
        navController = findNavController(view)
        viewModel!!.fetchFamilyMemberPhotos().observe(requireActivity()) { data ->
            try {
                if (data != null && !data.isEmpty()) {
                    familyMember = true
                    binding!!.familyMemberRv.layoutManager = GridLayoutManager(requireContext(), 3)
                    binding!!.familyMemberRv.adapter =
                        FamilyMemberAdapter(data, requireContext(), this)
                    hideProgressBar()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun hideProgressBar() {
        if (familyMember) {
            binding!!.familyMemberRv.visibility = View.VISIBLE
            binding!!.progressBar.visibility = View.GONE
        }
    }

    override fun familyMemberDetails(
        title: String,
        shortTitle: String,
        description: String,
        image: String
    ) {
        if (title != null && shortTitle != null && description != null && image != null) {
            navController!!.navigate(R.id.familyMemberDeatilsFragment)
            NavigationHelper.instanceNavHelper?.title = title
            NavigationHelper.instanceNavHelper?.shortTitle = shortTitle
            NavigationHelper.instanceNavHelper?.description = description
            NavigationHelper.instanceNavHelper?.image = image
        }
    }
}
package com.durbar.bangabandhuplay.ui.family_member.family_member_details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentFamilyMemberDeatilsBinding
import com.durbar.bangabandhuplay.ui.search.SearchResultActivity
import com.durbar.bangabandhuplay.utils.NavigationHelper
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso

class FamilyMemberDetailsFragment : Fragment() {
    private var title: String? = null
    private var shortTitle: String? = null
    private var description: String? = null
    private var image: String? = null
    private var appBarLayout: AppBarLayout? = null
    private var binding: FragmentFamilyMemberDeatilsBinding? = null
    private var navController: NavController? = null
    private var htmlDescription: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberDeatilsBinding.inflate(inflater, container, false)
        image = NavigationHelper.instanceNavHelper?.image
        title = NavigationHelper.instanceNavHelper?.title
        shortTitle = NavigationHelper.instanceNavHelper?.shortTitle
        description = NavigationHelper.instanceNavHelper?.description
        appBarLayout = NavigationHelper.instanceNavHelper?.appBarLayout
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)
        if (title != null) binding!!.familyDetailsTitle.text = title
        if (shortTitle != null) binding!!.familyDetailsShortTitle.text = shortTitle
        if (image != null) Picasso.get().load(image).fit().into(binding!!.familyDetailsImage)
        if (description != null) {
            htmlDescription = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                description.toString()
            }
            binding!!.familyDetailsDesc.text = htmlDescription
        }
        binding!!.ivSearch.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    context, SearchResultActivity::class.java
                )
            )
        }
        binding!!.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        appBarLayout!!.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        appBarLayout!!.visibility = View.VISIBLE
    }
}
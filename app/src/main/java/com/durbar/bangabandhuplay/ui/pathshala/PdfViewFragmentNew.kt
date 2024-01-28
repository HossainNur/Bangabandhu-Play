package com.durbar.bangabandhuplay.ui.pathshala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.durbar.bangabandhuplay.databinding.FragmentPdfViewNewBinding
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo
/**
 * PDF using 'com.github.afreakyelf:Pdf-Viewer:v2.0.5'
 */
class PdfViewFragmentNew : Fragment() {
    private lateinit var binding: FragmentPdfViewNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPdfViewNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val pdfUrl = arguments?.getString("pdfurl")

        if (!pdfUrl.isNullOrEmpty()) {
            binding.pdfView.initWithUrl(
                url = pdfUrl,
                lifecycleCoroutineScope = lifecycleScope,
                lifecycle = lifecycle
            )
        }

        if (!pdfUrl.isNullOrEmpty()) {
            launchPdfFromUrl(pdfUrl)
        }else{
            Toast.makeText(requireContext(), "Sorry! File not available", Toast.LENGTH_SHORT).show()
        }

    }

    private fun launchPdfFromUrl(url: String) {

        startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context = requireContext(),
                pdfUrl = url,
                pdfTitle = "PDF Title",
                saveTo = saveTo.ASK_EVERYTIME,
                enableDownload = true
            )
        )
    }

}
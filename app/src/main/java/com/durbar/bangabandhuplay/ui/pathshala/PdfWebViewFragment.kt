package com.durbar.bangabandhuplay.ui.pathshala

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentPdfWebViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PdfWebViewFragment : Fragment() {
    private lateinit var binding: FragmentPdfWebViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPdfWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pdfUrl = arguments?.getString("pdfurl")
        Log.d("pdfUrl", "onViewCreated: $pdfUrl")

        if (!pdfUrl.isNullOrEmpty()) {
            loadPDF(pdfUrl)
        } else {
            Toast.makeText(requireContext(), "Sorry! File not available", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun loadPDF(pdfLink: String) {
        val webSettings: WebSettings = binding.webview.settings
        webSettings.javaScriptEnabled = true  // Enable JavaScript if needed

        // Set a WebViewClient to handle navigation/progressbar inside the WebView
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // binding.progressBar.visibility = View.GONE
                CoroutineScope(Dispatchers.Main).launch {
                    // Delay for 4 seconds
                    delay(4000)
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        // Load the PDF from the URL with google docs
        binding.webview.loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfLink")
    }

}
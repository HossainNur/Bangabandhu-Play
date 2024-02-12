package com.durbar.bangabandhuplay.ui.pathshala

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.databinding.FragmentPdfViewBinding
import com.durbar.bangabandhuplay.utils.checkInternet
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * PDF using 'com.github.barteksc:android-pdf-viewer:2.8.2'
 */
class PdfViewFragment : Fragment() {
    lateinit var pdfView: PDFView
    private lateinit var binding: FragmentPdfViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPdfViewBinding.inflate(inflater, container, false)
        requireContext().checkInternet()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pdfUrl = arguments?.getString("pdfurl")

         pdfView = binding.idPDFView

        if (!pdfUrl.isNullOrEmpty()) {
              LoadPdfTask(pdfView, binding.progressBar).execute(pdfUrl)
        } else {
            Toast.makeText(requireContext(), "Sorry! File not available", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    class LoadPdfTask(private val pdfView: PDFView, private val progressBar: ProgressBar) :
        AsyncTask<String, Void, InputStream>() {

        override fun onPreExecute() {
            super.onPreExecute()
            // Show the progress bar before loading the PDF
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String?): InputStream? {
            var inputStream: InputStream? = null
            try {
                val url = URL(params[0])
                val urlConnection: HttpURLConnection = url.openConnection() as HttpsURLConnection

                if (urlConnection.responseCode == 200) {
                    inputStream = BufferedInputStream(urlConnection.inputStream)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
            return inputStream
        }

        override fun onPostExecute(result: InputStream?) {
            // Load the PDF into the PDFView
            pdfView.fromStream(result).load()
            // Hide the progress bar after loading the PDF

            // progressBar.visibility = View.GONE

            CoroutineScope(Dispatchers.Main).launch {
                // Delay for 5 seconds
                delay(5000)

                // Hide the progress bar after 5 seconds
                progressBar.visibility = View.GONE
            }

        }
    }

}
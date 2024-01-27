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
import com.github.barteksc.pdfviewer.PDFView
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PdfViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PdfViewFragment : Fragment() {
    lateinit var pdfView: PDFView
    private lateinit var binding: FragmentPdfViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPdfViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pdfUrl = arguments?.getString("pdfurl")

        pdfView = binding.idPDFView

        if (!pdfUrl.isNullOrEmpty()) {
            LoadPdfTask(pdfView, binding.progressBar).execute(pdfUrl)
        } else {
            Toast.makeText(requireContext(), "Sorry! Something went wrong!!", Toast.LENGTH_SHORT).show()
        }

        // for getting the current fragment, erase if not needed
        val navController =
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
                Log.e("onDestinationChanged", "onDestinationChanged: " + destination.label)

            }
        })
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
            progressBar.visibility = View.GONE

        }
    }
}
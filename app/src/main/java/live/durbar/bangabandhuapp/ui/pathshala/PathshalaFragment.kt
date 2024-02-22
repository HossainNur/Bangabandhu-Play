package live.durbar.bangabandhuapp.ui.pathshala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import live.durbar.bangabandhuapp.databinding.FragmentPathshalaBinding


class PathshalaFragment : Fragment() {
    private lateinit var binding: FragmentPathshalaBinding
    private lateinit var viewModel: PathshalaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPathshalaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PathshalaViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.progressBar.visibility = View.VISIBLE

        viewModel.fetchPdfs().observe(viewLifecycleOwner) {pathshala ->
            try {
                if (pathshala?.data != null){
                    val concatAdapter = ConcatAdapter()
                    pathshala.data?.data?.forEach {
                        concatAdapter.addAdapter(CommonAdapter(it))
                    }
                    binding.rcvPathshala.adapter = concatAdapter
                    binding.progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
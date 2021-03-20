package com.example.rotrofitwithcoroutineexampletow.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.actionlistenerexample.home.HomeViewmodel
import com.example.rotrofitwithcoroutineexampletow.data.api.ApiHelper
import com.example.rotrofitwithcoroutineexampletow.data.api.RetrofitBuilder
import com.example.rotrofitwithcoroutineexampletow.data.model.ImageItem
import com.example.rotrofitwithcoroutineexampletow.databinding.FragmentHomeBinding
import com.example.rotrofitwithcoroutineexampletow.ui.base.ViewModelFactory


class HomeFragment : Fragment() {

    val TAG = "giangtd"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewmodel


    private val observerListData = Observer<List<ImageItem>?> {
        it?.let {
            it.forEach {
                Log.d(TAG, "observerListData: $it")
            }
        }
    }

    private val observerLoading = Observer<Boolean> {
        if (!it) {
            binding.loading.visibility = View.GONE
        } else {
            binding.loading.visibility = View.VISIBLE
        }
    }

    private val observerExceptionNetwork = Observer<Boolean> {
        if (it) {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        } else {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(HomeViewmodel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.networkData.observe(viewLifecycleOwner, observerListData)
        viewModel.exceptionNetWork.observe(viewLifecycleOwner, observerExceptionNetwork)
        viewModel.loading.observe(viewLifecycleOwner, observerLoading)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
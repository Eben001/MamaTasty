package com.ebenezer.gana.mamatasty.ui.foodList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ebenezer.gana.mamatasty.R
import com.ebenezer.gana.mamatasty.data.network.ErrorCode
import com.ebenezer.gana.mamatasty.data.network.Status
import com.ebenezer.gana.mamatasty.databinding.FragmentFoodListBinding


class FoodListFragment : Fragment() {

    private val viewModel: FoodListViewModel by viewModels()

    private var _binding: FragmentFoodListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodListBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvFood) {
            adapter = FoodListAdapter {
                val action =
                    FoodListFragmentDirections.actionFoodListFragmentToFoodDetailsFragment(it.id)
                findNavController().navigate(action)
            }
        }

        viewModel.foodList.observe(viewLifecycleOwner) {
            (binding.rvFood.adapter as FoodListAdapter).submitList(it)
            if (it.isEmpty()) {
                viewModel.fetchFromNetwork()
            }
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) { loadingStatus ->

            when (loadingStatus?.status) {
                Status.LOADING -> {

                    binding.loadingStatus.visibility = View.VISIBLE
                    binding.statusError.visibility = View.INVISIBLE
                }
                Status.SUCCESS -> {
                    binding.loadingStatus.visibility = View.INVISIBLE
                    binding.statusError.visibility = View.INVISIBLE
                }
                Status.ERROR -> {
                    binding.loadingStatus.visibility = View.INVISIBLE
                    showErrorMessage(loadingStatus.errorCode, loadingStatus.message)
                    binding.statusError.visibility = View.VISIBLE
                }
                else -> {}
            }

            binding.swipeRefresh.isRefreshing = false


        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
    }


    private fun showErrorMessage(errorCode: ErrorCode?, message: String?) {
        when (errorCode) {
            ErrorCode.NO_DATA -> binding.statusError.text =
                getString(R.string.error_no_data)
            ErrorCode.NETWORK_ERROR -> binding.statusError.text =
                getString(R.string.error_network)
            ErrorCode.UNKNOWN_ERROR -> binding.statusError.text =
                getString(R.string.unknown_error, message)
            else -> {}
        }
    }


}
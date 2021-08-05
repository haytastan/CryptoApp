package com.muhammed.mvvm_hilt_coinapp.ui.view.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammed.mvvm_hilt_coinapp.R
import com.muhammed.mvvm_hilt_coinapp.databinding.FragmentHomeBinding
import com.muhammed.mvvm_hilt_coinapp.ui.adapter.HomeAdapter
import com.muhammed.mvvm_hilt_coinapp.ui.adapter.HomeRoomAdapter
import com.muhammed.mvvm_hilt_coinapp.ui.viewmodel.HomeViewModel
import com.muhammed.mvvm_hilt_coinapp.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeRoomAdapter: HomeRoomAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setObserver()
        loadRecycler()
        favoritesCoin()
        allCoin()
        refreshAllCoin()
    }

    private fun setObserver() {
        homeViewModel.getCoinList().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    binding.homeProgressbar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.homeProgressbar.visibility = View.GONE
                    it.data?.let {
                        homeAdapter.dataModel.clear()
                        homeAdapter.data.addAll((it))
                        homeAdapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    binding.homeProgressbar.visibility = View.GONE

                }
            }
        })
    }

    private fun loadRecycler() {
        homeAdapter = HomeAdapter()
        binding.homeRecyclerView.setHasFixedSize(true)
        binding.homeRecyclerView.adapter = homeAdapter
        homeAdapter.onClickItem {
            val coinData = bundleOf("coinID" to it.id)
            Log.d(TAG, "loadRecycler: $coinData")
            findNavController().navigate(R.id.detailFragment, coinData)
        }
    }

    private fun favoritesCoin() {

        binding.myCoin.setOnClickListener {
            homeViewModel.getFavoritesCoin.observe(viewLifecycleOwner, {
                homeRoomAdapter = HomeRoomAdapter()
                binding.homeRecyclerView.adapter = homeRoomAdapter
                homeRoomAdapter.setData(it)

            })
        }
    }

    private fun allCoin() {
        binding.homeAllCoin.setOnClickListener {
            binding.homeRecyclerView.visibility = View.VISIBLE
            binding.homeRecyclerView.adapter = homeAdapter
        }
    }

    private fun refreshAllCoin() {

        binding.homeSwipeRefreshLayout.setOnRefreshListener {
            homeAdapter.refreshData()
            binding.homeSwipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
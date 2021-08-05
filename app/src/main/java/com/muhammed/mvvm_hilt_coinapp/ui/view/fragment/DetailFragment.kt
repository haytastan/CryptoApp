package com.muhammed.mvvm_hilt_coinapp.ui.view.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammed.mvvm_hilt_coinapp.R
import com.muhammed.mvvm_hilt_coinapp.data.model.CurrentPrice
import com.muhammed.mvvm_hilt_coinapp.data.model.DetailModel
import com.muhammed.mvvm_hilt_coinapp.data.model.Image
import com.muhammed.mvvm_hilt_coinapp.data.model.MarketData
import com.muhammed.mvvm_hilt_coinapp.databinding.FragmentDetailBinding
import com.muhammed.mvvm_hilt_coinapp.ui.view.itemviewstate.CoinItemViewState
import com.muhammed.mvvm_hilt_coinapp.ui.viewmodel.DetailViewModel
import com.muhammed.mvvm_hilt_coinapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()
    private var coinID = ""
    private var coinImage = ""
    private var priceChange = ""
    private var buttonClick: Boolean = true
    private var click: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        coinID = requireArguments().getString("coinID").toString()
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        setObserver()
        loadInitData()
        favoritesButton()
        backButton()
    }


    private fun setObserver() {
        detailViewModel.getCoinDetail().observe(viewLifecycleOwner, { state ->

            when (state.status) {
                Status.LOADING -> {
                    binding.detaiProgress.visibility = View.VISIBLE
                    binding.detailCoinPrice.visibility = View.INVISIBLE
                }
                Status.SUCCESS -> {
                    state.data?.let {
                        binding.detaiProgress.visibility = View.GONE
                        binding.detailCoinPrice.visibility = View.VISIBLE

                        binding.detailData = CoinItemViewState(it)

//                        coinImage = it.image.toString()
                        coinImage = it.image.toString()
                        priceChange = it.market_data?.price_change_percentage_24h.toString()
                    }
                }
                Status.ERROR -> {
                    binding.detailCoinPrice.visibility = View.INVISIBLE
                    binding.detaiProgress.visibility = View.GONE
                }
            }
        })
    }

    private fun loadInitData() {
        detailViewModel.id = coinID

    }

    private fun favoritesButton() {


        binding.favoritesButton.setOnClickListener {

            click++


            if (click % 2 == 0) {
                Toast.makeText(context, "Favorilerden çıkarıldı", Toast.LENGTH_SHORT).show()

                binding.favoritesButton.setImageResource(R.drawable.ic_launcher_star_empty_foreground)

                CoroutineScope(Dispatchers.Main).launch {
                    var image = Image(binding.detailCoinImage.toString())
                    var marketData = MarketData(
                        CurrentPrice(binding.detailCoinPrice.toString().toDoubleOrNull()),
                        binding.detailPriceChange24h.toString().toDoubleOrNull()
                    )

                    var coinData = DetailModel(
                        0, coinID, binding.detailCoinName.toString(),
                        binding.detailCoinSymbol.toString(), image, marketData
                    )

                    detailViewModel.deleteCoin(coinData)
                    Log.d(TAG, "favorilerden cikti: ${detailViewModel.deleteCoin(coinData)}")

                }

            } else {
                Toast.makeText(context, "Favorilere eklendi", Toast.LENGTH_SHORT).show()
                binding.favoritesButton.setImageResource(R.drawable.ic_launcher_star_full_foreground)
                CoroutineScope(Dispatchers.Main).launch {
//                    var image = Image(binding.detailCoinImage.toString())
//                    var marketData = MarketData(
//                        CurrentPrice(binding.detailCoinPrice.text.toString().toDoubleOrNull()),
//                        binding.detailPriceChange24h.text.toString().toDoubleOrNull()
//                    )

                    var coinData = DetailModel(
                        coinId = 0,
                        symbol = binding.detailCoinSymbol.text.toString().uppercase(),
                        name = binding.detailCoinName.text.toString(),
                        image = Image(large = binding.detailCoinImage.toString()),
                        market_data = MarketData(
                            current_price = CurrentPrice(
                                type = binding.detailCoinPrice.text.toString().toDoubleOrNull()
                            ),
                            price_change_percentage_24h = binding.detailPriceChange24h.text.toString()
                                .toDoubleOrNull()
                        )
                    )

                    Log.d(TAG, "favorilere data: ${coinData}")


                    detailViewModel.insertCoin(coinData)

                }
            }
        }
    }

    private fun backButton() {
        binding.detailBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
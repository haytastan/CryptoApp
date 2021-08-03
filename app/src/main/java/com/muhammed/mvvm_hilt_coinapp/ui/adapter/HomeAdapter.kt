package com.muhammed.mvvm_hilt_coinapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.mvvm_hilt_coinapp.R
import com.muhammed.mvvm_hilt_coinapp.data.model.HomeModel
import com.muhammed.mvvm_hilt_coinapp.databinding.HomeRowItemBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    var dataModel = ArrayList<HomeModel>()
    var data: MutableList<HomeModel> = mutableListOf()

    init {
        data = dataModel
    }

    private lateinit var coinClick: (HomeModel) -> Unit
    fun onClickItem(onClick: (HomeModel) -> Unit) {
        this.coinClick = onClick
    }

    class HomeViewHolder(val binding: HomeRowItemBinding): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(homeModel: HomeModel, coinClick: (HomeModel) -> Unit) {
            binding.rowData = homeModel
            binding.root.setOnClickListener {
                coinClick.invoke(homeModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding : HomeRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.home_row_item,parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position],coinClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
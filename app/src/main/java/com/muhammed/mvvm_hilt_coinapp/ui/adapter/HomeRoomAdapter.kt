package com.muhammed.mvvm_hilt_coinapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.mvvm_hilt_coinapp.R
import com.muhammed.mvvm_hilt_coinapp.data.local.CoinDao
import com.muhammed.mvvm_hilt_coinapp.data.model.DetailModel
import com.muhammed.mvvm_hilt_coinapp.data.repository.CoinRepository
import com.muhammed.mvvm_hilt_coinapp.databinding.RoomItemLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeRoomAdapter: RecyclerView.Adapter<HomeRoomAdapter.HomeRoomViewHolder>() {

    private var favCoinList = emptyList<DetailModel>()

    class HomeRoomViewHolder(val binding: RoomItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailModel: DetailModel) {
            binding.roomData = detailModel

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRoomViewHolder {
        val binding: RoomItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.room_item_layout, parent, false
        )
        return HomeRoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRoomViewHolder, position: Int) {
        holder.bind(favCoinList[position])
        holder.binding.inFavorites.setOnClickListener {
            holder.binding.inFavorites.setImageResource(R.drawable.ic_launcher_star_empty_foreground)
            //Adapter'den veri silme işlemin de bir kaç yöntem denedim ve çözemedim.
            //Sanırım bu işlemin adapterden olmaması gerekiyor.
            notifyDataSetChanged()
//            val item = favCoinList[position]
//            (favCoinList as MutableList).remove(item)
//            notifyItemChanged(position)
//            coinDao.deleteCoin(item)
        }
    }

    override fun getItemCount(): Int {
        return favCoinList.size
    }

    fun setData(coin: List<DetailModel>) {
        this.favCoinList = coin
        notifyDataSetChanged()
    }
}
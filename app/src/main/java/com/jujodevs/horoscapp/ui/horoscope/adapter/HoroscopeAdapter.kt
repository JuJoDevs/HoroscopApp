package com.jujodevs.horoscapp.ui.horoscope.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jujodevs.horoscapp.databinding.ItemHoroscopeBinding
import com.jujodevs.horoscapp.domain.model.HoroscopeInfo

class HoroscopeAdapter(private var horoscopeList: List<HoroscopeInfo> = emptyList(),
                       private val onItemSelected:(HoroscopeInfo) -> Unit): RecyclerView.Adapter<HoroscopeViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list:List<HoroscopeInfo>){
        horoscopeList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(ItemHoroscopeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = horoscopeList.size

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position], onItemSelected)
    }
}
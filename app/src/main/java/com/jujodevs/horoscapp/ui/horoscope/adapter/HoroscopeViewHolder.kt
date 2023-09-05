package com.jujodevs.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.jujodevs.horoscapp.databinding.ItemHoroscopeBinding
import com.jujodevs.horoscapp.domain.HoroscopeInfo

class HoroscopeViewHolder(private val binding: ItemHoroscopeBinding):RecyclerView.ViewHolder(binding.root){

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit){
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.apply {
            text = context.getText(horoscopeInfo.name)
        }

        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope){
                onItemSelected(horoscopeInfo)
            }
        }
    }

    fun startRotationAnimation(view: View, endAction:() -> Unit){
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction { endAction() }
            start()
        }
    }

}
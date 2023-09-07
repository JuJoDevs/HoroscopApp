package com.jujodevs.horoscapp.ui.horoscope

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jujodevs.horoscapp.databinding.FragmentHoroscopeBinding
import com.jujodevs.horoscapp.domain.model.HoroscopeInfo
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Aquarius
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Aries
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Cancer
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Capricorn
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Gemini
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Leo
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Libra
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Pisces
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Sagittarius
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Scorpio
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Taurus
import com.jujodevs.horoscapp.domain.model.HoroscopeModel.Virgo
import com.jujodevs.horoscapp.ui.core.FragmentBinding
import com.jujodevs.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment() : FragmentBinding<FragmentHoroscopeBinding>() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private lateinit var horoscopeAdapter: HoroscopeAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHoroscopeBinding
        get() = FragmentHoroscopeBinding::inflate

    override fun setup() {
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter {
            val type = when(it){
                HoroscopeInfo.Aquarius -> Aquarius
                HoroscopeInfo.Aries -> Aries
                HoroscopeInfo.Cancer -> Cancer
                HoroscopeInfo.Capricorn -> Capricorn
                HoroscopeInfo.Gemini -> Gemini
                HoroscopeInfo.Leo -> Leo
                HoroscopeInfo.Libra -> Libra
                HoroscopeInfo.Pisces -> Pisces
                HoroscopeInfo.Sagittarius -> Sagittarius
                HoroscopeInfo.Scorpio -> Scorpio
                HoroscopeInfo.Taurus -> Taurus
                HoroscopeInfo.Virgo -> Virgo
            }

            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        }
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeViewModel.horoscope.collect{
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }
}
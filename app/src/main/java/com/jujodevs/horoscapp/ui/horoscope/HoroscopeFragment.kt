package com.jujodevs.horoscapp.ui.horoscope

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jujodevs.horoscapp.databinding.FragmentHoroscopeBinding
import com.jujodevs.horoscapp.ui.core.FragmentBinding


class HoroscopeFragment() : FragmentBinding<FragmentHoroscopeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHoroscopeBinding
        get() = FragmentHoroscopeBinding::inflate

    override fun setup() {

    }
}
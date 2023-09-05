package com.jujodevs.horoscapp.ui.luck

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jujodevs.horoscapp.databinding.FragmentLuckBinding
import com.jujodevs.horoscapp.ui.core.FragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LuckFragment() : FragmentBinding<FragmentLuckBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLuckBinding
        get() = FragmentLuckBinding::inflate

    override fun setup() {

    }


}
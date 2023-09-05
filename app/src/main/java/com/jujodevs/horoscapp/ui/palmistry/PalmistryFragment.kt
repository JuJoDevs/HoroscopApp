package com.jujodevs.horoscapp.ui.palmistry

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jujodevs.horoscapp.databinding.FragmentPalmistryBinding
import com.jujodevs.horoscapp.ui.core.FragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalmistryFragment : FragmentBinding<FragmentPalmistryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPalmistryBinding
        get() = FragmentPalmistryBinding::inflate

    override fun setup() {

    }

}
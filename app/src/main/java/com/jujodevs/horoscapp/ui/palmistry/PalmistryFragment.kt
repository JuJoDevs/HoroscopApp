package com.jujodevs.horoscapp.ui.palmistry

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jujodevs.horoscapp.databinding.FragmentPalmistryBinding
import com.jujodevs.horoscapp.ui.core.FragmentBinding


class PalmistryFragment : FragmentBinding<FragmentPalmistryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPalmistryBinding
        get() = FragmentPalmistryBinding::inflate

    override fun setup() {

    }

}
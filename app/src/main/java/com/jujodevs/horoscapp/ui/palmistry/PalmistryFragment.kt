package com.jujodevs.horoscapp.ui.palmistry

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.jujodevs.horoscapp.databinding.FragmentPalmistryBinding
import com.jujodevs.horoscapp.ui.core.FragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalmistryFragment : FragmentBinding<FragmentPalmistryBinding>() {

    companion object{
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPalmistryBinding
        get() = FragmentPalmistryBinding::inflate

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if (isGranted){

        }else{
            Toast.makeText(requireContext(), "Acepta los permisos para poder disfrutar de una experiencia mágica", Toast.LENGTH_LONG).show()
        }
    }

    override fun setup() {

        if (checkCameraPermission()){

        }else{
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

    }

    fun checkCameraPermission(): Boolean{
        return PermissionChecker.checkSelfPermission(requireContext(), CAMERA_PERMISSION) == PermissionChecker.PERMISSION_GRANTED
    }

}
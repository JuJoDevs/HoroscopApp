package com.jujodevs.horoscapp.ui.palmistry

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
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
            startCamera()
        }else{
            Toast.makeText(requireContext(), "Acepta los permisos para poder disfrutar de una experiencia mágica", Toast.LENGTH_LONG).show()
        }
    }

    override fun setup() {

        if (checkCameraPermission()){
            startCamera()
        }else{
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(
            {
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(binding.previewView.surfaceProvider)
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()

                    cameraProvider.bindToLifecycle(this, cameraSelector, preview)
                }catch (e: Exception){
                    Log.e("JuJo", "Algo ha ido mal ${e.message}")
                }
            }, ContextCompat.getMainExecutor(requireContext())
        )
    }

    fun checkCameraPermission(): Boolean{
        return PermissionChecker.checkSelfPermission(requireContext(), CAMERA_PERMISSION) == PermissionChecker.PERMISSION_GRANTED
    }

}
package com.jujodevs.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.jujodevs.horoscapp.R
import com.jujodevs.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.jujodevs.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel by viewModels<HoroscopeDetailViewModel>()

    private val args:HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeDetailViewModel.state.collect{
                    when(it){
                        HoroscopeDetailState.Loading -> { loadingState() }
                        is HoroscopeDetailState.Error -> { errorState() }
                        is HoroscopeDetailState.Success -> { successState(it) }
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image = when(state.horoscopeModel){
            HoroscopeModel.Aries -> R.drawable.aries
            HoroscopeModel.Taurus -> R.drawable.tauro
            HoroscopeModel.Gemini -> R.drawable.geminis
            HoroscopeModel.Cancer -> R.drawable.cancer
            HoroscopeModel.Leo -> R.drawable.leo
            HoroscopeModel.Virgo -> R.drawable.virgo
            HoroscopeModel.Libra -> R.drawable.libra
            HoroscopeModel.Scorpio -> R.drawable.escorpio
            HoroscopeModel.Sagittarius -> R.drawable.sagitario
            HoroscopeModel.Capricorn -> R.drawable.capricornio
            HoroscopeModel.Aquarius -> R.drawable.aquario
            HoroscopeModel.Pisces -> R.drawable.piscis
        }

        binding.ivDetail.setImageResource(image)
    }
}
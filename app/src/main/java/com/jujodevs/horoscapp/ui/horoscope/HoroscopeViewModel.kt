package com.jujodevs.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.jujodevs.horoscapp.data.providers.HoroscopeProvider
import com.jujodevs.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider): ViewModel() {

    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> get() = _horoscope

    init {
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }

}
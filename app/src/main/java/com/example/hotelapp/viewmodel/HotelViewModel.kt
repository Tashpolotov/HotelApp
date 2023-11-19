package com.example.hotelapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val repository: HotelRepository):ViewModel() {


    private val _hotel = MutableStateFlow(HotelState())
    val hotel :StateFlow<HotelState> = _hotel.asStateFlow()


    fun getBooked(){
        viewModelScope.launch {
            val booked = repository.getBooked()
            _hotel.value = _hotel.value.copy(booked = booked)
        }
    }

    fun getRoom(){
        viewModelScope.launch {
            val room = repository.getRoom()
            _hotel.value = _hotel.value.copy(room = room)

        }
    }

    fun getHome(){
        viewModelScope.launch {
            val home = repository.getHome()
            _hotel.value = _hotel.value.copy(home = home)
        }
    }
}
package com.example.domain.usecase

import com.example.domain.repository.HotelRepository

class HotelUseCase(private val repository: HotelRepository) {

    suspend operator fun invoke(){
        repository.getHome()
        repository.getRoom()
        repository.getBooked()
        repository.getInfoPerson()
    }
}
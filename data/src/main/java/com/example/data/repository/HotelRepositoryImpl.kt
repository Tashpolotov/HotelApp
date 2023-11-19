package com.example.data.repository

import android.util.Log
import com.example.data.network.ApiService
import com.example.domain.model.*
import com.example.domain.repository.HotelRepository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext
import retrofit2.Response

class HotelRepositoryImpl(private val apiService: ApiService) : HotelRepository {

    override suspend fun getHome(): List<HomeModel> = withContext(Dispatchers.IO) {
        val response = apiService.getHome()
        if (response.isSuccessful && response.body() != null)
            listOf(response.body()!!)
        else emptyList()

    }

    override suspend fun getRoom(): List<RoomsResponse> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getRooms()
            if (response.isSuccessful) {
                val roomModels = response.body()
                if (roomModels != null) {
                    roomModels.rooms
                } else {
                    emptyList()
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getBooked(): List<BookedModel> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getBooked()
            if (response.isSuccessful) {
                val booked = response.body()
                if (booked != null) {
                    listOf(booked)
                } else {
                    emptyList()
                }

            } else {

                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getInfoPerson(): List<InfoModel> = withContext(Dispatchers.IO) {
        try {
            // Возвращаем пустой список, поскольку мы не имеем конкретной реализации для getInfoPerson
            emptyList()
        } catch (e: Exception) {
            // Обработка ошибок, если это необходимо
            emptyList()
        }
    }
}
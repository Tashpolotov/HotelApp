package com.example.domain.repository

import com.example.domain.model.BookedModel
import com.example.domain.model.HomeModel
import com.example.domain.model.InfoModel
import com.example.domain.model.RoomsResponse

interface HotelRepository {


    suspend fun getHome():List<HomeModel>

    suspend fun getRoom(): List<RoomsResponse>

    suspend fun getBooked():List<BookedModel>

    suspend fun getInfoPerson():List<InfoModel>
}
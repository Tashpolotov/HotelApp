package com.example.data.network

import com.example.domain.model.BookedModel
import com.example.domain.model.HomeModel
import com.example.domain.model.RoomModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    ////https://run.mocky.io/v3/63866c74-d593-432c-af8e-f279d1a8d2ff

    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHome(): Response<HomeModel>

    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): Response<RoomModel>

    @GET("v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBooked():Response<BookedModel>

}
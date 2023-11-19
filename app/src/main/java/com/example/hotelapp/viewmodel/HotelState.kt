package com.example.hotelapp.viewmodel

import com.example.domain.model.BookedModel
import com.example.domain.model.HomeModel
import com.example.domain.model.RoomModel
import com.example.domain.model.RoomsResponse

data class HotelState(
    val home:List<HomeModel> = emptyList(),
    val room:List<RoomsResponse> = emptyList(),
    val booked:List<BookedModel> = emptyList(),
)
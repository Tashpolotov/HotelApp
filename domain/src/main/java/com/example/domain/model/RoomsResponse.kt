package com.example.domain.model

data class RoomsResponse (
        var id: Int,
        var name: String,
        var price: Int,
        var price_per: String,
        var peculiarities: List<String>,
        var image_urls: List<String>?
        )
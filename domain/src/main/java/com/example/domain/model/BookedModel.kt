package com.example.domain.model

data class BookedModel(

    var id:Int,
    var hotel_name:String,
    var hotel_adress:String,
    var horating:Int,
    var rating_name:String,
    var departure:String,
    var arrival_country:String,
    var tour_date_start:String,
    var tour_date_stop:String,
    var number_of_nights:Int,
    var room:String,
    var nutrition:String,
    var tour_price:Int,
    var fuel_charge :Int,
    var service_charge:Int,


/*{
    "id": 1,
    "hotel_name": "Лучший пятизвездочный отель в Хургаде, Египет",
    "hotel_adress": "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
    "horating": 5,
    "rating_name": "Превосходно",
    "departure": "Москва",
    "arrival_country": "Египет, Хургада",
    "tour_date_start": "19.09.2023",
    "tour_date_stop": "27.09.2023",
    "number_of_nights": 7,
    "room": "Люкс номер с видом на море",
    "nutrition": "Все включено",
    "tour_price": 289400,
    "fuel_charge": 9300,
    "service_charge": 2150
}*/
)
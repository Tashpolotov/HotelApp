package com.example.domain.model

data class HomeModel(
        var id: Int,
        var name: String,
        var address: String,
        var minimal_price: Int,
        var price_for_it: String,
        var rating: Int,
        var rating_name: String,
        var image_urls: List<String>,
        var about_the_hotel: AboutTheHotel
)

data class AboutTheHotel(
        var description: String,
        var peculiarities: List<String>
)

       //"id": 1,
        // "name": "Лучший пятизвездочный отель в Хургаде, Египет",
        // "adress": "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
        // "minimal_price": 134268,
        // "price_for_it": "За тур с перелётом",
        // "rating": 5,
        // "rating_name": "Превосходно",
        // "image_urls": ["https://www.atorus.ru/sites/default/files/upload/image/News/56149/Club_Priv%C3%A9_by_Belek_Club_House.jpg", "https://deluxe.voyage/useruploads/articles/The_Makadi_Spa_Hotel_02.jpg", "https://deluxe.voyage/useruploads/articles/article_1eb0a64d00.jpg"],
        // "about_the_hotel": {
        //    "description": "Отель VIP-класса с собственными гольф полями. Высокий уровнь сервиса. Рекомендуем для респектабельного отдыха. Отель принимает гостей от 18 лет!",
        //    "peculiarities": ["Бесплатный Wifi на всей территории отеля", "1 км до пляжа", "Бесплатный фитнес-клуб", "20 км до аэропорта"]
        //

package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.CategoryType
import com.example.mycity.model.CoffeeHouse
import com.example.mycity.model.Dummy
import com.example.mycity.model.Park
import com.example.mycity.model.Place
import com.example.mycity.model.Restaurant
import com.example.mycity.model.ShoppingCenter
import com.example.mycity.model.ViewPoint

object CategoriesDataProvider {
    val defaultPlace : Place = CoffeeHouse(
        name = R.string.coffee_standard,
        imageRes = R.drawable.ic_launcher_foreground,
        shortInfo = R.string.short_info_coffee_standard,
        address = "вулиця Вінстона Черчилля, 18, Київ, 02000",
        rating = 5.0F,
        isOpen = true,
        googleMapLink = "https://maps.app.goo.gl/nvXPBaxVucdmrhRM7"
    )

    private val categoryTypeList: List<CategoryType> = listOf(
        CategoryType.COFFEE_HOUSE,
        CategoryType.PARK,
        CategoryType.RESTAURANT,
        CategoryType.SHOPPING_CENTER,
        CategoryType.VIEW_POINT
    )

    private val recommendationByCategory: Map<CategoryType, List<Place>> = mapOf(
        CategoryType.COFFEE_HOUSE to listOf(
            CoffeeHouse(
                name = R.string.coffee_standard,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_coffee_standard,
                address = "вулиця Вінстона Черчилля, 18, Київ, 02000",
                rating = 5.0F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/nvXPBaxVucdmrhRM7"
            ),
            CoffeeHouse(
                name = R.string.coffee_12,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_coffee_standard,
                address = "dummy",
                rating = 4.7F,
                isOpen = true,
                googleMapLink = "dummy"
            )
        )
    )
    fun getCategoryTypeList(): List<CategoryType> {
        return categoryTypeList
    }

    fun getRecommendationListByCategory(categoryType: CategoryType) : List<Place> {
        return recommendationByCategory.get(categoryType)?: emptyList()
    }
    fun getPlaceByIndex(recommendationList: List<Place>, selectedPlace: Int): Place {
        return recommendationList.getOrElse(selectedPlace) { Dummy() }
    }
//    fun getCoffeeHouses(): List<CoffeeHouse> {
//        return listOf(
//            CoffeeHouse(
//                name = R.string.coffee_standart,
//                imageRes = R.drawable.ic_launcher_foreground,
//                address = "вулиця Вінстона Черчилля, 18, Київ, 02000",
//                rating = 5.0F,
//                isOpen = true,
//                googleMapLink = "https://maps.app.goo.gl/nvXPBaxVucdmrhRM7"
//            )
//        )
//    }
//    fun getRestaurants(): List<Restaurant> {
//        return listOf(
//            Restaurant(
//                name = R.string.restaurant_1,
//                imageRes = R.drawable.ic_launcher_foreground,
//                address = "dummy",
//                rating = 5.0F,
//                isOpen = true,
//                googleMapLink = "dummy"
//            )
//        )
//    }
//    fun getParks(): List<Park> {
//        return listOf(
//            Park(
//                name = R.string.park_1,
//                imageRes = R.drawable.ic_launcher_foreground,
//                address = "dummy",
//                rating = 5.0F,
//                isOpen = true,
//                googleMapLink = "dummy"
//            )
//        )
//    }
//    fun getShoppingCenters(): List<ShoppingCenter> {
//        return listOf(
//            ShoppingCenter(
//                name = R.string.ShoppingCenter_1,
//                imageRes = R.drawable.ic_launcher_foreground,
//                address = "dummy",
//                rating = 5.0F,
//                isOpen = true,
//                googleMapLink = "dummy"
//            )
//        )
//    }
//    fun getViewPoints(): List<ViewPoint> {
//        return listOf(
//            ViewPoint(
//                name = R.string.restaurant_1,
//                imageRes = R.drawable.ic_launcher_foreground,
//                address = "dummy",
//                rating = 5.0F,
//                isOpen = true,
//                googleMapLink = "dummy"
//            )
//        )
//    }
}
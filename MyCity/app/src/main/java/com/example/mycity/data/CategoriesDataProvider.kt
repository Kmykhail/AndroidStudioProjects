package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.CategoryType
import com.example.mycity.model.CoffeeHouse
import com.example.mycity.model.Dummy
import com.example.mycity.model.Park
import com.example.mycity.model.Place
import com.example.mycity.model.Restaurant

object CategoriesDataProvider {
    val defaultPlace : Place = CoffeeHouse(
        name = R.string.coffee_standard,
        imageRes = R.drawable.cs,
        shortInfo = R.string.short_info_cf,
        address = "вулиця Вінстона Черчилля, 18, Київ, 02000",
        rating = 5.0F,
        isOpen = true,
        googleMapLink = "https://maps.app.goo.gl/nvXPBaxVucdmrhRM7"
    )

    private val categoryTypeList: List<CategoryType> = listOf(
        CategoryType.COFFEE_HOUSE,
        CategoryType.PARK,
        CategoryType.RESTAURANT
    )

    private val recommendationByCategory: Map<CategoryType, List<Place>> = mapOf(
        CategoryType.COFFEE_HOUSE to listOf(
            CoffeeHouse(
                name = R.string.coffee_standard,
                imageRes = R.drawable.cs,
                shortInfo = R.string.short_info_cf,
                address = "Winston Churchill St, 18, Kyiv, 02000",
                rating = 5.0F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/nvXPBaxVucdmrhRM7"
            ),
            CoffeeHouse(
                name = R.string.coffee_12,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_12c,
                address = "Yevhena Chykalenka St, 12, Kyiv, 01034",
                rating = 4.6F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/yNw8FRr1uwA6GDsx7"
            ),
            CoffeeHouse(
                name = R.string.coffee_idealist,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_ci,
                address = "Yaroslaviv Val St, 15, Kyiv, 01030",
                rating = 4.1F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/hdSyxmpMJPwQnqTu5"
            ),
            CoffeeHouse(
                name = R.string.coffee_takava,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_ct,
                address = "Velyka Vasylkivska St, 43/16, Kyiv, 02000",
                rating = 4.5F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/na7Cb51ZaKFm9apQ8"
            )
        ),
        CategoryType.RESTAURANT to listOf(
            Restaurant(
                name = R.string.restaurant_1,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_r1,
                address = "Saksahanskoho St, 57А, Kyiv, 01033",
                rating = 4.7F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/tXPC6aMYiER5dJsr8"
            ),
            Restaurant(
                name = R.string.restaurant_2,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_r2,
                address = "Volodymyrska St, 4, Kyiv, 01001",
                rating = 4.6F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/VtDLkazCgRV2iDZb7"
            ),
            Restaurant(
                name = R.string.restaurant_3,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_r3,
                address = "Beresteiskyi Ave, 40, Kyiv, 03065",
                rating = 4.4F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/J9od2XUeM7WgZ7GR8"
            )
        ),
        CategoryType.PARK to listOf(
            Park(
                name = R.string.park_1,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_p1,
                address = "Parkova Rd, 1, Kyiv, 02000",
                rating = 4.7F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/ccMFwkNVcgKagqL59"
            ),
            Park(
                name = R.string.park_2,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_p2,
                address = "Obolonska naberezhna, 9, Kyiv, 02000",
                rating = 4.9F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/SCgazoxBjAnhGrSc8"
            ),
            Park(
                name = R.string.park_3,
                imageRes = R.drawable.ic_launcher_foreground,
                shortInfo = R.string.short_info_p3,
                address = "Horikhuvatskyi shliakh St, 6а, Kyiv, 02000",
                rating = 4.6F,
                isOpen = true,
                googleMapLink = "https://maps.app.goo.gl/AcCV6gAzAP3RuAYWA"
            ),

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
}
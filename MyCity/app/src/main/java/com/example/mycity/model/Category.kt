package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.mycity.R

enum class CategoryType(@StringRes val title: Int) {
    COFFEE_HOUSE(title = R.string.coffee_house),
    RESTAURANT(title = R.string.restaurant),
    PARK(title = R.string.park),
    SHOPPING_CENTER(title = R.string.shopping_center),
    VIEW_POINT(title = R.string.view_point)
}

interface Place {
    @get:StringRes val name: Int
    @get:DrawableRes val imageRes: Int
    @get:StringRes val shortInfo: Int
    val address: String
    val rating: Float
    val isOpen: Boolean
    val googleMapLink: String?
}

data class CoffeeHouse(
    override val name: Int,
    override val imageRes: Int,
    override val shortInfo: Int,
    override val address: String,
    override val rating: Float,
    override val isOpen: Boolean,
    override val googleMapLink: String?
) : Place

data class Restaurant(
    override val name: Int,
    override val imageRes: Int,
    override val shortInfo: Int,
    override val address: String,
    override val rating: Float,
    override val isOpen: Boolean,
    override val googleMapLink: String?
) : Place

data class Park(
    override val name: Int,
    override val imageRes: Int,
    override val shortInfo: Int,
    override val address: String,
    override val rating: Float,
    override val isOpen: Boolean,
    override val googleMapLink: String?
) : Place

data class ShoppingCenter(
    override val name: Int,
    override val imageRes: Int,
    override val shortInfo: Int,
    override val address: String,
    override val rating: Float,
    override val isOpen: Boolean,
    override val googleMapLink: String?
) : Place

data class ViewPoint(
    override val name: Int,
    override val imageRes: Int,
    override val shortInfo: Int,
    override val address: String,
    override val rating: Float,
    override val isOpen: Boolean,
    override val googleMapLink: String?
) : Place

data class Dummy(
    override val name: Int = R.string.DummyName,
    override val imageRes: Int = 69,
    override val shortInfo: Int = R.string.DummyName,
    override val address: String = "Dummy",
    override val rating: Float = -1.0F,
    override val isOpen: Boolean = false,
    override val googleMapLink: String? = "Dummy"
) : Place
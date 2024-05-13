package com.example.cources.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlin.reflect.typeOf

data class Topic(
    @StringRes val stringRes: Int,
    val coursesByTopic: Int,
    @DrawableRes val drawableRes: Int
)
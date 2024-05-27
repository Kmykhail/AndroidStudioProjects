package com.example.a30daysofrecipes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a30daysofrecipes.R

data class Recipe(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    @StringRes val description: Int
)

object RecipesRepository {
    val recipes = listOf(
        Recipe(
            R.drawable.borscht,
            R.string.borshch,
            R.string.borshch_description
        ),
        Recipe(
            R.drawable.varenyky,
            R.string.varenyky,
            R.string.varenyky_description
        ),
        Recipe(
            R.drawable.chicken_kyiv_with_vegetables,
            R.string.chicken_kyiv,
            R.string.chicken_kyiv_description
        ),
    )
}
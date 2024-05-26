package com.example.superheroesapp.model

import androidx.compose.ui.res.stringResource
import com.example.superheroesapp.R
import com.example.superheroesapp.model.Hero

object HeroesRepository {
    val heroes = listOf(
        Hero(
            name = R.string.andr_sup_1,
            description = R.string.andr_sup_1_description,
            image = R.drawable.android_superhero1
        ),
        Hero(
            name = R.string.andr_sup_2,
            description = R.string.andr_sup_2_description,
            image = R.drawable.android_superhero2
        ),
        Hero(
            name = R.string.andr_sup_3,
            description = R.string.andr_sup_3_description,
            image = R.drawable.android_superhero3
        ),
        Hero(
            name = R.string.andr_sup_4,
            description = R.string.andr_sup_4_description,
            image = R.drawable.android_superhero4
        ),
        Hero(
            name = R.string.andr_sup_5,
            description = R.string.andr_sup_5_description,
            image = R.drawable.android_superhero5
        ),
        Hero(
            name = R.string.andr_sup_6,
            description = R.string.andr_sup_6_description,
            image = R.drawable.android_superhero6
        ),
    )
}
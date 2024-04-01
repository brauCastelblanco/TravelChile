package com.example.a30daysinchile.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a30daysinchile.R

data class Day(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int
)

val days = listOf(
    Day(R.drawable.san_cristobal, R.string.place_name_1, R.string.place_description_1),
    Day(R.drawable.farellones, R.string.place_name_2, R.string.place_description_2),
    Day(R.drawable.la_moneda, R.string.place_name_3, R.string.place_description_3),
    Day(R.drawable.poyo, R.string.place_name_4, R.string.place_description_4),
    Day(R.drawable.rapa_nui, R.string.place_name_5, R.string.place_description_5),
    Day(R.drawable.valdivia, R.string.place_name_6, R.string.place_description_6),
    Day(R.drawable.antofagasta, R.string.place_name_7, R.string.place_description_7),
    Day(R.drawable.atacama, R.string.place_name_8, R.string.place_description_8),
    Day(R.drawable.valparaiso, R.string.place_name_9, R.string.place_description_9),
    Day(R.drawable.magallanes, R.string.place_name_10, R.string.place_description_10),
)


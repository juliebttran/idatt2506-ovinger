package com.example.oving7

import com.example.oving7.R

data class ColorChoice(val value: String, val label: Int)
val COLOR_CHOICES: List<ColorChoice> = listOf(
    ColorChoice("ingen", R.string.color_none),
    ColorChoice("bla", R.string.color_blue),
    ColorChoice("gronn", R.string.color_green),
    ColorChoice("rod", R.string.color_red)
)

package com.example.oving2.ui

data class Recipe(
    val name: String,
    val course: String,
    val time: String,
)

val sampleRecipes: List<Recipe> = listOf(
    Recipe("Fiskesuppe","Hovedrett", "35 min"),
    Recipe("Lapskaus","Hovedrett", "90 min"),
    Recipe("Vafler","Dessert", "20 min"),
    Recipe("Tomatsuppe","Forrett", "25 min"),
    Recipe("Eplekake","Dessert", "55 min"),
    Recipe("Fiskekaker","Hovedrett", "40 min"),
    )
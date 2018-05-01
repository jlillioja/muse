package io.grandlabs.muse

import java.util.*

interface MealCreator {
    fun logMeal(mealSize: MealSize): Meal
    fun logMeal(mealSize: Int): Meal
}

interface MealLogProvider {
    val meals: List<Meal>
}

class MealManager: MealCreator, MealLogProvider {

    override val meals: MutableList<Meal> = mutableListOf()

    override fun logMeal(mealSize: MealSize): Meal = Meal(mealSize).also { meals.add(it) }

    override fun logMeal(mealSize: Int): Meal = Meal(mealSize).also { meals.add(it) }

}

data class Meal(val mealSize: MealSize, val mealSizePercentage: Int? = null, val date: Date = Date()) {
    constructor(mealSizePercentage: Int) : this(MealSize.fromPercentage(mealSizePercentage))
}

enum class MealSize {
    SMALL, MEDIUM, LARGE;

    companion object {
        fun fromPercentage(mealSizePercentage: Int) = when {
            mealSizePercentage < 33 -> SMALL
            mealSizePercentage in 33..66 -> MEDIUM
            else -> LARGE
        }
    }
}
package com.zybooks.tipcalculator

import kotlin.math.ceil
import kotlin.random.Random

const val SLICES_PER_PIZZA = 8

val randomNum = (10..20).random() //This is for the random tip percent.

class TipCalculator(partySize: Double, var hungerLevel: HungerLevel) {
    var partySize = 0.0

        set(value) {
            field = if (value >= 0.0) value else 0.0
        }

    enum class HungerLevel(var numSlices: Int) {
        LIGHT(10), MEDIUM(15), RAVENOUS(20), RANDOM(randomNum)
    }

    val totalPizzas: Double
        get() {
            return (partySize + (partySize * (hungerLevel.numSlices/100.0))).toDouble()
        }

    init {
        this.partySize = partySize
    }
}
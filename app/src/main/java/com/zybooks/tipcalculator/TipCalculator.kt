package com.zybooks.tipcalculator

import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

class TipCalculator(partySize: Double, var hungerLevel: HungerLevel) {
    var partySize = 0.0
        set(value) {
            field = if (value >= 0.0) value else 0.0
        }

    enum class HungerLevel(var numSlices: Double) {
        LIGHT(2.0), MEDIUM(3.0), RAVENOUS(4.0)
    }

    val totalPizzas: Double
        get() {
            return (partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble())
        }

    init {
        this.partySize = partySize
    }
}
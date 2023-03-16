package com.zybooks.tipcalculator

import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

class TipCalculator(partySize: Double, var hungerLevel: HungerLevel) {
    var partySize = 0.0
        set(value) {
            field = if (value >= 0.0) value else 0.0
        }

    enum class HungerLevel(var numSlices: Double) {
        LIGHT(0.10), MEDIUM(0.15), RAVENOUS(0.20)
    }

    val totalPizzas: Double
        get() {
            return (partySize + (partySize * hungerLevel.numSlices)).toDouble()
        }

    init {
        this.partySize = partySize
    }
}
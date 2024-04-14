package com.example.unit2checks

fun main() {
    val child = 5
    val adult = 13
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    when {
        age < 13 -> return 15
        age in 13 .. 60 -> return if(isMonday) 25 else 30
        age > 60 -> return 20
        else -> return -1
    }
}
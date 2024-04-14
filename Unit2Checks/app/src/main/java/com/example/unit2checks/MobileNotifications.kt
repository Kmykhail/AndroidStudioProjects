package com.example.unit2checks

fun main() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}

fun printNotificationSummary(numberOfMessages: Int) {
    println(if (numberOfMessages >= 100) "Your phone is blowing up! You have 99+ notifications."
            else "You have $numberOfMessages notifications.")
}
package com.example.consoleoutput

enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING
}

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

// create an extended property "durationOfEvent"
val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
                "short"
            } else {
                "long"
            }

fun main() {
    // Single event
    val myEvent = Event("Study Kotlin",
        "Commit to studying Kotlin at least 15 minutes per day.",
        Daypart.EVENING,
        15)
    println(myEvent)

    // list of event
    var myEvents = mutableListOf(
        Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0),
        Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15),
        Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30),
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
        Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10),
        Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45))
    myEvents.add(Event(title = "New added event", daypart = Daypart.EVENING, durationInMinutes = 120))
    println("Events number is ${myEvents.size}")
    myEvents.forEach{println("Event - $it")}

    // filtered by duration, "Short events"
    val shortEvents = myEvents.filter { it.durationInMinutes < 60 }
    println("You have ${shortEvents.size} short events.")
    shortEvents.forEach { println("$it") }

    // groupedBy daypart
    val groupedEvents = myEvents.groupBy { it.daypart }
    // #1
    for (item in groupedEvents) {
        println("${item.key}: ${item.value.size} events")
    }
    // #2
    groupedEvents.forEach { daypart, events ->  println("$daypart: ${events.size} events") }

    // last one
    println("Last event of the day: ${myEvents.last().title}")
    println("Duration of first event of the day: ${myEvents[0].durationOfEvent}")
}
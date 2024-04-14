package com.example.unit2checks

fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()

    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    val hobbyString = if (hobby != null) "Likes to $hobby. " else ""
    val referrerString =
        if (referrer != null) {
            "Has a referrer named ${referrer.name}" + if (referrer.hobby != null) ", who likes to ${referrer.hobby}" else "\n"
        } else {
            "Doesn't have a referrer.\n"
        }
    fun showProfile() {
        println("Name: $name\nAge: $age\n$hobbyString$referrerString")
    }
}
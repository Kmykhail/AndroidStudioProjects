package com.example.unit2checks

// lambdas experience
//fun main() {
//    val trickFunction = trick
//    trickFunction()
//    treat()
//}

val trick = {
    println("No treats!")
}

val treat: () -> Unit = {
    println("Have a treat!")
}

// function that returns a function
fun main() {
    val coins: (Int) -> String = {
        "$it quarters"
    }

    val cupcake: (Int) ->String = {
        "Have a cupcake!"
    }

    val treatFunction = trickOrTreat(false, coins)
    /*
    * Trailing lambda syntax
    * val treatFunction = trickOrTreat(false) { "$it quater" }
    * */
    val trickFunction = trickOrTreat(true, null)

    treatFunction()
    trickFunction()

    repeat(4) {
        treatFunction()
    }
}

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    if (isTrick) {
        return trick
    }

    if (extraTreat != null) {
        println(extraTreat(5))
    }

    return treat
}

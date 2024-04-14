package com.example.unit2checks

class SongCatalog(private val title: String,
                  private val artist: String,
                  private val yearPublished: Int,
                  private val playCount: Int) {
    val songPopularity = playCount > 1000
    fun songDescription() {
        println("$title, performed by $artist, was released in $yearPublished")
    }
}

fun main() {
    val songCatalog = SongCatalog("Dirty Work", "Donald Fagen", 1972, 1001)
    songCatalog.songDescription()
    println(songCatalog.songPopularity)
}
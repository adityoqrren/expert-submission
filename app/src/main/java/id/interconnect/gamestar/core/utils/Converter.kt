package id.interconnect.gamestar.core.utils

import id.interconnect.gamestar.core.data.source.remote.response.Developer
import id.interconnect.gamestar.core.data.source.remote.response.EsrbRating
import id.interconnect.gamestar.core.data.source.remote.response.Genre
import id.interconnect.gamestar.core.data.source.remote.response.PlatformOuter

object Converter {
    //converter
    fun platformToString(input: List<PlatformOuter>): String {
        var platform_string = "-"
        if (input.isNotEmpty()) {
            val platformList = ArrayList<String>()
            for (x in input) {
                platformList.add(x.platform.name)
            }
            platform_string = platformList.joinToString(
                separator = ", "
            )

        }
        return platform_string
    }

    fun developerToString(input: List<Developer>): String {
        var developer_string = "-"
        if (input.isNotEmpty()) {
            val developerList = ArrayList<String>()
            for (x in input) {
                developerList.add(x.name)
            }
            developer_string = developerList.joinToString(
                separator = ", "
            )

        }
        return developer_string
    }

    fun genreToString(input: List<Genre>): String {
        var genre_string = "-"
        if (input.isNotEmpty()) {
            val genreList = ArrayList<String>()
            for (x in input) {
                genreList.add(x.name)
            }
            genre_string = genreList.joinToString(
                separator = ", "
            )

        }
        return genre_string
    }

    fun esrbRatingToString(input: EsrbRating): String {
        return input.name
    }

    fun stringToList(mystring: String):List<String>{
        val words = mystring.split(", ")
        return words
    }

}
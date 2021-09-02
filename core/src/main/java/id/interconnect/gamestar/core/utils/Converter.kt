package id.interconnect.gamestar.core.utils

import id.interconnect.gamestar.core.data.source.remote.response.Developer
import id.interconnect.gamestar.core.data.source.remote.response.EsrbRating
import id.interconnect.gamestar.core.data.source.remote.response.Genre
import id.interconnect.gamestar.core.data.source.remote.response.PlatformOuter

object Converter {
    //converter
    fun platformToString(input: List<PlatformOuter>): String {
        var platformString = "-"
        if (input.isNotEmpty()) {
            val platformList = ArrayList<String>()
            for (x in input) {
                platformList.add(x.platform.name)
            }
            platformString = platformList.joinToString(
                separator = ", "
            )

        }
        return platformString
    }

    fun developerToString(input: List<Developer>): String {
        var developerString = "-"
        if (input.isNotEmpty()) {
            val developerList = ArrayList<String>()
            for (x in input) {
                developerList.add(x.name)
            }
            developerString = developerList.joinToString(
                separator = ", "
            )

        }
        return developerString
    }

    fun genreToString(input: List<Genre>): String {
        var genreString = "-"
        if (input.isNotEmpty()) {
            val genreList = ArrayList<String>()
            for (x in input) {
                genreList.add(x.name)
            }
            genreString = genreList.joinToString(
                separator = ", "
            )

        }
        return genreString
    }

    fun esrbRatingToString(input: EsrbRating?): String {
        if (input != null) {
            return input.name
        }
        return "-"
    }

    fun dateTimeSeparator(mystring: String): String {
        val arrSeperated = mystring.split("T")
        return arrSeperated[0] + "\n" + arrSeperated[1]
    }

}
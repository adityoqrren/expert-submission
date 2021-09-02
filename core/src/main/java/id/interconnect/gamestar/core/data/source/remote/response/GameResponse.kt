package id.interconnect.gamestar.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name_original")
    val name_original: String,
    @field:SerializedName("background_image")
    val background_image: String,
    @field:SerializedName("released")
    val released: String,
    @field:SerializedName("tba")
    val tba: Boolean,
    @field:SerializedName("updated")
    val updated: String,
    @field:SerializedName("rating")
    val rating: Double,
    @field:SerializedName("playtime")
    val playtime: Int,
    @field:SerializedName("platforms")
    val platforms: List<PlatformOuter>,
    @field:SerializedName("developers")
    val developers: List<Developer>,
    @field:SerializedName("genres")
    val genres: List<Genre>,
    @field:SerializedName("esrb_rating")
    val esrb_rating: EsrbRating,
    @field:SerializedName("description_raw")
    val description_raw: String
)
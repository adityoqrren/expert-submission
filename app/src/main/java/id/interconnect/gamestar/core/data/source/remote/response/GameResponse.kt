package id.interconnect.gamestar.core.data.source.remote.response

data class GameResponse(
    val id: Int,
    val name_original: String,
    val background_image: String,
    val released: String,
    val tba: Boolean,
    val updated: String,
    val rating: Double,
    val playtime: Int,
    val platforms: List<PlatformOuter>,
    val developers: List<Developer>,
    val genres: List<Genre>,
    val esrb_rating: EsrbRating,
    val description_raw: String
)
package id.interconnect.gamestar.core.data.source.remote.response

data class GameItemResponse (
    val id: Int,
    val name: String,
    val background_image: String,
    val rating: Double,
    val parent_platforms: List<PlatformOuter>
)
package id.interconnect.gamestar.core.data.source.remote.response

data class GameListResponse (
    val count: Int,
    val results: List<GameItemResponse>
)

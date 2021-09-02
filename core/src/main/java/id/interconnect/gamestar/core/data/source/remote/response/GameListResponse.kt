package id.interconnect.gamestar.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameListResponse (
    @field:SerializedName("results")
    val results: List<GameItemResponse>
)

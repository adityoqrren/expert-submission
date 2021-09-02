package id.interconnect.gamestar.core.data.source.remote.network

import id.interconnect.gamestar.core.data.source.remote.response.GameListResponse
import id.interconnect.gamestar.core.data.source.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getListGames(
        @Query("key") key: String
    ): GameListResponse

    @GET("games/{id}")
    suspend fun getDetailGame(
        @Path("id") id: Int,
        @Query("key") key: String
    ): GameResponse

}
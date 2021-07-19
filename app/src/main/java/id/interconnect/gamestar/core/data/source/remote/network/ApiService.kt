package id.interconnect.gamestar.core.data.source.remote.network

import id.interconnect.gamestar.core.data.source.remote.response.GameListResponse
import id.interconnect.gamestar.core.data.source.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //getList https://api.rawg.io/api/games?key=e0b7fb22e279433bafd4021b2c0e057b
    @GET("games")
    suspend fun getListGames(
        @Query("key") key: String
    ): GameListResponse
    //getDetail https://api.rawg.io/api/games/3498?key=e0b7fb22e279433bafd4021b2c0e057b
    @GET("games/{id}")
    suspend fun getDetailGame(
        @Path("id") id: Int,
        @Query("key") key: String
    ): GameResponse

}
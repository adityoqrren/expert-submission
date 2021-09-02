package id.interconnect.gamestar.core.data.source.local.room

import androidx.room.*
import id.interconnect.gamestar.core.data.source.local.entity.GameItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    //insertGame
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameItemEntity>)

    //getAllGames
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameItemEntity>>

    //getGame
    @Query("SELECT * FROM games WHERE id = :id")
    fun getDetailGame(id: Int): Flow<GameItemEntity>

    //updateGame
    @Update
    fun updateGame(game: GameItemEntity)

    //getFavorites
    @Query("SELECT * FROM games WHERE favorited=1")
    fun getFavoriteGames(): Flow<List<GameItemEntity>>

}
package id.interconnect.gamestar.core.data.source.local

import id.interconnect.gamestar.core.data.source.local.entity.GameItemEntity
import id.interconnect.gamestar.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val gameDao: GameDao) {
    //insertGame
    suspend fun insertGames(gameList: List<GameItemEntity>) = gameDao.insertGames(gameList)

    //getAllGames
    fun getAllGames(): Flow<List<GameItemEntity>> = gameDao.getAllGames()

    //getDetailGame
    fun getDetailGame(id: Int): Flow<GameItemEntity> = gameDao.getDetailGame(id)

    //updateGame
    fun updateGame(game: GameItemEntity) = gameDao.updateGame(game)

    //getFavoriteGames
    fun getFavoriteGames(): Flow<List<GameItemEntity>> = gameDao.getFavoriteGames()

    //setFavoriteGame
    fun setFavoriteGame(game: GameItemEntity, newState: Boolean){
        game.favorited = newState
        gameDao.updateGame(game)
    }
}
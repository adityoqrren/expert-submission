package id.interconnect.gamestar.core.domain.usecase

import id.interconnect.gamestar.core.data.Resource
import id.interconnect.gamestar.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    //getAllGame
    fun getAllGames(): Flow<Resource<List<Game>>>
    //getDetailGame
    fun getDetailGame(id: Int): Flow<Resource<Game>>
    //getFavoriteGames
    fun getFavoriteGames(): Flow<List<Game>>
    //setFavoriteGame
    fun setFavoriteGame(game: Game, state:Boolean)
}
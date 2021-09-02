package id.interconnect.gamestar.core.domain.usecase

import id.interconnect.gamestar.core.data.Resource
import id.interconnect.gamestar.core.domain.model.Game
import id.interconnect.gamestar.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {
    override fun getAllGames(): Flow<Resource<List<Game>>> = gameRepository.getAllGames()

    override fun getDetailGame(id: Int): Flow<Resource<Game>> = gameRepository.getDetailGame(id)

    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGames()

    override fun setFavoriteGame(game: Game, state: Boolean) =
        gameRepository.setFavoriteGame(game, state)
}
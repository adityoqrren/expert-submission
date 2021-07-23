package id.interconnect.gamestar.core.data

import id.interconnect.gamestar.core.data.source.local.LocalDataSource
import id.interconnect.gamestar.core.data.source.remote.RemoteDataSource
import id.interconnect.gamestar.core.data.source.remote.network.ApiResponse
import id.interconnect.gamestar.core.data.source.remote.response.GameItemResponse
import id.interconnect.gamestar.core.data.source.remote.response.GameListResponse
import id.interconnect.gamestar.core.data.source.remote.response.GameResponse
import id.interconnect.gamestar.core.domain.model.Game
import id.interconnect.gamestar.core.domain.repository.IGameRepository
import id.interconnect.gamestar.core.utils.AppExecutors
import id.interconnect.gamestar.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getAllGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameItemResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameItemResponse>>> =
                remoteDataSource.getListGames()

            override suspend fun saveCallResult(data: List<GameItemResponse>) {
                val dataEntities = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(dataEntities)
            }

        }.asFlow()

    override fun getDetailGame(id: Int): Flow<Resource<Game>> {
        return object : NetworkBoundResource<Game, GameResponse>(){
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getDetailGame(id).map { DataMapper.mapEntityToDomainDetail(it) }
            }

            override fun shouldFetch(data: Game?): Boolean =
                data?.developers.isNullOrEmpty() || data?.released.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: GameResponse) {
                val gameEntity = DataMapper.mapResponseToEntityDetail(data)
                appExecutors.diskIO().execute { localDataSource.updateGame(gameEntity) }
            }

        }.asFlow()
    }

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}
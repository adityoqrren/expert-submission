package id.interconnect.gamestar.core.utils

import id.interconnect.gamestar.core.data.source.local.entity.GameItemEntity
import id.interconnect.gamestar.core.data.source.remote.response.GameItemResponse
import id.interconnect.gamestar.core.data.source.remote.response.GameResponse
import id.interconnect.gamestar.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameItemResponse>): List<GameItemEntity> {
        val gameItemEntityList = ArrayList<GameItemEntity>()
        input.map {
            val gameItemEntity = GameItemEntity(
                id = it.id,
                name_original = it.name,
                background_image = it.background_image,
                rating = it.rating,
                parent_platforms = Converter.platformToString(it.parent_platforms)
            )
            gameItemEntityList.add(gameItemEntity)
        }
        return gameItemEntityList
    }

    fun mapResponseToEntityDetail(input: GameResponse): GameItemEntity =
        GameItemEntity(
            id = input.id,
            name_original = input.name_original,
            background_image = input.background_image,
            rating = input.rating,
            parent_platforms = Converter.platformToString(input.platforms),
            released = input.released,
            tba = input.tba,
            updated = Converter.dateTimeSeparator(input.updated),
            playtime = input.playtime,
            developers = Converter.developerToString(input.developers),
            genres = Converter.genreToString(input.genres),
            esrb_rating = Converter.esrbRatingToString(input.esrb_rating),
            description_raw = input.description_raw
        )


    fun mapEntitiesToDomain(input: List<GameItemEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name_original = it.name_original,
                background_image = it.background_image,
                released = it.released,
                tba = it.tba,
                updated = it.updated,
                rating = it.rating,
                playtime = it.playtime,
                parent_platforms = it.parent_platforms,
                developers = it.developers,
                genres = it.genres,
                esrb_rating = it.esrb_rating,
                description_raw = it.description_raw,
                favorited = it.favorited
            )
        }

    fun mapEntityToDomainDetail(input: GameItemEntity): Game =
        Game(
            id = input.id,
            name_original = input.name_original,
            background_image = input.background_image,
            released = input.released,
            tba = input.tba,
            updated = input.updated,
            rating = input.rating,
            playtime = input.playtime,
            parent_platforms = input.parent_platforms,
            developers = input.developers,
            genres = input.genres,
            esrb_rating = input.esrb_rating,
            description_raw = input.description_raw,
            favorited = input.favorited
        )

    fun mapDomainToEntity(input: Game): GameItemEntity =
        GameItemEntity(
            id = input.id,
            name_original = input.name_original,
            background_image = input.background_image,
            released = input.released,
            tba = input.tba,
            updated = input.updated,
            rating = input.rating,
            playtime = input.playtime,
            parent_platforms = input.parent_platforms,
            developers = input.developers,
            genres = input.genres,
            esrb_rating = input.esrb_rating,
            description_raw = input.description_raw,
            favorited = input.favorited
        )

}
package id.interconnect.gamestar.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="games")
data class GameItemEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name_original")
    var name_original: String,

    @ColumnInfo(name = "background_image")
    var background_image: String,

    @ColumnInfo(name = "released")
    var released: String = "",

    @ColumnInfo(name = "tba")
    var tba: Boolean = false,

    @ColumnInfo(name = "updated")
    var updated: String = "",

    @ColumnInfo(name = "rating")
    var rating: Double = 0.0,

    @ColumnInfo(name = "playtime")
    var playtime: Int = 0,

    @ColumnInfo(name = "platforms")
    var platforms: String,

    @ColumnInfo(name = "developers")
    var developers: String = "",

    @ColumnInfo(name = "genres")
    var genres: String = "",

    @ColumnInfo(name = "esrb_rating")
    var esrb_rating: String = "",

    @ColumnInfo(name = "description_raw")
    var description_raw: String = "",

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)
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
    var released: String = "", //sudah

    @ColumnInfo(name = "tba")
    var tba: Boolean = false, //sudah

    @ColumnInfo(name = "updated")
    var updated: String = "", //sudah

    @ColumnInfo(name = "rating")
    var rating: Double = 0.0, //sudah

    @ColumnInfo(name = "playtime")
    var playtime: Int = 0, //sudah

    @ColumnInfo(name = "platforms")
    var parent_platforms: String, //sudah

    @ColumnInfo(name = "developers")
    var developers: String = "", //sudah

    @ColumnInfo(name = "genres")
    var genres: String = "", //sudah

    @ColumnInfo(name = "esrb_rating")
    var esrb_rating: String = "", //sudah

    @ColumnInfo(name = "description_raw")
    var description_raw: String = "", //sudah

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)
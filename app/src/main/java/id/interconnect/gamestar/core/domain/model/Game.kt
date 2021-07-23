package id.interconnect.gamestar.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name_original: String,
    val background_image: String,
    val released: String,
    val tba: Boolean,
    val updated: String,
    val rating: Double,
    val playtime: Int,
    val parent_platforms: String,
    val developers: String,
    val genres: String,
    val esrb_rating: String,
    val description_raw: String,
    val favorited: Boolean = false
):Parcelable

package id.interconnect.gamestar.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlatformOuter (
    @field:SerializedName("platform")
    val platform : Platform
)
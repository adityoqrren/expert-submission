package id.interconnect.gamestar.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Developer (
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String
)
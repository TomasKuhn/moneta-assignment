package cz.kuhnt.moneta.assignment.library.networking.data

import com.google.gson.annotations.SerializedName

data class PlayersDto(
    @SerializedName("data")
    val players: List<PlayerDto>,
    @SerializedName("meta")
    val meta: PagingMetaDto
)

data class PlayerDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
)

data class PagingMetaDto(
    @SerializedName("next_cursor")
    val nextCursor: Int,
    @SerializedName("per_page")
    val perPage: Int,
)


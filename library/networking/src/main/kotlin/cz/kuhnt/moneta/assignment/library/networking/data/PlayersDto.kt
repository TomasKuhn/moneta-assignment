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
    val id: Long,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("weight")
    val weight : String,
    @SerializedName("college")
    val college: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("team")
    val team: TeamDto,
)

data class PagingMetaDto(
    @SerializedName("next_cursor")
    val nextCursor: Int,
    @SerializedName("per_page")
    val perPage: Int,
)


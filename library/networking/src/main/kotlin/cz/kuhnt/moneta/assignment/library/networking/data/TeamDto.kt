package cz.kuhnt.moneta.assignment.library.networking.data

import com.google.gson.annotations.SerializedName

data class TeamDto(
    @SerializedName("name")
    val name: String,
)

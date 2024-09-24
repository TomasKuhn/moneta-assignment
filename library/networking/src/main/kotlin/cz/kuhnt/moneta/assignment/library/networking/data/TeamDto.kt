package cz.kuhnt.moneta.assignment.library.networking.data

import com.google.gson.annotations.SerializedName

data class TeamDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("conference")
    val conference: String,
    @SerializedName("division")
    val division: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("abbreviation")
    val abbreviation: String
)

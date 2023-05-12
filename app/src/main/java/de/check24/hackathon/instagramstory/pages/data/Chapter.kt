package de.check24.hackathon.instagramstory.pages.data

import com.google.gson.annotations.SerializedName

data class Chapter(
    @SerializedName("id")
    val id: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("length")
    val length: Int,

    @SerializedName("posted")
    val posted: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("startAt")
    val startAt: Int?,

    @SerializedName("endAt")
    val endAt: Int?,

    @SerializedName("type")
    val type: String
)
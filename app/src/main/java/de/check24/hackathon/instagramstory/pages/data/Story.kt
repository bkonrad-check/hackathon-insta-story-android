package de.check24.hackathon.instagramstory.pages.data

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("preview")
    val preview: String,

    @SerializedName("chapters")
    val chapters: List<Chapter>,
)

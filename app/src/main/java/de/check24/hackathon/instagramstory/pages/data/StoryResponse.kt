package de.check24.hackathon.instagramstory.pages.data

import com.google.gson.annotations.SerializedName

data class StoryResponse (
    @SerializedName("stories")
    val stories : List<Story>
)
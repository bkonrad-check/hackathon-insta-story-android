package de.check24.hackathon.instagramstory.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import de.check24.hackathon.instagramstory.mod.Story

class StoryParamType : NavType<Story>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Story? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Story {
        return Gson().fromJson(value, Story::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Story) {
        bundle.putParcelable(key, value)
    }
}
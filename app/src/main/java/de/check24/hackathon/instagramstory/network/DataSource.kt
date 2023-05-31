package de.check24.hackathon.instagramstory.network

import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.mod.Storries
import de.check24.hackathon.instagramstory.mod.Story
import retrofit2.Response

class DataSource(
    private val apiService: ApiService
) {
    suspend fun stories(): Storries {
        val response: Response<Storries> = apiService.stories()
        if (response.isSuccessful) {
            return Storries(
                stories = (response.body()?.stories?.toMutableList() ?: mutableListOf()).also {
                    it.add(
                        0, Story(
                            chapters = listOf(
                                ChapterApi(
                                    listOf(),
                                    null,
                                    13377,
                                    44000,
                                    1,
                                    null,
                                    "",
                                    "VIDEO",
                                    "https://cdn.c24.de/city-3840x2160-24fps/master.m3u8"
                                )
                            ),
                            13377,
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Empire_State_Building_Aerial.JPG/1280px-Empire_State_Building_Aerial.JPG",
                            "this is the title"
                        )
                    )
                })
        }
        throw NetworkException(response.code(), response.errorBody().toString())
    }

}

class NetworkException(val code: Int, val errorBody: String) : Throwable()
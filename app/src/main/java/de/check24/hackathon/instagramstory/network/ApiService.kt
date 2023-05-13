package de.check24.hackathon.instagramstory.network

import de.check24.hackathon.instagramstory.mod.Storries
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("stories.list.php")
    suspend fun stories(): Response<Storries>
}
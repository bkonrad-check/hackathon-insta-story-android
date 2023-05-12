package de.check24.hackathon.instagramstory.network

import de.check24.hackathon.instagramstory.mod.Storries
import retrofit2.Response

class DataSource(
    private val apiService: ApiService
) {
    suspend fun stories(): Storries {
        val response: Response<Storries> = apiService.stories()
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw NetworkException(response.code(), response.errorBody().toString())
    }

}
class NetworkException(val code: Int, val errorBody: String) : Throwable()
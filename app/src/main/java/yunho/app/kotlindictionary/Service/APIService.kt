package yunho.app.kotlindictionary.Service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import yunho.app.kotlindictionary.DTO.RestaurantDTO

interface APIService {
    @GET("api/15099339/v1/uddi:5891d346-bf13-4c2c-a765-b035fa949786")
    fun getRestaurantList(
        @Query("page") page:Int,
        @Query("perPage") perPage:Int,
        @Query("serviceKey") serviceKey:String
    ):Call<RestaurantDTO>
}
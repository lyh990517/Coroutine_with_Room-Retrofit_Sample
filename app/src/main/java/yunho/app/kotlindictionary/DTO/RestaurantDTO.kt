package yunho.app.kotlindictionary.DTO

import com.google.gson.annotations.SerializedName
import yunho.app.kotlindictionary.Entity.Restaurant

data class RestaurantDTO(
    @SerializedName("page") val page:Int,
    @SerializedName("perPage") val perPage:Int,
    @SerializedName("data") val restaurants: List<Restaurant>
)

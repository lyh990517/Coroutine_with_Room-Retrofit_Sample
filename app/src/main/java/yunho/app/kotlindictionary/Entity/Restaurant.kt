package yunho.app.kotlindictionary.Entity

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("식당이미지(URL)") val image: String
)

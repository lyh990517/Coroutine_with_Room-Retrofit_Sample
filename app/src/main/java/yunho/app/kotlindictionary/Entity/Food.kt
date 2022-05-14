package yunho.app.kotlindictionary.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food (
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "price") val price: Int?
    )
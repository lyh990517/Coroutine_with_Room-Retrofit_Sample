package yunho.app.kotlindictionary.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import yunho.app.kotlindictionary.Entity.Food
import yunho.app.kotlindictionary.Entity.Student


@Dao
interface FoodDAO {
    @Query("SELECT * FROM Food")
    fun getALl(): List<Student>

    @Query("DELETE FROM Food WHERE id = :ID")
    fun Delete(ID:Int)

    @Insert
    fun Insert(food: Food)
}
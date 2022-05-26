package yunho.app.kotlindictionary.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import yunho.app.kotlindictionary.Entity.Student

@Dao
interface StudentDAO {

    @Query("SELECT * FROM Student")
    suspend fun getALl(): List<Student>

    @Query("DELETE FROM Student WHERE id = :ID")
    suspend fun Delete(ID:Int)

    @Insert
    suspend fun Insert(student: Student)

    @Query("DELETE FROM Student")
    suspend fun DeleteAll()
}
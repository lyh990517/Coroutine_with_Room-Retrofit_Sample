package yunho.app.kotlindictionary.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import yunho.app.kotlindictionary.DAO.FoodDAO
import yunho.app.kotlindictionary.DAO.StudentDAO
import yunho.app.kotlindictionary.Entity.Food
import yunho.app.kotlindictionary.Entity.Student

@Database(entities = [Student::class,Food::class],version = 1)
abstract class Database :RoomDatabase(){
    abstract fun foodDao(): FoodDAO
    abstract fun studentDao(): StudentDAO
}
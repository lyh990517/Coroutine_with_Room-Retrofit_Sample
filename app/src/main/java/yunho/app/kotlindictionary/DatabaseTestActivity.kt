package yunho.app.kotlindictionary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.coroutines.*
import yunho.app.kotlindictionary.Adapter.StudentAdapter
import yunho.app.kotlindictionary.Database.Database
import yunho.app.kotlindictionary.Entity.Student
import yunho.app.kotlindictionary.databinding.ActivityDatabasetestBinding
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext

class DatabaseTestActivity: AppCompatActivity(),CoroutineScope {
    private lateinit var adapter: StudentAdapter
    private lateinit var binding: ActivityDatabasetestBinding
    private lateinit var db: Database
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDatabasetestBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        db = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "StudentDB"
        ).build()
        launch (coroutineContext){
            withContext(Dispatchers.IO){
                db.studentDao().DeleteAll()
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"ajh",24,"software"))
                db.studentDao().Insert(Student(null,"asd",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                db.studentDao().Insert(Student(null,"lyh",24,"software"))
                adapter.students = db.studentDao().getALl().toMutableList()
                withContext(Dispatchers.Main){
                    adapter.notifyDataSetChanged()
                    adapter.students
                    Log.e("1234","${adapter.students}")
                }
            }
        }
        adapter = StudentAdapter(listener = {
            launch {
                withContext(Dispatchers.IO){
                    db.studentDao().Delete(it.id!!)
                    adapter.students = db.studentDao().getALl().toMutableList()
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@DatabaseTestActivity,"${it.id}",Toast.LENGTH_SHORT).show()
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
        binding.databaseList.adapter = adapter
        binding.databaseList.layoutManager = LinearLayoutManager(this)


    }

}
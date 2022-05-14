package yunho.app.kotlindictionary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import yunho.app.kotlindictionary.Adapter.StudentAdapter
import yunho.app.kotlindictionary.Database.Database
import yunho.app.kotlindictionary.Entity.Student
import yunho.app.kotlindictionary.databinding.ActivityDatabasetestBinding

class DatabaseTestActivity: AppCompatActivity() {
    private lateinit var adapter: StudentAdapter
    private lateinit var binding: ActivityDatabasetestBinding
    private lateinit var db: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDatabasetestBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        db = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "StudentDB"
        ).build()
        adapter = StudentAdapter(listener = {
            Thread(Runnable {
                db.studentDao().Delete(it.id!!)
                adapter.students = db.studentDao().getALl().toMutableList()
            })
            Toast.makeText(this,"${it.id}",Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
            adapter.students
        })
        binding.databaseList.adapter = adapter
        binding.databaseList.layoutManager = LinearLayoutManager(this)
        Thread(Runnable {
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
            Log.e("1234","${adapter.students}")
        }).start()
        adapter.notifyDataSetChanged()
        adapter.students
    }
}
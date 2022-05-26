package yunho.app.kotlindictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.room.Room
import yunho.app.kotlindictionary.Database.Database
import yunho.app.kotlindictionary.Entity.Student
import yunho.app.kotlindictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDatabase()
        initGoAPI()
    }

    private fun initDatabase() {
        binding.goDatabase.setOnClickListener {
            val intent = Intent(this,DatabaseTestActivity::class.java)
            startActivity(intent)

        }
    }
    private fun initGoAPI(){
        binding.goAPI.setOnClickListener {
            val intent = Intent(this,RestAPITestActivity::class.java)
            startActivity(intent)
        }
    }
}

//TODO 로컬데이터베이스 O
//TODO REST API
//TODO RecyclerView O
//TODO ScrollView
//TODO pull to refresh (Swipe Refresh Layout) O
//TODO MAP
//TODO WEB
//TODO Fragment
//TODO BottomNavi
//TODO EXOPlayer (video,mp3)
//TODO Seekbar
//TODO viewBinding
//TODO Splash
//TODO okhttp
//TODO Coroutine !!!!
//TODO html parsing
//TODO Motion layout
//TODO 무한 스크롤 O
//TODO MVVM
//TODO DataBinding
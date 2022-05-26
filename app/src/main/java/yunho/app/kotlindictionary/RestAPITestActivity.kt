package yunho.app.kotlindictionary

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import yunho.app.kotlindictionary.Adapter.RestaurantAdapter
import yunho.app.kotlindictionary.DTO.RestaurantDTO
import yunho.app.kotlindictionary.Service.APIService
import yunho.app.kotlindictionary.databinding.ActivityRestapitestBinding
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class RestAPITestActivity : AppCompatActivity(),CoroutineScope {
    private lateinit var binding: ActivityRestapitestBinding
    private lateinit var adapter: RestaurantAdapter
    private lateinit var API : APIService
    //코루틴 사용시 재정의 필요한 부분
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job = Job()

    private var isLoading = true
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRestapitestBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.odcloud.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        API = retrofit.create(APIService::class.java)
        adapter = RestaurantAdapter(){
            adapter.restaurants.remove(it)
            adapter.notifyDataSetChanged()
        }
        getRestaurants(100,5)
        //최상단은 -1
        //최하단은 1

        binding.itemList.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            //TODO 무한스크롤 API 왜 오류나는지 모르겠음 나중에 수정
            //리사이클러뷰가 스크롤 될때 감지
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!binding.itemList.canScrollVertically(1)){
                    if(!isLoading){
                        val handler = Handler()
                        val size = adapter.itemCount
                        getRestaurants(size,size + 50)
                        Toast.makeText(this@RestAPITestActivity,"${size} ${size + 10}",Toast.LENGTH_SHORT).show()
                        isLoading = true
                    }else{
                        isLoading = false
                    }
                }
            }

        })
        binding.itemList.adapter = adapter
        binding.itemList.layoutManager = LinearLayoutManager(this)
        //TODO pull to refresh 구현하자!!
        binding.swipeLayout.setOnRefreshListener {
            Toast.makeText(this,"refresh",Toast.LENGTH_SHORT).show()
            binding.swipeLayout.isRefreshing = false
        }

    }
    private fun getRestaurants(pageIndex:Int,perPage:Int) = launch(coroutineContext){
        withContext(Dispatchers.IO){
            val response = API.getRestaurantList(pageIndex,perPage, key)
            if(response.isSuccessful){
                val body = response.body()
                withContext(Dispatchers.Main){
                    body?.let {
                        it.restaurants.forEach {
                            Log.e("1","${it}")
                            adapter.restaurants.add(it)
                            adapter.notifyItemInserted(adapter.restaurants.size)
                        }
                    }
                }
            }
        }
    }
    companion object{
        private const val key = "IzXw1F88L1rL6JJuOYmZ+8lCgnUTMhLSAjPaeS6yf2R58uvo1Bki9dBhl/y9VzA97RRD4ff4XTgLfuCtB7jwFw=="
    }

}

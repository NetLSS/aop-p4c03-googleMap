package com.lilcode.aop.p4c03.googlemap

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.lilcode.aop.p4c03.googlemap.databinding.ActivityMainBinding
import com.lilcode.aop.p4c03.googlemap.model.LocationLatLngEntity
import com.lilcode.aop.p4c03.googlemap.model.SearchResultEntity
import com.lilcode.aop.p4c03.googlemap.utility.RetrofitUtil
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: SearchRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        job = Job()

        initAdapter()
        initViews()
        bindViews()
        initData()
        setData()
    }

    private fun initAdapter() {
        adapter = SearchRecyclerAdapter()
    }

    private fun bindViews() = with(binding){
        searchButton.setOnClickListener {
            searchKeyword(searchBarInputView.text.toString())
        }
    }

    /*
    `with` scope function 사용
     */
    private fun initViews() = with(binding){
        emptyResultTextView.isVisible = false
        recyclerView.adapter = adapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData(){
        adapter.notifyDataSetChanged()
    }

    private fun setData(){
        // mocking data
        val dataList = (0..10).map {
            SearchResultEntity(
                name = "빌딩 $it",
                fullAddress = "주소 $it",
                locationLatLng = LocationLatLngEntity(
                    it.toFloat(),
                    it.toFloat()
                )
            )
        }
        adapter.setSearchResultList(dataList){
            Toast.makeText(this, "빌딩이름 : ${it.name}, 주소 : ${it.fullAddress}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun searchKeyword(keywordString: String){
        // 비동기 처리
        launch(coroutineContext) {
            try{
                withContext(Dispatchers.IO){
                    val response = RetrofitUtil.apiService.getSearchLocation(
                        keyword = keywordString
                    )
                    if (response.isSuccessful){
                        val body = response.body()
                        withContext(Dispatchers.Main){
                            Log.e("response LSS", body.toString())
                        }
                    }
                }
            } catch (e: Exception){
                e.printStackTrace() // Permission denied (missing INTERNET permission?) 인터넷 권한 필요
            }
        }
    }


}
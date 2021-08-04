package com.lilcode.aop.p4c03.googlemap

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.lilcode.aop.p4c03.googlemap.databinding.ActivityMainBinding
import com.lilcode.aop.p4c03.googlemap.model.LocationLatLngEntity
import com.lilcode.aop.p4c03.googlemap.model.SearchResultEntity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: SearchRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()
        initViews()
        initData()
        setData()
    }

    private fun initAdapter() {
        adapter = SearchRecyclerAdapter()
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


}
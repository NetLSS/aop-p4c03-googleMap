package com.lilcode.aop.p4c03.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.lilcode.aop.p4c03.googlemap.databinding.ActivityMainBinding

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

    }

    private fun initAdapter() {
        adapter = SearchRecyclerAdapter{
            Toast.makeText(this, "아이템 클릭", Toast.LENGTH_SHORT).show()
        }
    }

    /*
    `with` scope function 사용
     */
    private fun initViews() = with(binding){
        emptyResultTextView.isVisible = false
        recyclerView.adapter = adapter

    }
}
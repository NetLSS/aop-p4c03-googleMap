package com.lilcode.aop.p4c03.googlemap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lilcode.aop.p4c03.googlemap.databinding.ViewholderSearchResultItemBinding

class SearchRecyclerAdapter(private val searchResultClickListener: (Any) -> Unit): RecyclerView.Adapter<SearchRecyclerAdapter.SearchResultViewHolder>() {

    private var searchResultList: List<Any> = List(10){it}

    inner class SearchResultViewHolder(private val binding: ViewholderSearchResultItemBinding, private val searchResultClickListener: (Any)-> Unit): RecyclerView.ViewHolder(binding.root){
        fun bindData(data: Any) = with(binding){
            titleTextView.text = "제목"
            subtitleTextView.text = "부제"
        }

        fun bindViews(data: Any){
            binding.root.setOnClickListener {
                searchResultClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ViewholderSearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding, searchResultClickListener)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bindData(searchResultList[position])
        holder.bindViews(searchResultList[position])
    }

    override fun getItemCount(): Int {
        return searchResultList.size
    }

}
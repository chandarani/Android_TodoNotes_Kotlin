package com.chanda.todonotesapp.blog.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chanda.todonotesapp.R
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.chanda.todonotesapp.data.remote.model.JsonResponse
import com.androidnetworking.interfaces.ParsedRequestListener
import com.androidnetworking.error.ANError

class BlogActivity : AppCompatActivity() {
    lateinit var recyclerViewBlogs: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindViews()
        getBlogs()
    }

    private fun getBlogs() {
        AndroidNetworking.get("http://www.mocky.io/v2/5926ce9d11000096006ccb30")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(JsonResponse::class.java, object : ParsedRequestListener<JsonResponse> {
                    override fun onResponse(response: JsonResponse) {
                        setupRecyclerView(response)
                    }

                    override fun onError(anError: ANError?) {

                    }

                })
    }

    private fun bindViews() {
        recyclerViewBlogs = findViewById(R.id.recyclerViewBlogs)
    }

    private fun setupRecyclerView(response: JsonResponse) {
        val blogsAdapter = BlogsAdapter(response.data)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerViewBlogs.layoutManager = linearLayoutManager
        recyclerViewBlogs.adapter = blogsAdapter
    }
}
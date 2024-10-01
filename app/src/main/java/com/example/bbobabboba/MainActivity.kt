package com.example.bbobabboba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbobabboba.databinding.ActivityMainBinding
import com.example.bbobabboba.main.itemrecyclerview.ItemRecyclerAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var adapter: ItemRecyclerAdapter
    val viewModel : MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        adapter = ItemRecyclerAdapter(viewModel.getList())
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
    }
}

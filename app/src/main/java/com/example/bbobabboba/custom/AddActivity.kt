package com.example.bbobabboba.custom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbobabboba.adapter.AddAdapter
import com.example.bbobabboba.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var adapter: AddAdapter
    private val viewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observe()

        binding.btnAdd.setOnClickListener {
            viewModel.addList()
        }

        binding.btnComplete.setOnClickListener {
            if (viewModel.isListValid()) {
                startActivity(Intent(this, CustomActivity::class.java).apply {
                    putParcelableArrayListExtra("list", ArrayList(viewModel.getList()))
                })
                finish()
            } else {
                Toast.makeText(this, "최소 2가지 이상 입력하셔야 합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, CustomActivity::class.java))
        finish()
    }

    private fun setupRecyclerView() {
        adapter = AddAdapter(viewModel.getList())
        binding.rvAdd.adapter = adapter
        binding.rvAdd.layoutManager = LinearLayoutManager(this)
    }

    private fun observe() {
        viewModel.afterList.observe(this) { list ->
            adapter.notifyDataSetChanged()
        }
    }
}

package com.example.bbobabboba.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbobabboba.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    private lateinit var adapter: AddAdapter
    private val list = mutableListOf<CustomData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(CustomData("입력"))
        list.add(CustomData("입력"))
        setupRecyclerView()

        binding.button.setOnClickListener {
            list.add(CustomData("입력"))
            adapter.notifyItemInserted(list.size - 1)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, CustomActivity::class.java)
            intent.putParcelableArrayListExtra("list", ArrayList(list))
            startActivity(intent)
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = AddAdapter(list)
        binding.rvAdd.adapter = adapter
        binding.rvAdd.layoutManager = LinearLayoutManager(this)
    }
}

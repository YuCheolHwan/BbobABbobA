package com.example.bbobabboba.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbobabboba.databinding.ActivityCustomBinding
import com.example.bbobabboba.adapter.CustomAdapter
import com.example.bbobabboba.adapter.HistoryAdapter

class CustomActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCustomBinding
    private val viewModel : CustomViewModel by viewModels()
    private lateinit var customAdapter: CustomAdapter
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        setupCustomRecyclerView()
        setupHistoryRecyclerView()
        viewModel.dbOpenHelperCreate(this)
        val list = intent.getParcelableArrayListExtra<CustomData>("list") ?: mutableListOf()
        viewModel.setIntentList(list)

        binding.btnPlus.setOnClickListener {
            viewModel.startAddActivity()
        }
        binding.btnShoot.setOnClickListener {
            if(list.isNullOrEmpty()){
                Toast.makeText(this,"리스트를 만들어주십시오.",Toast.LENGTH_SHORT).show()
            }else {
                val result = viewModel.customRandom()
                binding.tvChuk.text = "$result 당첨!"
            }
        }
        binding.btnDelete.setOnClickListener{
            viewModel.reTable()
            binding.tvChuk.text = "결과"
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    fun observe(){
        viewModel.startAddActivityValue.observe(this){
            if(it){
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        viewModel.history.observe(this){list ->
            if(list != null){
                historyAdapter.updateList(list)
                binding.rvHistory.scrollToPosition(list.size - 1)
            }

        }
        viewModel.intentList.observe(this){list ->
            if(list != null){
                customAdapter.updateList(list)
                binding.rvMain.scrollToPosition(list.size - 1)
            }
        }
    }
    private fun setupHistoryRecyclerView() {
        historyAdapter = HistoryAdapter(mutableListOf())
        binding.rvHistory.adapter = historyAdapter
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
    }
    private fun setupCustomRecyclerView(){
        // RecyclerView 어댑터 설정
        customAdapter = CustomAdapter(mutableListOf())
        binding.rvMain.adapter = customAdapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
    }
}

package com.example.bbobabboba.randomnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbobabboba.databinding.ActivityRandomNumberBinding
import com.example.bbobabboba.adapter.HistoryAdapter

class RandomNumberActivity : AppCompatActivity() {
    lateinit var binding : ActivityRandomNumberBinding
    val viewModel : RandomNumberViewModel by viewModels()
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.dbOpenHelperCreate(this)
        observe()
        setupHistoryRecyclerView()
        binding.btnShoot.setOnClickListener{
                var firstNum = binding.edtNum1.text.toString()
                var secondNum = binding.edtNum2.text.toString()
                if (viewModel.isInputValid(firstNum,secondNum)) {
                    binding.tvResult.text = viewModel.ranNum(firstNum.toInt(),secondNum.toInt())
                } else {
                    Toast.makeText(this, "올바른 숫자 범위를 입력해주십시오.", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnDelete.setOnClickListener{
            viewModel.dbDelete()
            binding.tvResult.text = "결과"
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupHistoryRecyclerView() {
        adapter = HistoryAdapter(mutableListOf())
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
    }
    private fun observe(){
        viewModel.history.observe(this) { list ->
            if(list != null){
                adapter.updateList(list)
                binding.rvHistory.scrollToPosition(list.size - 1)
            }
        }
    }
}

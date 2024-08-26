package com.example.bbobabboba.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bbobabboba.R
import com.example.bbobabboba.databinding.ActivityCustomBinding

class CustomActivity : AppCompatActivity() {
    lateinit var binding : ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlus.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            finish()
        }
        val inputData = intent.getParcelableArrayListExtra<CustomData>("inputData")

        // inputData를 사용하여 필요한 작업을 수행하세요
        inputData?.forEach {
            Log.d("CustomActivity", "Received input: ${it.content}")
        }
    }
}
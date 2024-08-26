package com.example.bbobabboba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbobabboba.databinding.ActivityMainBinding
import com.example.bbobabboba.main.itemrecyclerview.ItemRecyclerAdapter
import com.example.bbobabboba.main.itemrecyclerview.RandomData

class MainActivity : AppCompatActivity() {
    // databinding 초기 선언
    lateinit var binding : ActivityMainBinding
    // 데이터베이스 이름 및 버전 선언
    companion object{
        val DB_NAME = "bbobA_bbobA"
        val DB_VER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // databinding 선언
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // 뽑기 종류 리스트화
        val itemList = mutableListOf<RandomData>()
        itemList.add(RandomData(R.drawable.numbers,"커스텀 랜덤 뽑기"))
        itemList.add(RandomData(R.drawable.numbers,"랜덤 숫자 뽑기"))
        itemList.add(RandomData(R.drawable.numbers,"복불복 제비 뽑기"))

        // 리사이클러뷰 연동
        binding.rvMain.adapter = ItemRecyclerAdapter(itemList)
        binding.rvMain.layoutManager = LinearLayoutManager(this)

    }
}
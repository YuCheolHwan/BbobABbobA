package com.example.bbobabboba.custom

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.MainActivity
import com.example.bbobabboba.databinding.ActivityCustomBinding
import com.example.bbobabboba.databinding.CustomListBinding
import com.example.bbobabboba.dbopenhelper.DbOpenHelper
import com.example.bbobabboba.randomcustomadapter.CustomAdapter

class CustomActivity : AppCompatActivity() {
    lateinit var binding : ActivityCustomBinding

    val TABLE_NAME = "custom"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        var list = mutableListOf<CustomData>()
        setContentView(binding.root)

        // 뽑기 기록을 위한 데이터베이스 선언
        val dbOpenHelper = DbOpenHelper(this, MainActivity.DB_NAME, MainActivity.DB_VER,TABLE_NAME)

        reSelect(binding,dbOpenHelper,this)

        if(intent.getParcelableArrayListExtra<CustomData>("list") != null){
                list = intent.getParcelableArrayListExtra<CustomData>("list") as MutableList<CustomData>
        }
        binding.btnPlus.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            finish()
        }
        if(list!!.isNotEmpty()){
            binding.rvMain.adapter = CAdapter(list)
            binding.rvMain.layoutManager = LinearLayoutManager(this)
        }
        binding.btnShoot.setOnClickListener {
            if(list.isEmpty()){
                Toast.makeText(this,"리스트를 만들어주십시오.",Toast.LENGTH_SHORT).show()
            }else {
                val ran = (0 until list.size).random()
                var result = list[ran].inputText
                binding.tvChuk.text = "$result 당첨!"
                dbOpenHelper.insertContent(result)
                reSelect(binding, dbOpenHelper, this)
            }
        }
        binding.btnDelete.setOnClickListener{
            dbOpenHelper.reTable(TABLE_NAME)
            reSelect(binding,dbOpenHelper,this)
            binding.tvChuk.text = "결과"
        }
        binding.btnBack.setOnClickListener {
            finish()
        }

    }
    inner class CAdapter(val list : MutableList<CustomData>):RecyclerView.Adapter<CAdapter.CustomViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val binding = CustomListBinding.inflate(LayoutInflater.from(parent.context))
            return CustomViewHolder(binding)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.binding.tvItem.text = list.get(position).inputText
        }
        inner class CustomViewHolder(val binding : CustomListBinding) : RecyclerView.ViewHolder(binding.root)

    }
    private fun reSelect(binding : ActivityCustomBinding, dbOpenHelper: DbOpenHelper, context : Context){
        val adapter = CustomAdapter(dbOpenHelper.selectAll())
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        binding.rvHistory.post {
            binding.rvHistory.scrollToPosition(adapter.itemCount - 1)
        }
    }
}

package com.example.bbobabboba.randomnumber

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbobabboba.MainActivity
import com.example.bbobabboba.databinding.ActivityRandomNumberBinding
import com.example.bbobabboba.dbopenhelper.DbOpenHelper
import com.example.bbobabboba.randomcustomadapter.CustomAdapter
import java.lang.NumberFormatException

class RandomNumberActivity : AppCompatActivity() {
    lateinit var binding : ActivityRandomNumberBinding

    val TABLE_NAME = "randomNumber"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 뽑기 기록을 위한 데이터베이스 선언
        val dbOpenHelper = DbOpenHelper(this,MainActivity.DB_NAME,MainActivity.DB_VER,TABLE_NAME)

        reSelect(binding,dbOpenHelper,this)

        binding.btnShoot.setOnClickListener{
            try {
                if (binding.edtNum1.text.isEmpty() || binding.edtNum2.text.isEmpty()) {
                    Toast.makeText(this, "숫자 범위를 입력해주십시오.", Toast.LENGTH_SHORT).show()

                } else if (binding.edtNum1.text.toString()
                        .toInt() >= binding.edtNum2.text.toString().toInt()
                ) {
                    Toast.makeText(this, "올바른 숫자 범위를 입력해주십시오.", Toast.LENGTH_SHORT).show()
                } else {
                    val range = binding.edtNum1.text.toString().toInt()..
                            binding.edtNum2.text.toString().toInt()
                    val ranNum = range.random().toString()
                    binding.tvResult.text = ranNum
                    dbOpenHelper.insertContent(ranNum)
                    reSelect(binding, dbOpenHelper, this)
                }
            }catch (e:NumberFormatException){
                Toast.makeText(this,"허용된 숫자 범위를 벗어났습니다. 더 적은 수를 입력해주십시오.",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnDelete.setOnClickListener{
            dbOpenHelper.reTable(TABLE_NAME)
            reSelect(binding,dbOpenHelper,this)
            binding.tvResult.text = "결과"
        }

        binding.btnBack.setOnClickListener {
            finish()
        }


    }
    private fun reSelect(binding : ActivityRandomNumberBinding, dbOpenHelper: DbOpenHelper, context : Context){
        val adapter = CustomAdapter(dbOpenHelper.selectAll())
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        binding.rvHistory.post {
            binding.rvHistory.scrollToPosition(adapter.itemCount - 1)
        }
    }
}

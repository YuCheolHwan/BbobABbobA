package com.example.bbobabboba.randomnumber

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbobabboba.Const.Companion.RANDOM_TABLE_NAME
import com.example.bbobabboba.dbopenhelper.DbOpenHelper
import com.example.bbobabboba.dbopenhelper.TableData

class RandomNumberViewModel : ViewModel() {
    private lateinit var dbOpenHelper : DbOpenHelper
    private val beforeHistory = MutableLiveData<MutableList<TableData>>()
    val history: LiveData<MutableList<TableData>> = beforeHistory

    fun dbOpenHelperCreate(context : Context){
        dbOpenHelper = DbOpenHelper
        dbOpenHelper.initialize(context, RANDOM_TABLE_NAME)
    }

    fun ranNum(first : Int, second : Int) : String{
        val range = first..second
        val ranNum = range.random().toString()
        dbOpenHelper.insertContent(ranNum)
        loadHistory()
        return ranNum
    }

    fun dbDelete(){
        dbOpenHelper.reTable(RANDOM_TABLE_NAME)
        loadHistory()

    }

    fun isInputValid(firstNum: String?, secondNum: String?): Boolean {
        if (firstNum.isNullOrEmpty() || secondNum.isNullOrEmpty()) {
            return false
        }
        return try {
            val num1 = firstNum.toInt()
            val num2 = secondNum.toInt()
            num1 < num2
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun loadHistory() {
        beforeHistory.value = dbOpenHelper.selectAll()
    }

}
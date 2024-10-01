package com.example.bbobabboba.custom

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbobabboba.Const.Companion.CUSTOM_TABLE_NAME
import com.example.bbobabboba.dbopenhelper.DbOpenHelper
import com.example.bbobabboba.dbopenhelper.TableData

class CustomViewModel: ViewModel() {
    private lateinit var dbOpenHelper : DbOpenHelper
    private val beforeHistory = MutableLiveData<MutableList<TableData>>()
    val history: LiveData<MutableList<TableData>> = beforeHistory
    private val _intentList = MutableLiveData<MutableList<CustomData>>()
    val intentList: LiveData<MutableList<CustomData>> = _intentList


    var startAddActivityValue : MutableLiveData<Boolean> = MutableLiveData(false)

    fun dbOpenHelperCreate(context : Context){
        dbOpenHelper = DbOpenHelper
        dbOpenHelper.initialize(context, CUSTOM_TABLE_NAME)
    }

    fun customRandom() : String{
        val ran = (0 until intentList.value!!.size).random()
        var result = intentList.value!!.get(ran).inputText
        dbOpenHelper.insertContent(result)
        loadHistory()
        return result
    }
    fun reTable(){
        dbOpenHelper.reTable(CUSTOM_TABLE_NAME)
        loadHistory()
    }
    fun startAddActivity(){
        startAddActivityValue.value = true
    }

    fun setIntentList(list: MutableList<CustomData>) {
        _intentList.value = list
    }
    private fun loadHistory() {
        beforeHistory.value = dbOpenHelper.selectAll()
    }
}
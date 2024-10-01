package com.example.bbobabboba

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbobabboba.main.itemrecyclerview.RandomData

class MainViewModel : ViewModel() {
    private val _list = MutableLiveData<MutableList<RandomData>>(mutableListOf())

    init {
        setupInitialList()
    }

    private fun setupInitialList() {
        val initialList = mutableListOf(RandomData(R.drawable.numbers,"커스텀 랜덤 뽑기")
            , RandomData(R.drawable.numbers,"랜덤 숫자 뽑기"))
        _list.value = initialList
    }

    fun getList(): MutableList<RandomData> {
        return _list.value ?: mutableListOf()
    }
}
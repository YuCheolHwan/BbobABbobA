package com.example.bbobabboba.custom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {
    private val _list = MutableLiveData<MutableList<CustomData>>(mutableListOf())
    val afterList: LiveData<MutableList<CustomData>> get() = _list

    init {
        setupInitialList()
    }

    private fun setupInitialList() {
        val initialList = mutableListOf(CustomData("입력")
            , CustomData("입력"))
        _list.value = initialList
    }

    fun addList() {
        _list.value?.add(CustomData("입력"))
        _list.value = _list.value
    }

    fun getList(): MutableList<CustomData> {
        return _list.value ?: mutableListOf()
    }

    fun isListValid(): Boolean {
        return (_list.value?.size ?: 0) >= 2
    }
}

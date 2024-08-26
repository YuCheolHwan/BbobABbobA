package com.example.bbobabboba.dbopenhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbOpenHelper(context : Context, dbName : String, dbVer : Int, private val tableName : String) : SQLiteOpenHelper(context,dbName,null,dbVer) {
    // 각 테이블 명을 받아서 테이블 생성
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table $tableName (`no` integer primary key, content text)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertContent(content : String){
        val wd = writableDatabase
        val query = "insert into $tableName (content) values ($content)"
        wd.execSQL(query)
    }

    fun selectAll() : MutableList<TableData>{
        val wd = writableDatabase
        val rtList = mutableListOf<TableData>()
        val query = "select * from $tableName"
        val cursor = wd.rawQuery(query,null)
        while (cursor.moveToNext()){
            val list = TableData(cursor.getInt(0),cursor.getString(1))
            rtList.add(list)
            Log.e("select","${list.no}")

        }
        wd.close()
        return rtList
    }

    fun reTable(tableName : String){
        val wd = writableDatabase
        val query = "drop table if exists $tableName"
        wd.execSQL(query)
        onCreate(wd)
    }

}
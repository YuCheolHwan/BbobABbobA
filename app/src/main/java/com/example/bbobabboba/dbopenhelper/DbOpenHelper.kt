package com.example.bbobabboba.dbopenhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.bbobabboba.Const.Companion.DB_NAME
import com.example.bbobabboba.Const.Companion.DB_VER

object DbOpenHelper {
    private lateinit var tableName: String
    private lateinit var instance: SQLiteOpenHelper

    fun initialize(context: Context, tableName: String) {
        if (!::instance.isInitialized) {
            instance = object : SQLiteOpenHelper(context, DB_NAME, null, DB_VER) {
                override fun onCreate(p0: SQLiteDatabase?) {
                    val query = "create table if not exists custom (`no` integer primary key, content text)"
                    val query2 = "create table if not exists randomnumber (`no` integer primary key, content text)"
                    p0?.execSQL(query)
                    p0?.execSQL(query2)
                }

                override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
                }
            }
        }
        this.tableName = tableName
    }
    fun insertContent(content : String){
        val wd = instance.writableDatabase
        val query = "insert into $tableName (content) values ('$content')"
        wd.execSQL(query)
    }

    fun selectAll() : MutableList<TableData>{
        val wd = instance.writableDatabase
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
        val wd = instance.writableDatabase
        val query = "drop table if exists $tableName"
        wd.execSQL(query).let {
        }
        instance.onCreate(wd)
    }

}

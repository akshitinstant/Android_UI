package com.example.contentproviderexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context): SQLiteOpenHelper(context, "mydb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("Create table ${MyContract.TABLE_NAME} (${MyContract.COLUMN_ID} INTEGER primary key autoincrement, ${MyContract.COLUMN_NAME} TEXT)".trimIndent())
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int,
    ) {
// DB upgrades:
    db!!.execSQL("Drop table if exists ${MyContract.TABLE_NAME}")
        onCreate(db)
    }

}
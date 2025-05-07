package com.example.contentproviderexample

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {
    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(): Boolean {
        dbHelper = MyDatabaseHelper(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String?>?,
        selection: String?,
        selectionArgs: Array<out String?>?,
        sortOrder: String?,
    ): Cursor? {
        val db = dbHelper.readableDatabase
        return db.query(
            MyContract.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.${MyContract.AUTHORITY}.${MyContract.TABLE_NAME}"
    }

    override fun insert(
        uri: Uri,
        values: ContentValues?,
    ): Uri? {
        val db = dbHelper.writableDatabase
        val id = db.insert(MyContract.TABLE_NAME, null, values)
        return Uri.withAppendedPath(MyContract.CONTENT_URI, id.toString())
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String?>?,
    ): Int {
        val db = dbHelper.writableDatabase
        return db.delete(MyContract.TABLE_NAME, selection, selectionArgs)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String?>?,
    ): Int {
        val db = dbHelper.writableDatabase
        return db.update(MyContract.TABLE_NAME, values, selection, selectionArgs)
    }

}
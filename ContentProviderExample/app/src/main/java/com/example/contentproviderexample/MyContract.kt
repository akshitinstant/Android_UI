package com.example.contentproviderexample

import android.net.Uri
import androidx.core.net.toUri

object MyContract {
    const val AUTHORITY="com.example.contentproviderexample"
    val CONTENT_URI: Uri= "content://$AUTHORITY/items".toUri()

    const val TABLE_NAME="items"
    const val COLUMN_ID="_id"
    const val COLUMN_NAME="name"
}
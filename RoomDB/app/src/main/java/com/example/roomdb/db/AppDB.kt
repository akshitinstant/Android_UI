package com.example.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb.dao.SubjectDAO
import com.example.roomdb.dao.UserDAO
import com.example.roomdb.model.Subject
import com.example.roomdb.model.User

@Database(entities = [User::class, Subject::class], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun subjectDAO(): SubjectDAO
}
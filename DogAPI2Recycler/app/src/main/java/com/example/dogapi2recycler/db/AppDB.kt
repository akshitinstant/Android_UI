package com.example.dogapi2recycler.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogapi2recycler.dao.UserDAO
import com.example.dogapi2recycler.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDB: RoomDatabase() {

    abstract fun userDAO(): UserDAO
}
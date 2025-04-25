package com.example.dogapi2recycler.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dogapi2recycler.model.User

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Select * from User")
    fun getAllRecord(): LiveData<List<User>>

    @Query("Select * from User where email=:email and password=:password")
    fun verify(email:String, password:String): User?

    @Query("update User set active=:isActive where email=:email")
    fun updateActiveStatus(email: String, isActive:Boolean)

    @Query("delete from User where name=:name")
    fun deleteUserWithName(name:String)

    @Query("select * from User where email=:email")
    fun checkEmail(email:String): User?
}
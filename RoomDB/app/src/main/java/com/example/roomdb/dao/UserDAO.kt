package com.example.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdb.model.Subject
import com.example.roomdb.model.User

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Select * from User")        // compile time verification of query
    fun getUser(): LiveData<List<User>>    // Function returning Live data is by default executed in background thread by Room

    data class UserIdName(val id: Int = 0, val name: String)
    @Query("Select rowid,name from User")
    fun getAllNameWithId() : LiveData<List<UserIdName>>

    @Query("Select * from User Join Subject on User.id = Subject.subId")
    fun loadUserAndSubjectName() : LiveData<Map<User, List<Subject>>>

}
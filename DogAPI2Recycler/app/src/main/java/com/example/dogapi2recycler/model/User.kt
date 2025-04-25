package com.example.dogapi2recycler.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import javax.annotation.processing.Generated

@Entity(
    indices = [Index(value=["email"], unique = true)]
)
class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val name:String,
    val email:String,
    val password:String,
    val active: Boolean=false)

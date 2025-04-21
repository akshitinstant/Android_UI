package com.example.roomdb.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity/*(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["subId"],
            childColumns = ["userId"],
           )])*/
class Subject(
    @PrimaryKey(autoGenerate = true) val subId: Int=0,
//    val userId:Int,
    val subName: String,
    val subCode: String,
) {
}
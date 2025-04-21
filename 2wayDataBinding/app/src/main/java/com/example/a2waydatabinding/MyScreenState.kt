package com.example.a2waydatabinding

import androidx.databinding.BaseObservable
import java.io.Serializable

class MyScreenState: BaseObservable(), Serializable{
    var i :Int=1
        set(id){
            field=id
            notifyChange()
        }

    var name :String="Empty"
        set(id){
            field=id
            notifyChange()
        }

 }
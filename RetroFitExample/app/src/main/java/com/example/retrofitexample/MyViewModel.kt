package com.example.retrofitexample

import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
val repo= RepoClass()

    fun setApi(){
        repo.setAPI()
    }

//    fun getList(): List<Cat>{
//       return repo.getList()
//    }
}
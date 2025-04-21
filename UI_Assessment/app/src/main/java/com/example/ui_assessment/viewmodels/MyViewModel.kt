package com.example.ui_assessment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui_assessment.models.FoodItem
import com.example.ui_assessment.models.FoodList
import com.example.ui_assessment.repository.FoodRepo

class MyViewModel : ViewModel() {
    var list: ArrayList<FoodList> = arrayListOf()
    lateinit var repo : FoodRepo
    init {
        repo = FoodRepo()
        repo.fetchFoodLists()
    }
    //    val list= MutableLiveData<ArrayList<FoodItem>>(FoodRepo().fetchFoodItem())
    fun getFoodItems(): ArrayList<FoodItem> {
        return repo.fetchFoodItems()
    }
    fun getFoodList(): LiveData<ArrayList<FoodList>> {
        return repo.list
    }
}
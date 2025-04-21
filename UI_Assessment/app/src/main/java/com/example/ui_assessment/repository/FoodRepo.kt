package com.example.ui_assessment.repository

import androidx.lifecycle.MutableLiveData
import com.example.ui_assessment.R
import com.example.ui_assessment.models.FoodItem
import com.example.ui_assessment.models.FoodList

class FoodRepo {

    val list : MutableLiveData<ArrayList<FoodList>> = MutableLiveData<ArrayList<FoodList>>()
    fun fetchFoodItems(): ArrayList<FoodItem> {
        val foodItems = ArrayList<FoodItem>()

        foodItems.add(FoodItem(R.drawable.pizza, "Pizza","Cheesy, crispy, delicious, toppings, sauce, crust, oven-baked, Italian, pepperoni, mozzarella.", "$ 2.00"))
        foodItems.add(FoodItem(R.drawable.burger, "Burger", "Juicy, grilled, bun, patty, lettuce, tomato, cheese, fast food, ketchup, sesame.", "$ 3.00"))
        foodItems.add(FoodItem(R.drawable.sandwich, "Sandwich","Fresh, toasted, bread, filling, mayo, healthy, deli, wrap, stacked, crunchy.", "$ 2.50"))
        foodItems.add(FoodItem(R.drawable.pasta, "Pasta", "Spaghetti, penne, fusilli, creamy alfredo, marinara, baked into lasagna, tossed in pesto", "$ 4.00"))
        foodItems.add(FoodItem(R.drawable.fries, "Fries", "Crispy, golden, salted, deep-fried, side dish, snack, potato, seasoned, dipping, crunchy.", "$ 1.50"))
        foodItems.add(FoodItem(R.drawable.pizza2, "Pizza", "Cheesy, crispy, delicious, toppings, sauce, crust, oven-baked, Italian, pepperoni, mozzarella" ,"$ 2.00"))
        foodItems.add(FoodItem(R.drawable.burger2, "Burger","Juicy, grilled, bun, patty, lettuce, tomato, cheese, fast food, ketchup, sesame.", "$ 3.00"))
        foodItems.add(FoodItem(R.drawable.sanwich2, "Sandwich","Fresh, toasted, bread, filling, mayo, healthy, deli, wrap, stacked, crunchy." ,"$ 2.50"))
        foodItems.add(FoodItem(R.drawable.pasta2, "Pasta","Spaghetti, penne, fusilli, creamy alfredo, marinara, baked into lasagna, tossed in pesto","$ 2.50"))
        foodItems.add(FoodItem(R.drawable.fries2, "Fries", "Crispy, golden, salted, deep-fried, side dish, snack, potato, seasoned, dipping, crunchy.", "$ 2.50"))

        return foodItems
    }
fun fetchFoodItems1(): ArrayList<FoodItem> {
        val foodItems = ArrayList<FoodItem>()

    foodItems.add(FoodItem(R.drawable.pasta2, "Pasta", "Spaghetti, penne, fusilli, creamy alfredo, marinara, baked into lasagna, tossed in pesto", "$ 2.50"))
    foodItems.add(FoodItem(R.drawable.fries2, "Fries", "Crispy, golden, salted, deep-fried, side dish, snack, potato, seasoned, dipping, crunchy.", "$ 2.50"))
    foodItems.add(FoodItem(R.drawable.pizza2, "Pizza", "Cheesy, crispy, delicious, toppings, sauce, crust, oven-baked, Italian, pepperoni, mozzarella","$ 2.00"))
    foodItems.add(FoodItem(R.drawable.burger2, "Burger", "Juicy, grilled, bun, patty, lettuce, tomato, cheese, fast food, ketchup, sesame.", "$ 3.00"))
    foodItems.add(FoodItem(R.drawable.sanwich2, "Sandwich", "Fresh, toasted, bread, filling, mayo, healthy, deli, wrap, stacked, crunchy.", "$ 2.50"))
    foodItems.add(FoodItem(R.drawable.pizza, "Pizza", "Cheesy, crispy, delicious, toppings, sauce, crust, oven-baked, Italian, pepperoni, mozzarella", "$ 2.00"))
    foodItems.add(FoodItem(R.drawable.burger, "Burger", "Juicy, grilled, bun, patty, lettuce, tomato, cheese, fast food, ketchup, sesame.", "$ 3.00"))
    foodItems.add(FoodItem(R.drawable.sandwich, "Sandwich", "Fresh, toasted, bread, filling, mayo, healthy, deli, wrap, stacked, crunchy." ,"$ 2.50"))
    foodItems.add(FoodItem(R.drawable.pasta, "Pasta","Spaghetti, penne, fusilli, creamy alfredo, marinara, baked into lasagna, tossed in pesto" ,"$ 4.00"))
    foodItems.add(FoodItem(R.drawable.fries, "Fries","Crispy, golden, salted, deep-fried, side dish, snack, potato, seasoned, dipping, crunchy." ,"$ 1.50"))

        return foodItems
    }

//    fun fetchFoodLists(): ArrayList<FoodList> {
    fun fetchFoodLists(){
        val foodLists = ArrayList<FoodList>()
        foodLists.add(FoodList("Vegetarian", fetchFoodItems()))
        foodLists.add(FoodList("Non-Veg", fetchFoodItems1()))
        foodLists.add(FoodList("Sea-Food", fetchFoodItems()))
        foodLists.add(FoodList("Italian", fetchFoodItems1()))
        foodLists.add(FoodList("Asian", fetchFoodItems()))
        foodLists.add(FoodList("South Indian", fetchFoodItems1()))
        list.value = foodLists
        //return foodLists
    }
}

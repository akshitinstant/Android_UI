package com.example.dogapi2recycler.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.FragmentDogProfileBinding
import com.example.dogapi2recycler.viewmodel.MyViewModel

class DogProfile() : Fragment() {
    lateinit var bind: FragmentDogProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.d("h3", "onCreateView called")
        bind = FragmentDogProfileBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        var name = if(!arguments?.getString("name").isNullOrEmpty()) arguments?.getString("name") else getString(R.string.dog)
        var breed = if(!arguments?.getString("breed").isNullOrEmpty()) arguments?.getString("breed") else getString(R.string.Mixed)
        val breedFor = if(!arguments?.getString("for").isNullOrEmpty()) arguments?.getString("for") else getString(R.string.cattle_herding)
        val life = if(!arguments?.getString("life").isNullOrEmpty()) arguments?.getString("life") else getString(R.string._10_years)
        val imageURL = arguments?.getString("imgURL")
        val image = if(!arguments?.getString("image").equals("HkC31gcNm")) arguments?.getString("image") else getString(R.string.hjq8ge5v7)
        val height = if(!arguments?.getString("height").isNullOrEmpty()) arguments?.getString("height") else "40"
        var weight = if(!arguments?.getString("weight").isNullOrEmpty()) arguments?.getString("weight") else "25"
        val temperament = if(!arguments?.getString("temperament").isNullOrEmpty()) arguments?.getString("temperament") else getString(R.string.loyal_brave)
        val origin = if(!arguments?.getString("origin").isNullOrEmpty()) arguments?.getString("origin") else getString(R.string.united_kingdom)
        Log.d("h3", "$name, $imageURL$image.jpg, $weight")
        var imageLink=imageURL + image + ".jpg"
        Glide.with(this).load(imageLink).into(bind.image)
        bind.name.text = name
        bind.breed.text = breed
        bind.life.text = life
        bind.origin.text = origin
        bind.height.text = height
        bind.weight.text = weight
        bind.forTxt.text = breedFor
        bind.temperament.text = temperament
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("h3", "onDestroyView called")
        activity?.findViewById<View>(R.id.activity_content)?.visibility = View.VISIBLE
        activity?.findViewById<View>(R.id.container)?.visibility = View.GONE
    }
}
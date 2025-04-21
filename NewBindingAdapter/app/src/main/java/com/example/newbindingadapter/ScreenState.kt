package com.example.newbindingadapter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.io.Serializable

class ScreenState : BaseObservable(), Serializable {

    @Bindable
    var count: Int = 0
        set(id) {
            field = id
            notifyPropertyChanged(BR.count)
        }

}



/*
* notifyPropertyChange(BR.variable): is used to notify the update of a specific property
* notifyChange(): is used to notify the update of all properties.
*
* notifyPropertyChange(BR.variable): must be used in Setters, It requires @Bindable annotation, which creates a unique identifier in the BR class
* notifyChange(): must be used inside the functions, where you need to notify the changes for all the properties.
*/
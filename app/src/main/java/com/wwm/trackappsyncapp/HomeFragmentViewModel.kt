package com.wwm.trackappsyncapp

import android.app.Application
import androidx.lifecycle.*

class HomeFragmentViewModel() : ViewModel() {

    private val _list = MutableLiveData<List<TrackItem>>()
    val list: LiveData<List<TrackItem>>
        get() = _list

    init {

    }


}

class HomeFragmentViewModelFactory() :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
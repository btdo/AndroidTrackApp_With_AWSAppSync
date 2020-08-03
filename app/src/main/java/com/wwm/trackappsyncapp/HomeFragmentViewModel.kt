package com.wwm.trackappsyncapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeFragmentViewModel() : ViewModel() {

    private val _list = MutableLiveData<List<TrackItemModel>>()
    val list: LiveData<List<TrackItemModel>>
        get() = _list

    init {
        val trackList = mutableListOf<TrackItemModel>()
        trackList.add(TrackItemModel("", "btdo", "EB1231232WEB"))
        _list.value = trackList
    }
}

@ExperimentalCoroutinesApi
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
package com.wwm.trackappsyncapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemFragmentViewModel : ViewModel() {

    fun addItem(task: TrackItemModel) {

    }
}

class ItemFragmentViewModelFactory :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemFragmentViewModel::class.java)) {
            return ItemFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
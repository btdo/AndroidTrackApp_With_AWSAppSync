package com.wwm.trackappsyncapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.TrackItem

class ItemFragmentViewModel : ViewModel() {

    fun addItem(task: TrackItemModel) {
        val post = TrackItem.builder().pin(task.pin).description("Test") .build()
        Amplify.DataStore.save(post,
            { Log.i("MyAmplifyApp", "Saved a post.") },
            { Log.e("MyAmplifyApp", "Save failed.", it) }
        )
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
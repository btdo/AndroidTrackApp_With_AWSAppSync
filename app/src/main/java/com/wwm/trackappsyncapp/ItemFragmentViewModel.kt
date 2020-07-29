package com.wwm.trackappsyncapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.TrackSyncItem
import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

class ItemFragmentViewModel : ViewModel() {

    fun addItem(item: TrackItemModel) {
        val post = TrackSyncItem.builder().pin(item.pin).description(item.description).insertDateTime(
            getCurrentDateTime().toString("yyyy-MM-dd HH:mm:ss.SSS")).build()
        Amplify.DataStore.save(post,
            {
                Log.i("MyAmplifyApp", "Saved a post.")
            },
            {
                Log.e("MyAmplifyApp", "Save failed.", it)
            }
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
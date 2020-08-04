package com.wwm.trackappsyncapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.api.graphql.model.ModelMutation
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

    fun addItem(task: TrackItemModel) {
        val item = TrackSyncItem.builder().pin(task.pin).desc(task.description).build()

        Amplify.API.mutate(
            ModelMutation.create(item),
            { response -> Log.i("MyAmplifyApp", "Updated Item with id: " + response.data.id) },
            { error -> Log.e("MyAmplifyApp", "Update failed", error) }
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
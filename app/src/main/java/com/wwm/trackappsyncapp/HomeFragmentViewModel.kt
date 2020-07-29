package com.wwm.trackappsyncapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.TrackSyncItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeFragmentViewModel() : ViewModel() {

    private val _list = MutableLiveData<List<TrackItemModel>>()
    val list: LiveData<List<TrackItemModel>>
        get() = _list

    init {
       subscribe()
    }

    private fun subscribe() {
        Amplify.DataStore.observe(
            TrackSyncItem::class.java,
            { Log.i("MyAmplifyApp", "Observation began.") },
            {
                Log.i("MyAmplifyApp", "Observing: ${it.item()}")
                query()
            },
            { Log.e("MyAmplifyApp", "Observation failed.", it) },
            { Log.i("MyAmplifyApp", "Observation complete.") }
        )
    }

    fun query () {
        Amplify.DataStore.query(TrackSyncItem::class.java, Where.matches(TrackSyncItem.OWNER.eq("sbteststg02")),
            {
                val list = mutableListOf<TrackItemModel>()
                while (it.hasNext()) {
                    val item = it.next()
                    Log.i("MyAmplifyApp", "Owner: ${item.owner}")
                    list.add(TrackItemModel(item.id, item.owner, item.pin, item.description))
                }

                _list.postValue(list)
            },
            { Log.e("MyAmplifyApp", "Query failed.", it) }
        )
    }

    fun delete(item: TrackItemModel){
        Amplify.DataStore.query(TrackSyncItem::class.java, Where.id(item.id),
            { matches ->
                if (matches.hasNext()) {
                    val post = matches.next()
                    Amplify.DataStore.delete(post,
                        {
                            Log.i("MyAmplifyApp", "Deleted a post.")
                        },
                        {
                            Log.e("MyAmplifyApp", "Delete failed.", it)
                        }
                    )
                }
            },
            { Log.e("MyAmplifyApp", "Query failed.", it) }
        )
    }

    fun update(item: TrackItemModel){
        Amplify.DataStore.query(TrackSyncItem::class.java, Where.id(item.id),
            { matches ->
                if (matches.hasNext()) {
                    var post = matches.next()
                    val edited = post.copyOfBuilder()
                        .description("Just updated")
                        .build()

                    Amplify.DataStore.save(edited,
                        {
                            Log.i("MyAmplifyApp", "Deleted a post.")
                        },
                        {
                            Log.e("MyAmplifyApp", "Delete failed.", it)
                        }
                    )
                }
            },
            { Log.e("MyAmplifyApp", "Query failed.", it) }
        )
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
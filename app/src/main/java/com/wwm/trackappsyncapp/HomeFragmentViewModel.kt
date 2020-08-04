package com.wwm.trackappsyncapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.api.ApiOperation
import com.amplifyframework.api.graphql.GraphQLRequest
import com.amplifyframework.api.graphql.PaginatedResult
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelPagination
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.api.graphql.model.ModelSubscription
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.TrackSyncItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeFragmentViewModel() : ViewModel() {

    var subscription: ApiOperation<*>? = null
    private val _list = MutableLiveData<List<TrackItemModel>>()
    val list: LiveData<List<TrackItemModel>>
        get() = _list

    init {
        //subscribe()
        queryFirstPage()
    }

    fun subscribe() {
        subscription = Amplify.API.subscribe(
            ModelSubscription.onCreate(TrackSyncItem::class.java),
            { Log.i("ApiQuickStart", "Subscription established") },
            { onCreated ->
                Log.i("ApiQuickStart", "Item create subscription received: " + onCreated)
            },
            { onFailure -> Log.e("ApiQuickStart", "Subscription failed", onFailure) },
            { Log.i("ApiQuickStart", "Subscription completed") }
        )
    }

    fun queryFirstPage() {
        query(
            ModelQuery.list(
                TrackSyncItem::class.java,
                ModelPagination.firstPage().withLimit(1_000)
            )
        )
    }

    fun query(request: GraphQLRequest<PaginatedResult<TrackSyncItem>>) {
        Amplify.API.query(
            request,
            { response ->
                if (response.hasData()) {
                    val list = mutableListOf<TrackItemModel>()
                    for (item in response.data.items) {
                        Log.d("MyAmplifyApp", item.pin)
                        list.add(TrackItemModel(item.id, item.owner, item.pin, item.desc))
                    }

                    _list.postValue(list)
                    if (response.data.hasNextResult()) {
                        query(response.data.requestForNextResult)
                    }
                }
            },
            { failure -> Log.e("MyAmplifyApp", "Query failed.", failure) }
        )
    }

    fun delete(trackItemModel: TrackItemModel) {
        Amplify.API.query(
            ModelQuery.get(TrackSyncItem::class.java, trackItemModel.id),
            { response ->
                Log.i("MyAmplifyApp", response.data.pin)
                Amplify.API.mutate(
                    ModelMutation.delete(response.data),
                    { response ->
                        Log.i("MyAmplifyApp", "Updated Todo with id: " + response.data.id)
                        queryFirstPage()
                    },
                    { error -> Log.e("MyAmplifyApp", "Update failed", error) }
                )
            },
            { error -> Log.e("MyAmplifyApp", "Query failed", error) }
        )
    }


    override fun onCleared() {
        super.onCleared()
        subscription?.cancel()
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
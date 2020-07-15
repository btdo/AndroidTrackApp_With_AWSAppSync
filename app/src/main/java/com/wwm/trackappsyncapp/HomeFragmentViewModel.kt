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
import com.amplifyframework.datastore.generated.model.TrackItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeFragmentViewModel() : ViewModel() {

    private val _list = MutableLiveData<List<TrackItemModel>>()
    val list: LiveData<List<TrackItemModel>>
        get() = _list

    var subscriptionCreate: ApiOperation<*>? = null
    var subscriptionDelete: ApiOperation<*>? = null
    var subscriptionUpdate: ApiOperation<*>? = null

    init {
        // queryDataStore()
        // subscribeDataSource()
        queryAPI()
        subscribeAPI()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptionCreate?.let { it.cancel() }
        subscriptionDelete?.let { it.cancel() }
        subscriptionUpdate?.let { it.cancel() }
    }

    private fun subscribeAPI() {
        subscriptionCreate = Amplify.API.subscribe(
            ModelSubscription.onCreate(TrackItem::class.java),
            { Log.i("ApiQuickStart", "Subscription established") },
            { onCreated -> queryAPI() },
            { onFailure -> Log.e("ApiQuickStart", "Subscription failed", onFailure) },
            { Log.i("ApiQuickStart", "Subscription completed") }
        )

        subscriptionUpdate = Amplify.API.subscribe(
            ModelSubscription.onUpdate(TrackItem::class.java),
            { Log.i("ApiQuickStart", "Subscription established") },
            { onCreated -> queryAPI() },
            { onFailure -> Log.e("ApiQuickStart", "Subscription failed", onFailure) },
            { Log.i("ApiQuickStart", "Subscription completed") }
        )

        subscriptionDelete = Amplify.API.subscribe(
            ModelSubscription.onDelete(TrackItem::class.java),
            { Log.i("ApiQuickStart", "Subscription established") },
            { onDeleted ->   queryAPI() },
            { onFailure -> Log.e("ApiQuickStart", "Subscription failed", onFailure) },
            { Log.i("ApiQuickStart", "Subscription completed") }
        )
    }

    private fun subscribeDataSource() {
        Amplify.DataStore.observe(
            TrackItem::class.java,
            { Log.i("MyAmplifyApp", "Observation began.") },
            {
                Log.i("MyAmplifyApp", "Observing: ${it.item()}")
                queryDataStore()
            },
            { Log.e("MyAmplifyApp", "Observation failed.", it) },
            { Log.i("MyAmplifyApp", "Observation complete.") }
        )
    }

    fun queryDataStore () {
        //Where.matches(TrackItem.USER_ID.eq("sbteststg02")
        Amplify.DataStore.query(
            TrackItem::class.java,
            {
                val list = mutableListOf<TrackItemModel>()
                while (it.hasNext()) {
                    val item = it.next()
                    Log.i("MyAmplifyApp", "Title: ${item.pin}")
                    list.add(TrackItemModel(item.id, item.userId, item.pin, item.description))
                }

                _list.postValue(list)
            },
            { Log.e("MyAmplifyApp", "Query failed.", it) }
        )
    }

    fun queryAPI() {
        queryAPI(ModelQuery.list(TrackItem::class.java, ModelPagination.firstPage().withLimit(1_000)))
    }

    fun queryAPI(request: GraphQLRequest<PaginatedResult<TrackItem>>) {
        Amplify.API.query(
            request,
            { response ->
                if (response.hasData()) {
                    val list = mutableListOf<TrackItemModel>()
                    for (item in response.data.items) {
                        list.add(TrackItemModel(item.id, item.userId, item.pin, item.description))
                    }

                    _list.postValue(list)
                    if (response.data.hasNextResult()) {
                        queryAPI(response.data.requestForNextResult)
                    }
                }
            },
            { failure -> Log.e("MyAmplifyApp", "Query failed.", failure) }
        )
    }

    fun delete(item: TrackItemModel){
        Amplify.API.query(
            ModelQuery.list(TrackItem::class.java, TrackItem.ID.eq(item.id)),
            { response ->
                for (todo in response.data) {
                    Amplify.API.mutate(
                        ModelMutation.delete(todo) ,
                        { Log.i("MyAmplifyApp", "Deleted a post.")
                            queryAPI()
                        },
                        { Log.e("MyAmplifyApp", "Save failed.", it) }
                    )
                }
            },
            { error -> Log.e("MyAmplifyApp", "Query failure", error) }
        )
    }

    fun deleteDataSource(item: TrackItemModel){
        Amplify.DataStore.query(
            TrackItem::class.java, Where.id(item.id),
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
package id.co.culturefluent.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        delay(3000)
        emit(Resource.loading())
        val go = networkCall.invoke()
        emit(networkCall.invoke())
    }

suspend fun <T> performGetRealValueOperation(networkCall: suspend () -> Resource<T>): Resource<T> =
    networkCall.invoke()


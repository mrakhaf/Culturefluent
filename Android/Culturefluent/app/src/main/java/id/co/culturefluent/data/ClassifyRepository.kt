package id.co.culturefluent.data

import id.co.culturefluent.utils.performGetRealValueOperation
import okhttp3.RequestBody
import javax.inject.Inject

class ClassifyRepository @Inject constructor(
    private val classifyDataSource: ClassifyDataSource
){
    suspend fun predict(body : RequestBody) = performGetRealValueOperation { classifyDataSource.predict(body) }
}
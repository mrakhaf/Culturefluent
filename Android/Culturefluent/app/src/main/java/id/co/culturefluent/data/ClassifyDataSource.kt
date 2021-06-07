package id.co.culturefluent.data

import okhttp3.RequestBody
import javax.inject.Inject

class ClassifyDataSource @Inject constructor(
    private val classifyService: ClassifyService
) : BaseDataSource() {
    private val contentType = "image/jpeg"
    suspend fun predict(body : RequestBody) = getResult { classifyService.predict(contentType, body)}
}
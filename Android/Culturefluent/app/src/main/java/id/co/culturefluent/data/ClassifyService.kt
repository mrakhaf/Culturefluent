package id.co.culturefluent.data

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ClassifyService {

    @POST("prediction_1")
    suspend fun predict(@Header("content-type") contentType : String,
        @Body body: RequestBody) : Response<RootResponse<List<Double>>>

}
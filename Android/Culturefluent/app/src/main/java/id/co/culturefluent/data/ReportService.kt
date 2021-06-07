package id.co.culturefluent.data

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ReportService {

//    @Multipart
//    @POST("report_error")
//    suspend fun report(@Header("content-type") contentType : String,
//                       @Part("img") img: MultipartBody.Part,
//                       @Part("name") name: RequestBody
//    ) : Response<RootResponse<List<String>>>

    @Multipart
    @POST("report_error")
    suspend fun report(@PartMap part: Map<String, @JvmSuppressWildcards RequestBody>
    ) : Response<RootResponse<List<String>>>

}
package id.co.culturefluent.data

import android.util.Log
import id.co.culturefluent.utils.Resource
import retrofit2.Response
import timber.log.Timber


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<RootResponse<T>>): Resource<List<T>> {
        try {
            Log.d("dadfqweqwemocewo", "here")

            val response = call()
            Log.d("dsfdsaw546", response.isSuccessful.toString())
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("dsfdsdvvsaw546", body.toString())
                if (body != null) {
                    Log.d("ds645htrh46", body.status.toString())
                    if (body.status == "true") {
                        Log.d("ds6sdf435b6", body.data.toString())
                        return Resource.success(body.data)
                    } else {
                        Log.d("ds645htrh46", "ke error1")
                        return error(body!!.message!!)
                    }
                }

            }
            Log.d("ds645htrh46", "ke error 2")
            if (response.code() == 503)
                return error("Maaf server sedang sibuk, silahkan coba lagi lain waktu :)")
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.d("dsfdsaw546exeption", e.message ?: e.toString())
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error(message)
//        return Resource.error("Network call has failed for a following reason: $message")
    }

    protected suspend fun <T> getResultStatus(call: suspend () -> Response<RootResponse<T>>): Resource<String> {
        try {
            Log.d("fafdsvxvzg", "here")

            val response = call()
            Log.d("sdfsfsdv", response.isSuccessful.toString())
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("dfsfdscxv", body.toString())
                if (body != null) {
                    Log.d("sfddcxvdf", body.status)
                    return Resource.success(body.status)
                }

            }
            Log.d("ds645htrh46", "ke error 2")
            if (response.code() == 503)
                return error("Maaf server sedang sibuk, silahkan coba lagi lain waktu :)")
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.d("dsfdsaw546exeption", e.message ?: e.toString())
            return error(e.message ?: e.toString())
        }
    }


}



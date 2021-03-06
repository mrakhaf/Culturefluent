package id.co.culturefluent.data

import id.co.culturefluent.utils.performGetRealValueOperation
import okhttp3.RequestBody
import javax.inject.Inject

class ReportRepository @Inject constructor(
    private val reportDataSource: ReportDataSource
) {
    suspend fun report(body: RequestBody, name:String) =
        performGetRealValueOperation { reportDataSource.report(body, name) }
}
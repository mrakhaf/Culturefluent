package id.co.culturefluent.data

import okhttp3.RequestBody
import javax.inject.Inject

class ReportDataSource @Inject constructor(
    private val reportService: ReportService
) : BaseDataSource() {
    private val contentType = "application/json"
    suspend fun report(
        body: RequestBody,
        name:String
    ) = getResultStatus { reportService.report(body,name) }
}
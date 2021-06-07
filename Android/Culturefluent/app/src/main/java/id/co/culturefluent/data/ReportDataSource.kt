package id.co.culturefluent.data

import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ReportDataSource @Inject constructor(
    private val reportService: ReportService
) : BaseDataSource() {
    private val contentType = "application/json"
    suspend fun report(part: Map<String, RequestBody>
    ) = getResultStatus { reportService.report(part )}
}
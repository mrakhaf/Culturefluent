package id.co.culturefluent.ui.report

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.culturefluent.data.ClassifyRepository
import id.co.culturefluent.data.InstrumentModel
import id.co.culturefluent.data.ReportRepository
import id.co.culturefluent.utils.Resource
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ReportViewModel @ViewModelInject constructor(
    private val repository: ReportRepository
) : ViewModel() {

    private val _result = MutableLiveData<Resource<String>>()
    val result : LiveData<Resource<String>> = _result

    fun report(part: Map<String, RequestBody>){
        _result.value = Resource.loading()
        viewModelScope.launch {
            _result.value = repository.report(part)
        }
    }
}
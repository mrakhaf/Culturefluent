package id.co.culturefluent.ui.classifyobject

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.culturefluent.data.ClassifyRepository
import id.co.culturefluent.data.InstrumentModel
import id.co.culturefluent.utils.Resource
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class ClassifyViewModel @ViewModelInject constructor(
    private val repository: ClassifyRepository
) : ViewModel() {

    private val _result = MutableLiveData<Resource<List<List<Double>>>>()
    val result : LiveData<Resource<List<List<Double>>>> = _result

    var currentPhotoPath = ""
    fun predict(body : RequestBody){
        _result.value = Resource.loading()
        viewModelScope.launch {
            _result.value = repository.predict(body)
        }
    }
}
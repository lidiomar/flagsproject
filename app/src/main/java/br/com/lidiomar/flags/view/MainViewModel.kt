package br.com.lidiomar.flags.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.model.repository.MainRepository
import br.com.lidiomar.flags.utils.DataResource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository): ViewModel() {

    private val _countries = MutableLiveData<DataResource<List<Country>>>()
    val countries: LiveData<DataResource<List<Country>>>
    get() = _countries

    fun getAllCountries() {
        viewModelScope.launch {
            _countries.postValue(DataResource.loading("Loading data..."))
            val countries = repository.getAllCountries()
            _countries.postValue(countries)
        }
    }

}
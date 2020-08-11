package br.com.lidiomar.flags.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.lidiomar.flags.model.data.Country
import br.com.lidiomar.flags.model.repository.MainRepository
import br.com.lidiomar.flags.utils.DataResource
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainRepository(application)

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
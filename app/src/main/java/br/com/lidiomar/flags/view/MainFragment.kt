package br.com.lidiomar.flags.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import br.com.lidiomar.flags.viewmodel.MainViewModel
import br.com.lidiomar.flags.R
import br.com.lidiomar.flags.utils.DataResource

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onResume() {
        super.onResume()
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            when(countries.status) {
                DataResource.Status.LOADING -> {
                    Log.i("flags", "Show loading")
                }

                DataResource.Status.SUCCESS -> {
                    countries.data?.let { countriesData ->
                        for(country in countriesData) {
                            Log.i("flags", country.name)
                        }
                    }
                }

                DataResource.Status.ERROR -> {
                    Log.i("flags", "ERROR")
                }
            }
        }
        viewModel.getAllCountries()
    }
}
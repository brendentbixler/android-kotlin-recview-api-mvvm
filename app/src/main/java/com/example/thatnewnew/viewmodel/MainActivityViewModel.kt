package com.example.thatnewnew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatnewnew.model.RecyclerList
import com.example.thatnewnew.network.RetroService
import com.example.thatnewnew.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    lateinit var recyclerListLiveData: MutableLiveData<RecyclerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListLiveData
    }

    // Make API call in IO instead of the main thread
    fun makeAiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("ny")
            recyclerListLiveData.postValue(response)
        }
    }
}
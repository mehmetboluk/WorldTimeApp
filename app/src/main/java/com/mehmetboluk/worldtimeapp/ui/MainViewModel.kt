package com.mehmetboluk.worldtimeapp.ui

import android.content.Context
import androidx.lifecycle.*
import com.mehmetboluk.worldtimeapp.database.CitiesDatabase
import com.mehmetboluk.worldtimeapp.database.Model
import com.mehmetboluk.worldtimeapp.model.CityTime
import com.mehmetboluk.worldtimeapp.network.ApiService
import com.mehmetboluk.worldtimeapp.network.RetrofitInstance
import kotlinx.coroutines.*


class MainViewModel : ViewModel() {

    private var cityTimeLiveData : MutableLiveData<CityTime> = MutableLiveData()
    private var citiesDatabase : ArrayList<Model> = ArrayList()

    fun getCityTimeObserver() : MutableLiveData<CityTime> {
        return cityTimeLiveData
    }

    fun callDataFromRoom(context : Context) : ArrayList<Model>{
        val data = CitiesDatabase(context).getCitiesDAO().getAll()
        citiesDatabase.addAll(data)
        return citiesDatabase
    }


    fun upsertData(context : Context, city : Model){
        viewModelScope.launch(Dispatchers.IO) {
            CitiesDatabase(context).getCitiesDAO().upsertData(city)
        }
    }

    fun deleteData(context : Context, city : Model){
        viewModelScope.launch(Dispatchers.IO) {
            CitiesDatabase(context).getCitiesDAO().delete(city)
        }
    }

    fun deleteAllData(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            CitiesDatabase(context).getCitiesDAO().deleteAll()
        }
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
                val retroInstance = RetrofitInstance.getRetroInstance().create(ApiService::class.java)
                val response = retroInstance.getDataFromApi()
                cityTimeLiveData.postValue(response)
        }
    }
}
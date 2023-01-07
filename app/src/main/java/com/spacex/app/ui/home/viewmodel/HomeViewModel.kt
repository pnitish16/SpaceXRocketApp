package com.spacex.app.ui.home.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.spacex.app.domain.model.Rocket
import com.spacex.app.domain.model.RocketDetail
import com.spacex.app.domain.repository.RocketRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RocketRepository
) : ViewModel() {

    private val _rocketListResponse = MutableSharedFlow<List<Rocket>>()
    val rocketListResponse = _rocketListResponse.asSharedFlow()
    private val _isLoading = MutableSharedFlow<Boolean>(1)
    val isLoading = _isLoading.asSharedFlow()

    fun fetchRockets() {
        viewModelScope.launch {
            _isLoading.emit(true)
            repository.getRockets().let { response ->
                if (response.isNotEmpty())
                    _rocketListResponse.emit(response)
                else _errorResponse.emit("Some issue occurred, please try again")
            }
            _isLoading.emit(false)
        }
    }

    val rockets: LiveData<List<Rocket>> =
        repository.getRocketsFromDb()
            .asLiveData()

    fun getRocketDetailFromDb(rocketId: String) =
        repository.getRocketDetailFromDb(rocketId)
            .asLiveData()

    private val _rocketDetailResponse = MutableSharedFlow<RocketDetail>()
    val rocketDetailResponse = _rocketDetailResponse.asSharedFlow()
    private val _errorResponse = MutableSharedFlow<String>()
    val errorResponse = _errorResponse.asSharedFlow()

    fun fetchRocketDetail(rocketId: String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            repository.fetchRocketDetails(rocketId).let { response ->
                if (response == null) {
                    _errorResponse.emit("Some issue occurred, please try again")
                } else {
                    _rocketDetailResponse.emit(response)
                }
            }
            _isLoading.emit(false)
        }
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}
package com.jarvis.livedatademo

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/15
 */
class LiveDataViewModel(private val dataSource: DataSource) : ViewModel() {

    val currentTime = dataSource.getCurrentTime()

    val currentTimeTransformed = currentTime.switchMap {
        liveData {
            emit(timeStampToTime(it))
        }
    }

    val currentWeather: LiveData<String> = liveData {

        emit(LOADING_STRING)
        emitSource(dataSource.fetchWeather())

    }

    val cachedValue = dataSource.cachedData

    fun onRefresh() {
        viewModelScope.launch {
            dataSource.fetchNewData()
        }
    }

    private suspend fun timeStampToTime(timeStamp: Long): String {
        delay(500)
        val date = Date(timeStamp)
        return date.toString()
    }

    companion object {
        const val LOADING_STRING = "loading..."
    }

}


object LiveDataVMFactory : ViewModelProvider.Factory {


    private val dataSource = DefaultDataSource(Dispatchers.IO)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return LiveDataViewModel(dataSource) as T
    }


}
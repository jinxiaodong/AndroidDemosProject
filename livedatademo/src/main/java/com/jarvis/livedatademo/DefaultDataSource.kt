package com.jarvis.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/15
 */
class DefaultDataSource(private val ioDispatcher: CoroutineDispatcher) : DataSource {

    override fun getCurrentTime(): LiveData<Long> =
        liveData {
            while (true) {
                emit(System.currentTimeMillis())
                delay(1000)
            }
        }


    private val weatherConditions = listOf("Sunny", "Cloudy", "Rainy", "Stormy", "Snowy")


    override fun fetchWeather(): LiveData<String> = liveData {
        var counter = 0
        while (true) {
            counter++
            delay(1000)
            emit(weatherConditions[counter % weatherConditions.size])

        }
    }


    private val _cacheData = MutableLiveData("This is old Data")


    override val cachedData: LiveData<String> = _cacheData

    override suspend fun fetchNewData() {
        withContext(Dispatchers.Main) {
            _cacheData.value = "Fetching new data"
            _cacheData.value = simulateNetWorkDataFetch()
        }
    }

    private var counter = 0

    private suspend fun simulateNetWorkDataFetch(): String = withContext(ioDispatcher) {
        delay(3000)
        counter++
        "New data from request #$counter"
    }
}


interface DataSource {

    fun getCurrentTime(): LiveData<Long>

    fun fetchWeather(): LiveData<String>


    val cachedData: LiveData<String>

    suspend fun fetchNewData()
}
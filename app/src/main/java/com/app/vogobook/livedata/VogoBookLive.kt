package com.app.vogobook.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.vogobook.livedata.`object`.LiveDataBook

/**
 * You can write object that is similar to LiveDataBook.
 * LiveDataBook means object is used throw two methods: initLiveDataBook and implLiveDataBook
 * Now, you embed it into MainModule.
 * Let's get started with it.
 */
class VogoBookLive : ViewModel() {

    private val liveDataBook: MutableLiveData<LiveDataBook> = MutableLiveData()

    // Initilize object book
    fun initLiveDataBook(item: LiveDataBook?) {
        liveDataBook.value = item
    }

    // Implement for other screen
    open fun implLiveDataBook(): LiveData<LiveDataBook?>? {
        return liveDataBook
    }
}
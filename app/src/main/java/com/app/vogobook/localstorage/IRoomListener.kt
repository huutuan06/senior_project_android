package com.app.vogobook.localstorage

interface IRoomListener<T> {
    fun showListData(t : List<T>)
}
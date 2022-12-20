package com.aminovic.loula.domain.utils

interface Paginator<K, T> {
    suspend fun loadNextItems()
    fun reset()
}
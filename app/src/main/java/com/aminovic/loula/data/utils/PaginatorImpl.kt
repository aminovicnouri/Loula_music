package com.aminovic.loula.data.utils

import com.aminovic.loula.domain.utils.Paginator
import com.aminovic.loula.domain.utils.Resource

class PaginatorImpl<K, T>(
    private val initialKey: K,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: K) -> Resource<T>,
    private inline val getNextKey: suspend (T) -> K,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (result: T) -> Unit,
) : Paginator<K, T> {
    private var currentKey: K? = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        } else {
            isMakingRequest = true
            onLoadUpdated(true)
            val result = onRequest(currentKey!!)
            isMakingRequest = false
            when (result) {
                is Resource.Error -> {
                    onError(Throwable(message = result.message))
                    onLoadUpdated(false)
                    return
                }
                is Resource.Success -> {
                    val items = result.data!!
                    currentKey = getNextKey(items)
                    onSuccess(items)
                    onLoadUpdated(false)
                }
            }
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}
package com.aminovic.loula.domain.sync

interface Syncable {
    suspend fun synchronize()
}

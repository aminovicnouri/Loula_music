package com.aminovic.loula.data.mappers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun <T, R> Flow<List<T>>.listMap(transform: (T) -> R) = map { it.map(transform) }

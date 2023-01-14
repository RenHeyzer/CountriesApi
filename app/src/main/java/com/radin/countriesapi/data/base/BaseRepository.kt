package com.radin.countriesapi.data.base

import com.radin.countriesapi.domain.either.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    /**
     * Do network request
     *
     * @return result in [flow] with [Either]
     */
    protected fun <T> doRequest(request: suspend () -> T) = flow<Either<String, T>> {
        emit(Either.Right(data = request()))
    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Either.Left(message = exception.localizedMessage ?: "Error Occurred!"))
    }
}
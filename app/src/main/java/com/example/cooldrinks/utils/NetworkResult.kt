package com.example.cooldrinks.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

fun <TRemote: Any, TResult: Any> networkResult(
    networkCall: suspend () -> TRemote,
    mapRemoteToResult: (data: TRemote) -> TResult,
    coroutineContext: CoroutineContext
): Flow<Resource<TResult>> = flow<Resource<TResult>> {
    val remoteData = coroutineContext.run {
        networkCall.invoke()
    }
    val returnData = mapRemoteToResult.invoke(remoteData)

    emit(Resource.Success(returnData))
}.catch { e ->
    emit(Resource.Error(e))
}.flowOn(Dispatchers.IO)
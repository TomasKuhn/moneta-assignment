package cz.kuhnt.moneta.assignment.library.networking.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiHandler {

    fun <DTO, MODEL> request(
        callApi: suspend () -> Response<DTO>,
        parseDto: DTO.() -> MODEL,
    ): Flow<Data<MODEL>> = flow {
        emit(Data.Loading)

        emit(requestSynchronous(callApi, parseDto))
    }

    suspend fun <DTO, MODEL> requestSynchronous(
        callApi: suspend () -> Response<DTO>,
        parseDto: (DTO.() -> MODEL),
    ): Data<MODEL> {
        val response: Response<DTO> = when (val result = executeRequest(callApi)) {
            is Data.Success -> result.value
            is Data.Error -> return result
            Data.Loading -> return Data.Loading
        }
        return when {
            response.isSuccessful -> processSuccessfulResponse(response, parseDto)
            else -> processUnsuccessfulResponse<DTO, MODEL>(response) as Data.Error
        }
    }

    private suspend fun <DTO> executeRequest(
        callApi: suspend () -> Response<DTO>
    ): Data<Response<DTO>> =
        try {
            Data.Success(withContext(Dispatchers.IO) { callApi() })
        } catch (e: Exception) {
            Data.Error(e)
        }

    private suspend fun <DTO, MODEL> processSuccessfulResponse(
        response: Response<DTO>,
        parseDto: DTO.() -> MODEL
    ): Data<MODEL> = withContext(Dispatchers.IO) {
        response.body()?.let { responseBody ->
            try {
                responseBody.parseDto().let { model ->
                    Data.Success(model)
                }
            } catch (e: Exception) {
                Data.Error(e)
            }
        } ?: Data.Error(
            Exception("API response of ${response.raw().request().method()} ${response.raw().request().url()} doesn't contain any body")
        )
    }

    private suspend fun <DTO, MODEL> processUnsuccessfulResponse(
        response: Response<DTO>,
    ): Data<MODEL> = withContext(Dispatchers.IO) {
        Data.Error(
            Exception(
                "API response of ${response.raw().request().method()} ${response.raw().request().url()} " +
                    "finished with unsuccessful http status code ${response.code()}"
            )
        )
    }
}

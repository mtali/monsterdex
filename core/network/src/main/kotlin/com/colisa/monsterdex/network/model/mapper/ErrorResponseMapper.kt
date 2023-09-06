package com.colisa.monsterdex.network.model.mapper

import com.colisa.monsterdex.network.model.PokemonErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper : ApiErrorModelMapper<PokemonErrorResponse> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): PokemonErrorResponse {
        return PokemonErrorResponse(
            code = apiErrorResponse.statusCode.code,
            message = apiErrorResponse.message()
        )
    }

}
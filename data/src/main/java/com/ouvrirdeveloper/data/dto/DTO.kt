package com.ouvrirdeveloper.data.dto


sealed class Dto(val response: Response, val request: Request) {

    interface Response
    interface Request

    abstract class GetUserList(request: Request, response: Response) : Dto(request, response) {
        data class Request(val id: Long) : Dto.Response
        data class Response(val name: String, val family: String) : Dto.Request
    }
}
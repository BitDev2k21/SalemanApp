package com.saleman.api

import android.util.Log
import com.google.gson.JsonSyntaxException
import dcom.toyourstore.api.ApiException
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        var erroMessage = "Er is iets fout gegaan. Probeer het nog een keer"
        var jsonObject = JSONObject()
        var response: Response<T>? = null
        try {
            response = call.invoke()
        } catch (e: IllegalStateException) {
            jsonObject.put("code", 0)
            jsonObject.put("errorMessage", erroMessage)
            throw ApiException(jsonObject.toString())
        } catch (e: JsonSyntaxException) {
            jsonObject.put("code", 0)
            jsonObject.put("errorMessage", erroMessage)
            throw ApiException(jsonObject.toString())
        } catch (e: SocketException) {
            jsonObject.put("code", 999)
            jsonObject.put("errorMessage", "Connection fail")
            throw ApiException(jsonObject.toString())
        } catch (e: Exception) {
            jsonObject.put("code", 1111)
            jsonObject.put("errorMessage", "Unknow exaception")
            throw ApiException(jsonObject.toString())
        }
        if (response?.isSuccessful!!) {
            if (response.body() == null) {
                jsonObject.put("code", response.code())
                jsonObject.put("message", "Password changed successfully")
                throw ApiException(jsonObject.toString())
            } else {
                return response.body()!!
            }
        } else if (response?.code() == 401) {
            val jsonObj = JSONObject()
            jsonObj.put("code", 401)
            jsonObject.put("errorMessage", "Un authrised")
            throw ApiException(jsonObj.toString())
        }
        else {
            var jsonObj = JSONObject()
            try {
                jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                throw ApiException("" + jsonObj)
            } catch (e: Exception) {
                jsonObj.put("code", response.code())
                jsonObj.put("errorMessage","something went wrong")
                throw ApiException(jsonObj.toString())
            }
        }
    }


}





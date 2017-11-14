package com.yolasite.hardtapgames.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.yolasite.hardtapgames.smack.Utlities.URL_REGISTER
import org.json.JSONObject

/**
 * Created by ronnie on 13/11/17.
 */
object AuthService {

    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit ){

        val jsonBody = JSONObject()

        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val registerRequest = object : StringRequest(Method.POST, URL_REGISTER, Response.Listener { response ->

            println(response)
            complete(true)


        }, Response.ErrorListener { error ->
            Log.d("ERROR", "could not register user $error")
            complete(false)

        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(registerRequest)

    }

}
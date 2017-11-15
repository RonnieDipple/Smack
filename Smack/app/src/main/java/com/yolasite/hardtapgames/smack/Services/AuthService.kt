package com.yolasite.hardtapgames.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.yolasite.hardtapgames.smack.Utlities.URL_LOGIN
import com.yolasite.hardtapgames.smack.Utlities.URL_REGISTER
import org.json.JSONObject
import java.lang.reflect.Method
import com.android.volley.VolleyError
import com.android.volley.RetryPolicy
import com.android.volley.DefaultRetryPolicy
import org.json.JSONException


/**
 * Created by ronnie on 13/11/17.
 */
object AuthService {
    var isLoggedIn = false
    var userEmail = ""
    var authToken = ""


    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {


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

        registerRequest.retryPolicy = DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)




        Volley.newRequestQueue(context).add(registerRequest)

    }


    fun loginUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {


        val jsonBody = JSONObject()

        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()


        val loginRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN, null, Response.Listener { response ->

            try {
                userEmail = response.getString("user")
                authToken = response.getString("token")
                isLoggedIn = true
                complete(true)

            }catch (e: JSONException ) {

               Log.d("JSON", "EXC:"+e.localizedMessage)
                complete(false)

            }



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

        loginRequest.retryPolicy = DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)


        Volley.newRequestQueue(context).add(loginRequest)


    }


}
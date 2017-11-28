package com.yolasite.hardtapgames.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.yolasite.hardtapgames.smack.Controller.App
import com.yolasite.hardtapgames.smack.Model.Channel
import com.yolasite.hardtapgames.smack.Utlities.URL_GET_CHANNELS
import org.json.JSONException

/**
 * Created by ronnie on 27/11/17.
 */
object MessageService {
    val channels = ArrayList<Channel>()

    fun getChannels(complete: (Boolean) -> Unit){
        val channelsRequest = object : JsonArrayRequest(Method.GET, URL_GET_CHANNELS, null, com.android.volley.Response.Listener { response ->
            try {

                for (x in 0 until response.length()){
                    val channel = response.getJSONObject(x)
                    val name = channel.getString("name")
                    val channelDesc = channel.getString("description")
                    val channelId = channel.getString("_id")

                    val newChannel = Channel(name,channelDesc,channelId)
                    this.channels.add(newChannel)
                }
                complete(true)


            }catch (e: JSONException){
                Log.d("JSON", "EXC:" + e.localizedMessage)
                complete(false)

            }

        },com.android.volley.Response.ErrorListener{ error ->
            Log.d("ERROR", "Could not retrieve channels")
            complete(false)


        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.prefs.authToken}")
                return headers
            }

        }

        App.prefs.requestQueue.add(channelsRequest)


    }

}
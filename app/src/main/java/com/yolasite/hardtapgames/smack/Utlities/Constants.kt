package com.yolasite.hardtapgames.smack.Utlities

/**
 * Created by ronnie on 13/11/17.
 */
const val BASE_URL  = "https://chattyappchatslack.herokuapp.com/v1/"
//const val BASE_URL  = "http://10.0.2.2:3005/v1/"
const val SOCKET_URL  = "https://chattyappchatslack.herokuapp.com/"
const val URL_REGISTER = "${BASE_URL}account/register"
const val URL_LOGIN = "${BASE_URL}account/login"
const val URL_CREATE_USER = "${BASE_URL}user/add"
const val URL_GET_USER = "${BASE_URL}user/byEmail/"
const val URL_GET_CHANNELS = "${BASE_URL}channel/"

// Broadcast constants
const val BROADCAST_USER_DATA_CHANGE = "BROADCAST_USER_DATA_CHANGE"
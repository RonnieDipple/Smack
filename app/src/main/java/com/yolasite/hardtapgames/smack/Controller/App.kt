package com.yolasite.hardtapgames.smack.Controller

import android.app.Application
import com.yolasite.hardtapgames.smack.Utlities.SharedPrefs

/**
 * Created by ronnie on 28/11/17.
 */
class App: Application() {
    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)

        super.onCreate()
    }

}
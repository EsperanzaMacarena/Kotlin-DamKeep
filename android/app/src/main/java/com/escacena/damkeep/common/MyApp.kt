package com.escacena.damkeep.common

import android.app.Application

class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =DaggerApplicationComponent.create()
    }
    fun getAppCompontent(): ApplicationComponent{
        return appComponent
    }
}
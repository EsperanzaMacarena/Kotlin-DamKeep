package com.escacena.damkeep.common

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferencesManager {

    fun getSharedPreferences(): SharedPreferences {
        return  MyApp.instance?.getSharedPreferences(
            Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE
        )
    }

    fun setStringValue(label: String, value: String) {
        var editor: SharedPreferences.Editor = getSharedPreferences().edit()
        editor.putString(label, value)
        editor.commit()
    }

    fun removeStringValue(label: String) {
        var editor: SharedPreferences.Editor = getSharedPreferences().edit()
        editor.remove(label)
        editor.commit()

    }
}
package com.ouvrirdeveloper.core.utils

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class PrefUtil<T>(
    private val key: String,
    private val default: T,
    private val name: String? = null
) : ReadWriteProperty<Any?, T> {

    companion object {
        private const val PREF_NAME = "appPref"

        private lateinit var prefName: String
        private lateinit var context: Context
        private var prefMode: Int = Context.MODE_PRIVATE

        fun init(ctx: Context, name: String = PREF_NAME, mode: Int = Context.MODE_PRIVATE) {
            context = ctx
            prefName = name
            prefMode = mode
        }

        fun clear() {
            val editor: SharedPreferences.Editor = context.getSharedPreferences(
                prefName,
                prefMode
            ).edit()
            editor.clear()
            editor.apply()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val pref = context.getSharedPreferences(
            name ?: prefName,
            prefMode
        )

        return when (default) {
            is Int -> pref.getInt(key, default) as T
            is String -> pref.getString(key, default) as T
            is Boolean -> pref.getBoolean(key, default) as T
            is Float -> pref.getFloat(key, default) as T
            is Long -> pref.getLong(key, default) as T
            else -> throw IllegalArgumentException("LoginData type not supported")
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val editor: SharedPreferences.Editor = context.getSharedPreferences(
            name ?: prefName,
            prefMode
        ).edit()

        when (value) {
            is String -> editor.putString(key, value)
            is Float -> editor.putFloat(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
            else -> throw IllegalArgumentException("${property.name} variable type is not supported yet!!")
        }

        editor.apply()
    }
}
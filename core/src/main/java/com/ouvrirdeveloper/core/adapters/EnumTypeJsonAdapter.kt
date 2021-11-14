package com.ouvrirdeveloper.core.adapters

import com.ouvrirdeveloper.domain.ITypeEnum
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException

class EnumTypeJsonAdapter<T : ITypeEnum<E>, E>
private constructor(
    val eType: Class<E>,
    private val enumType: Class<T>, private val fallbackValue: T?,
    private val useFallbackValue: Boolean
) : JsonAdapter<T>() {
    private val constants = enumType.enumConstants
    private val enumValues: HashMap<E, T> =
        constants?.associateTo(hashMapOf()) { it.type to it } ?: hashMapOf()

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): T? {
        var value: E? = null
        when (eType) {
            String::class.java -> {
                value = reader.nextString() as E
                if (enumValues.containsKey(value))
                    return enumValues[value]
            }
            Int::class.java -> {
                value = reader.nextString() as E
                if (enumValues.containsKey(value))
                    return enumValues[value]
            }
        }


        val path = reader.path
        if (!useFallbackValue) {
            throw JsonDataException("Unknown value of enum ${enumType.name} ($value) at path $path")
        }
        return fallbackValue
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: T?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        when (eType) {
            String::class.java -> {
                writer.value(value.type as String)
            }
            Int::class.java -> {
                writer.value(value.type as Int)
            }
        }

    }

    override fun toString() = "EnumJsonAdapter(" + enumType.name + ")"

    companion object {
        fun <T : ITypeEnum<E>, E> create(
            eType: Class<E>,
            enumType: Class<T>,
            unknownFallback: T? = null
        ): EnumTypeJsonAdapter<T, E> {
            val useFallbackValue = (unknownFallback != null)
            return EnumTypeJsonAdapter(eType, enumType, unknownFallback, useFallbackValue)
        }
    }
}
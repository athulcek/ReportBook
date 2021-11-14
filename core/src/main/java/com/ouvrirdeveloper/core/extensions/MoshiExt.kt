package com.ouvrirdeveloper.core.extensions

import com.ouvrirdeveloper.core.adapters.EnumTypeJsonAdapter
import com.ouvrirdeveloper.domain.ITypeEnum
import com.squareup.moshi.Moshi
import kotlin.reflect.KClass

inline  fun <T : ITypeEnum<E>,reified E : Any> Moshi.Builder.addValueEnum(
    kClass: KClass<T>,
    unknownFallback: T? = null
): Moshi.Builder =
    this.add(kClass.java, EnumTypeJsonAdapter.create(E::class.java,kClass.java, unknownFallback).nullSafe())

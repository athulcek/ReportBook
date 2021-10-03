package com.ouvrirdeveloper.data.helper

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.ouvrirdeveloper.basearc.core.extension.toPreservedByteArray
import com.ouvrirdeveloper.core.extensions.toPreservedString
import java.security.Key
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec


class KeystoreHelper {
    private val androidKeyStore = "AndroidKeyStore"
    private val aesMode = "AES/GCM/NoPadding"
    private val keyAlias = "AppName"

    private var keyStore: KeyStore? = null

    init {
        generateKeyAliasIfRequired()
    }

    fun encryptData(dataToEncrypt: String): Pair<String, String>? {
        return try {
            val encodeValue = Base64.encode(dataToEncrypt.toByteArray(), Base64.DEFAULT)
            val base64String = String(encodeValue)
            aesEncrypt(base64String)
        } catch (ex: Exception) {
            null
        }
    }

    fun decryptData(dataToDecrypt: String, iv: String): String? {
        return try {
            val decryptedData =
                aesDecrypt(dataToDecrypt.toPreservedByteArray(), iv.toPreservedByteArray())
            val decodedValue = Base64.decode(decryptedData.toByteArray(), Base64.DEFAULT)
            String(decodedValue)
        } catch (ex: Exception) {
            null
        }
    }

    private fun generateKeyAliasIfRequired() {
        keyStore = KeyStore.getInstance(androidKeyStore)
        keyStore?.let {
            it.load(null)
            if (!it.containsAlias(keyAlias)) {
                val keyGenerator: KeyGenerator =
                    KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, androidKeyStore)
                keyGenerator.init(
                    KeyGenParameterSpec.Builder(
                        keyAlias,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                    )
                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                        .setRandomizedEncryptionRequired(true)
                        .build()
                )
                keyGenerator.generateKey()
            }
        }
    }

    @Throws(Exception::class)
    private fun getSecretKey(): Key? {
        return keyStore?.getKey(keyAlias, null)
    }

    private fun aesEncrypt(dataToEncrypt: String): Pair<String, String> {
        val c = Cipher.getInstance(aesMode)
        c.init(Cipher.ENCRYPT_MODE, getSecretKey())
        val encodedBytes = c.doFinal(dataToEncrypt.toPreservedByteArray())
        return Pair(encodedBytes.toPreservedString(), c.iv.toPreservedString())
    }

    private fun aesDecrypt(encryptedBytes: ByteArray?, iv: ByteArray): String {
        val c = Cipher.getInstance(aesMode)
        c.init(
            Cipher.DECRYPT_MODE,
            getSecretKey(),
            GCMParameterSpec(128, iv)
        )
        return c.doFinal(encryptedBytes).toPreservedString()
    }
}
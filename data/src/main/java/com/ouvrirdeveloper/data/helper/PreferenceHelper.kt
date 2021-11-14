package com.ouvrirdeveloper.data.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceHelper(private val context: Context) {

    companion object {
        const val kPart1 = "cf9ad3f68ce6db42e3fc3db29be01869768797cabff9bd008dc5a3101f6ff251\n" +
                "e4ac6d504dd601843fab3eb55ff607ea24b32be0b5b63bc5dc56e3322b8a76c2d5a07d0cfbfd9a602b6a09bf056bdd05b57f349dabe37b6dc8f9e81ac9d8ddd05da78270e990eb65fadca59a5b11f6be\n" +
                "ec65000b25272a32a9e47e16ea1a89d239a833d57bce663afcd51ecc1e74e2e30564ab4b83dfc5d46066d56dc20f472fed8ed16fc26bc6b8e24b59433a5c79a8dd2e645b0cdb256b68ec22160e0958f4\n" +
                "4f80d2441443829745fde71c00c3512199396066c756de258ab5fbc55a2702ea0d555e88fff30eb58a1b882047b0c5ecbb10ed0fc7bfd4ffb5371206673a31a707280c4b21a7a734fd8120d2cea46257\n" +
                "b324e8d622355ca8481147b1550dcf1e1d2bad62b91e92ef077c01eb7ede9e75acaa18e76fb9b5300e55c1c0f582f3273d922b18bc9dbba416d937a4ca03a7ef44eb39d01ae6fbad783b69f7947d651b\n" +
                "59b6b0b27b3963ea6946122fa6503715683ed0b280391cf94d9486370565f4c430505af81527cf2567574ba238ef8d8057752e0ad2b07febc1447108710217ccebdc16dfc4d58a78da4c9d32e7d4f08f\n" +
                "0274c822e36eb8d85d22f205543f331a9b55af012522d686c1eef3baf7f619215696f5654b20b8129f18288d1b291012969a213bca8db8589110e08f8d4b89af0b9c80a13e0e821f9fd76102415da716\n" +
                "d4a06cae8770db802ac21ba704c3b3a3a28c80d341b33ba8c78d09d88df78c056d983ee07ac26b7e2c128aeca71618ebcb45be6bcb82561594f5af693c26f6a9b75e805e29672c9b9d7e2b077fc92efb\n" +
                "549698a4e3edcd5eb795ea3f6f48f0d2eb42874c7b556ff5a3e35bf7b742f394c5ed88e9293a4929303bcd2c1e47a5136146e79cef62bf80768aeb423dcdc599af274cff245dd7ce35bd4623bac2fa0e\n" +
                "8a8e151e276e26b678bd4516d7042b36a01514b3920cb5618e7895e1b9f0f11a5301bc10d68228d3fab008b892021f7f84fffdbef4c248ecb6e26a970db5b01ca666b240e3b7083de753d293dc5f2b3d\n" +
                "dfc9acb111d61c721e52967e39fcbc2fc9ed7b2c975f66e97e0d33c58f1d07af5af18cc94450d3a6bb522928ed69afc774ae4df5896a0334640950cd5ce273223d46272df1996d1af8b5bd31970665dd\n" +
                "9518f1d7e626e286c83fcb86ad627c39c77dff43785d3407168f9af58c4f71cb060acd9e7f7778a04d5c9745bdc9116a8bbe0fca45bbc4e5b3863b2cb2a1e13b1ba12d4ab2b8fff09c73ded788514ac5\n" +
                "aac418791f8577fa93df7da5b507e7328e5434f4f1aa85251d1f5856df7cb7f0cc34c73f6c348476fd03b2016375654e61cbe61f97f2333455f643ee08f308604c3c4af46c55fa7531faffb01a0401d0"
    }

    fun defaultPrefs(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun setPref(key: String, value: Any?) = defaultPrefs().set(key, value)

    inline fun <reified T : Any?> getPref(key: String, defaultValue: T?) = when (T::class) {
        String::class -> defaultPrefs().getString(key, defaultValue as? String ?: "") as T?
        Int::class -> defaultPrefs().getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> defaultPrefs().getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> defaultPrefs().getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> defaultPrefs().getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented")
    }


    inline fun isAvailable(key: String) = defaultPrefs().contains(key)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a value for the given [key].
     */
    operator fun SharedPreferences.set(key: String, value: Any?) = when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     * finds a preference based on the given [key].
     * [T] is the type of value
     * @param defaultValue optional defaultValue - will take a default defaultValue if it is not specified
     */
    inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T? = when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "").let {
            return if (it == "") defaultValue
            else it as T?
        }
        Int::class -> getInt(key, defaultValue as? Int ?: -1).let {
            return if (it == -1) defaultValue
            else it as T?
        }
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false).let {
            return if (!it) defaultValue
            else it as T?
        }
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f).let {
            return if (it == -1f) defaultValue
            else it as T?
        }
        Long::class -> getLong(key, defaultValue as? Long ?: -1).let {
            return if (it == -1L) defaultValue
            else it as T?
        }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}
package com.ouvrirdeveloper.core.network


import com.ouvrirdeveloper.core.extensions.applogd
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory


/**
 * Send a ping to googles primary DNS.
 * If successful, that means we have internet.
 */
object DoesNetworkHaveInternet {

    // Make sure to execute this on a background thread.
    fun execute(socketFactory: SocketFactory): Boolean {
        return try {
            applogd("PINGING google.")
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            applogd("PING success.")
            true
        } catch (e: IOException) {
            applogd("No internet connection. ${e}")
            false
        }
    }
}
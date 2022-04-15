package comnet.proxy

import comnet.routes.telemetry.LiveTelemetryView
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.util.concurrent.ConcurrentLinkedQueue

class ProxyConnector {
    companion object {
        val instance = ProxyConnector()
    }
    private var ready: Boolean = false
    val queue: ConcurrentLinkedQueue<LiveTelemetryView> = ConcurrentLinkedQueue()

    @Volatile
    var lastPacketTimestamp: Long = System.currentTimeMillis()
    @Volatile
    var isProxyConnected: Boolean = false

    @Synchronized
    fun setReadyState(state: Boolean) {
        this.ready = state
    }

    suspend fun start() {
        while(true) {
            delay(3000)
            val socket = kotlin.runCatching {
                aSocket(ActorSelectorManager(Dispatchers.IO)).tcp()
                    .connect(InetSocketAddress("localhost", 5000))
            }.getOrNull() ?: continue
            isProxyConnected = true

            kotlin.runCatching {
                val input = socket.openReadChannel()
                while(true) {
                    val rawData = ByteArray(1024)
                    input.readFully(rawData)
                    lastPacketTimestamp = System.currentTimeMillis()
                    if(ready) {
                        // Process data
                    }
                }
            }.onFailure {
                if(!socket.isClosed) socket.close()
                isProxyConnected = false
            }
        }
    }
}
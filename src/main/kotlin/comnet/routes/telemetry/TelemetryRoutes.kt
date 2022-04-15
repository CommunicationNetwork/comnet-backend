package comnet.routes.telemetry

import comnet.proxy.ProxyConnector
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.utils.io.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay

fun Application.initTelemetryRoutes() {
    routing {
        telemetryRoute()
    }
}

fun Route.telemetryRoute() {
    webSocket("/telemetry") {
        ProxyConnector.instance.setReadyState(true)
        try {
            while(true) {
                delay(500)
                if(ProxyConnector.instance.queue.isEmpty() || !ProxyConnector.instance.isProxyConnected) continue
                sendSerialized(
                    ProxyConnector.instance.queue.poll()
                )
            }
        } catch(_: Exception) {
            ProxyConnector.instance.setReadyState(false)
        }
    }
}
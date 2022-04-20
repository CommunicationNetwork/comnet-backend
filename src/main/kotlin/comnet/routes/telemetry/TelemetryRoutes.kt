package comnet.routes.telemetry

import comnet.proxy.ProxyConnector
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

fun Application.initTelemetryRoutes() {
    routing {
        telemetryRoute()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun Route.telemetryRoute() {
    webSocket("/telemetry") {
        ProxyConnector.instance.setReadyState(true)
        while(!outgoing.isClosedForSend) {
            if(ProxyConnector.instance.queue.isEmpty() || !ProxyConnector.instance.isProxyConnected) continue
            sendSerialized(
                ProxyConnector.instance.queue.poll()
            )
        }
        ProxyConnector.instance.setReadyState(false)
    }
}
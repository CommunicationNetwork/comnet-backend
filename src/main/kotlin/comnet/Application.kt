package comnet

import comnet.proxy.ProxyConnector
import comnet.routes.configureRouting
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) { gson() }
        install(WebSockets)
        install(CORS) {
            anyHost() // TODO: Change
        }
        CoroutineScope(Dispatchers.Default).launch {
            ProxyConnector.instance.start()
        }
        configureRouting()
    }.start(wait = true)
}


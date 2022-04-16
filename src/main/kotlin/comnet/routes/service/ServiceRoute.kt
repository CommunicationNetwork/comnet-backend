package comnet.routes.service

import comnet.proxy.ProxyConnector
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.initServiceRoutes() {
    routing {
        serviceStatusRoute()
    }
}

fun Route.serviceStatusRoute() {
    get("/service/status") {
        call.respond(ServiceStatusResponse(
            true,
            ProxyConnector.instance.isProxyConnected,
            (System.currentTimeMillis() - ProxyConnector.instance.lastPacketTimestamp) < 5000
        ))
    }
}

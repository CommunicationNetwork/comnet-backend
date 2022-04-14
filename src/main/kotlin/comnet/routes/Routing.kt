package comnet.routes

import comnet.routes.service.initServiceRoutes
import comnet.routes.telemetry.initTelemetryRoutes
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        initTelemetryRoutes()
        initServiceRoutes()
    }
}

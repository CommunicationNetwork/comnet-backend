package comnet.routes.telemetry

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*

fun Application.initTelemetryRoutes() {
    routing {
        telemetryRoute()
    }
}

fun Route.telemetryRoute() {
    webSocket("/telemetry") {

    }
}
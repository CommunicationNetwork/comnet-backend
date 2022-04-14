package comnet.routes.telemetry

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.initTelemetryRoutes() {
    routing {
        telemetryRoute()
    }
}

fun Route.telemetryRoute() {
    get("/telemetry") {

    }
}
package comnet.routes.service

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.initServiceRoutes() {
    routing {
        serviceStatusRoute()
    }
}

fun Route.serviceStatusRoute() {
    get("/service/status") {

    }
}

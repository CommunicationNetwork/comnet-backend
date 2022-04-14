package comnet.routes.service

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.initSerivceRoutes() {
    routing {
        serviceStatusRoute()
    }
}

fun Route.serviceStatusRoute() {
    get("/service/status") {

    }
}

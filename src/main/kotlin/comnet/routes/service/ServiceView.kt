package comnet.routes.service

data class ServiceStatusResponse(
    val backend: Boolean,
    val proxy: Boolean,
    val flightComputer: Boolean
)
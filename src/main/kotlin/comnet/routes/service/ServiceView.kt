package comnet.routes.service

data class ServiceStatusView(
    val backend: Boolean,
    val proxy: Boolean,
    val flightComputer: Boolean
)
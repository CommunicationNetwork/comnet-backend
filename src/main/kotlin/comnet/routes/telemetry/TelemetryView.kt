package comnet.routes.telemetry

data class LiveTelemetryResponse(
    val height: Double,
    val velocity: Double,
    val temperature: Double,
    val pressure: Double
)
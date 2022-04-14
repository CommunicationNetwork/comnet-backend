package comnet.routes.telemetry

data class LiveTelemetryView(
    val height: Double,
    val velocity: Double,
    val temperature: Double,
    val pressure: Double
)
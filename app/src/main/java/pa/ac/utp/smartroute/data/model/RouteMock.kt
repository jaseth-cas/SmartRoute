package pa.ac.utp.smartroute.data.model

data class RouteMock(
    val id: Int,
    val name: String,
    val origin: String,
    val destination: String,
    val status: String,
    val etaMinutes: Int?
)
package pa.ac.utp.smartroute.data.model

data class StopMock(
    val id: Int,
    val name: String,
    val reference: String,
    val latitude: Double,
    val longitude: Double,
    val routeId: Int
)
package pa.ac.utp.smartroute.data.model

data class BusMock(
    val id: Int,
    val code: String,
    val plate: String,
    val model: String,
    val capacity: Int,
    val status: String,
    val driverName: String,
    val ownerName: String,
    val routeName: String?,
    val simulatedGpsActive: Boolean
)
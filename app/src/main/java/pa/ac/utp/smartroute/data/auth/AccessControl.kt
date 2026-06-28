package pa.ac.utp.smartroute.data.auth

import pa.ac.utp.smartroute.navigation.SmartRouteDestination

object AccessControl {
    fun hasAccess(role: UserRole?, destination: SmartRouteDestination): Boolean {
        // Public screens
        if (destination in listOf(
                SmartRouteDestination.SPLASH,
                SmartRouteDestination.LOGIN,
                SmartRouteDestination.ROLE_HOME,
                SmartRouteDestination.ACCESS_DENIED
            )
        ) return true

        if (role == null) return false

        return when (role) {
            UserRole.STUDENT -> destination in listOf(
                SmartRouteDestination.STUDENT_HOME,
                SmartRouteDestination.ROUTES,
                SmartRouteDestination.STOPS,
                SmartRouteDestination.SIMULATED_MAP,
                SmartRouteDestination.ETA,
                SmartRouteDestination.NOTIFICATIONS
            )
            UserRole.ADMIN -> destination in listOf(
                SmartRouteDestination.ADMIN_DASHBOARD,
                SmartRouteDestination.MANAGE_ROUTES,
                SmartRouteDestination.MANAGE_STOPS,
                SmartRouteDestination.MANAGE_BUSES
            )
            UserRole.DRIVER -> destination == SmartRouteDestination.DRIVER_HOME
            UserRole.OWNER -> destination == SmartRouteDestination.OWNER_HOME
        }
    }
}
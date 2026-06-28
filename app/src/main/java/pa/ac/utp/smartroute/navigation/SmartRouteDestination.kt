package pa.ac.utp.smartroute.navigation

enum class SmartRouteDestination(val route: String) {
    SPLASH("splash"),
    LOGIN("login"),
    ROLE_HOME("role_home"),
    ACCESS_DENIED("access_denied"),
    STUDENT_HOME("student_home"),
    ROUTES("routes"),
    STOPS("stops"),
    SIMULATED_MAP("simulated_map"),
    ETA("eta"),
    NOTIFICATIONS("notifications"),
    ADMIN_DASHBOARD("admin_dashboard"),
    MANAGE_ROUTES("manage_routes"),
    MANAGE_STOPS("manage_stops"),
    MANAGE_BUSES("manage_buses"),
    DRIVER_HOME("driver_home"),
    OWNER_HOME("owner_home")
}
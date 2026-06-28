package pa.ac.utp.smartroute.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pa.ac.utp.smartroute.data.auth.AccessControl
import pa.ac.utp.smartroute.data.auth.SessionManager
import pa.ac.utp.smartroute.data.auth.UserRole
import pa.ac.utp.smartroute.ui.admin.*
import pa.ac.utp.smartroute.ui.auth.AccessDeniedScreen
import pa.ac.utp.smartroute.ui.auth.RoleHomeScreen
import pa.ac.utp.smartroute.ui.driver.DriverHomeScreen
import pa.ac.utp.smartroute.ui.login.LoginScreen
import pa.ac.utp.smartroute.ui.owner.OwnerHomeScreen
import pa.ac.utp.smartroute.ui.splash.SplashScreen
import pa.ac.utp.smartroute.ui.student.*

@Composable
fun SmartRouteNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SmartRouteDestination.SPLASH.route
    ) {
        // Public
        composable(SmartRouteDestination.SPLASH.route) {
            SplashScreen(onStartClick = {
                navController.navigate(SmartRouteDestination.LOGIN.route)
            })
        }

        composable(SmartRouteDestination.LOGIN.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(SmartRouteDestination.ROLE_HOME.route) {
                    popUpTo(SmartRouteDestination.LOGIN.route) { inclusive = true }
                }
            })
        }

        composable(SmartRouteDestination.ROLE_HOME.route) {
            RoleHomeScreen(
                onNavigateToLogin = {
                    navController.navigate(SmartRouteDestination.LOGIN.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToRoleHome = { role ->
                    val destination = when (role) {
                        UserRole.STUDENT -> SmartRouteDestination.STUDENT_HOME
                        UserRole.ADMIN -> SmartRouteDestination.ADMIN_DASHBOARD
                        UserRole.DRIVER -> SmartRouteDestination.DRIVER_HOME
                        UserRole.OWNER -> SmartRouteDestination.OWNER_HOME
                    }
                    navController.navigate(destination.route)
                }
            )
        }

        composable(SmartRouteDestination.ACCESS_DENIED.route) {
            AccessDeniedScreen(
                onNavigateHome = {
                    navController.navigate(SmartRouteDestination.ROLE_HOME.route)
                },
                onNavigateToLogin = {
                    navController.navigate(SmartRouteDestination.LOGIN.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        // Student
        protectedComposable(navController, SmartRouteDestination.STUDENT_HOME) {
            StudentHomeScreen(
                onNavigate = { route -> navController.navigate(route) },
                onLogout = { performLogout(navController) }
            )
        }

        protectedComposable(navController, SmartRouteDestination.ROUTES) {
            RoutesScreen(
                onNavigateBack = { navController.popBackStack() },
                onViewStops = { routeId -> navController.navigate("stops") } // Simplified for now
            )
        }

        protectedComposable(navController, SmartRouteDestination.STOPS) {
            StopsScreen(
                routeId = 1, // Simulated
                onNavigateBack = { navController.popBackStack() },
                onSelectStop = { stopId -> navController.navigate(SmartRouteDestination.SIMULATED_MAP.route) }
            )
        }

        protectedComposable(navController, SmartRouteDestination.SIMULATED_MAP) {
            SimulatedMapScreen(
                onNavigateBack = { navController.popBackStack() },
                onConsultEta = { navController.navigate(SmartRouteDestination.ETA.route) }
            )
        }

        protectedComposable(navController, SmartRouteDestination.ETA) {
            EtaScreen(
                onNavigateBack = { navController.popBackStack() },
                onViewMap = { navController.navigate(SmartRouteDestination.SIMULATED_MAP.route) }
            )
        }

        protectedComposable(navController, SmartRouteDestination.NOTIFICATIONS) {
            NotificationsScreen(onNavigateBack = { navController.popBackStack() })
        }

        // Admin
        protectedComposable(navController, SmartRouteDestination.ADMIN_DASHBOARD) {
            AdminDashboardScreen(
                onNavigate = { route -> navController.navigate(route) },
                onLogout = { performLogout(navController) }
            )
        }

        protectedComposable(navController, SmartRouteDestination.MANAGE_ROUTES) {
            ManageRoutesScreen(onNavigateBack = { navController.popBackStack() })
        }

        protectedComposable(navController, SmartRouteDestination.MANAGE_STOPS) {
            ManageStopsScreen(onNavigateBack = { navController.popBackStack() })
        }

        protectedComposable(navController, SmartRouteDestination.MANAGE_BUSES) {
            ManageBusesScreen(onNavigateBack = { navController.popBackStack() })
        }

        // Driver
        protectedComposable(navController, SmartRouteDestination.DRIVER_HOME) {
            DriverHomeScreen(onLogout = { performLogout(navController) })
        }

        // Owner
        protectedComposable(navController, SmartRouteDestination.OWNER_HOME) {
            OwnerHomeScreen(onLogout = { performLogout(navController) })
        }
    }
}

private fun androidx.navigation.NavGraphBuilder.protectedComposable(
    navController: NavHostController,
    destination: SmartRouteDestination,
    content: @Composable () -> Unit
) {
    composable(destination.route) {
        val role = SessionManager.getCurrentRole()
        if (SessionManager.isLoggedIn()) {
            if (AccessControl.hasAccess(role, destination)) {
                content()
            } else {
                navController.navigate(SmartRouteDestination.ACCESS_DENIED.route)
            }
        } else {
            navController.navigate(SmartRouteDestination.LOGIN.route) {
                popUpTo(0)
            }
        }
    }
}

private fun performLogout(navController: NavHostController) {
    SessionManager.logout()
    navController.navigate(SmartRouteDestination.LOGIN.route) {
        popUpTo(0)
    }
}
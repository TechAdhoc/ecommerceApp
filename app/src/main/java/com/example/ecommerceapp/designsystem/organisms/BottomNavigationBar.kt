package com.example.ecommerceapp.designsystem.organisms

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecommerceapp.design.Theme

/**
 * Data class to define items in the bottom navigation bar
 */
data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector = selectedIcon,
    val badgeCount: Int? = null
)

/**
 * Bottom navigation bar organism that handles navigation between main sections of the app
 */
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.primary,
    contentColor: Color = Theme.onPrimary
) {
    NavigationBar(
        modifier = modifier,
        containerColor = backgroundColor,
        contentColor = contentColor,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            AddItem(
                screen = item,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

/**
 * Helper function to add an item to the bottom navigation bar
 */
@Composable
private fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    
    NavigationBarItem(
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                // Pop up to the start destination of the graph to avoid building up
                // a large stack of destinations
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination
                launchSingleTop = true
                // Restore state when navigating back
                restoreState = true
            }
        },
        icon = {
            Icon(
                imageVector = if (selected) screen.selectedIcon else screen.unselectedIcon,
                contentDescription = screen.title
            )
        },
        label = {
            Text(
                text = screen.title,
                style = Theme.bodySmall,
                textAlign = TextAlign.Center
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Theme.onPrimary,
            unselectedIconColor = Theme.onPrimary.copy(alpha = 0.6f),
            selectedTextColor = Theme.onPrimary,
            unselectedTextColor = Theme.onPrimary.copy(alpha = 0.6f),
            indicatorColor = Theme.primaryContainer
        )
    )
}

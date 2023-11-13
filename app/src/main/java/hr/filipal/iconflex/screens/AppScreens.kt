package hr.filipal.iconflex.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.filipal.iconflex.viewmodels.AppsViewModel
import androidx.compose.ui.Modifier
import hr.filipal.iconflex.AppDestinations.APPS_ROUTE

@Composable
fun AppsScreen(appsViewModel: AppsViewModel = viewModel()) {
    // Ovdje koristite AppsListScreen za prikaz liste aplikacija
    AppsListScreen(appsViewModel)
}

@Composable
fun WidgetsScreen() {
    Text(text = "Ovo je ekran za widgete")
}

@Composable
fun SettingsScreen() {
    Text(text = "Ovo je ekran za postavke")
}

@Composable
fun BodyContent(paddingValues: PaddingValues, navController: NavHostController, searchQuery: String) {
    val appsViewModel: AppsViewModel = viewModel()
    Column(modifier = Modifier.padding(paddingValues)) {
        NavHost(navController = navController, startDestination = APPS_ROUTE) {
            composable(APPS_ROUTE) { AppsScreen(appsViewModel) }
            // Ostale rute...
        }
    }
}

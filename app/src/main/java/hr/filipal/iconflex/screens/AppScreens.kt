package hr.filipal.iconflex.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hr.filipal.iconflex.AppDestinations.APPS_ROUTE
import hr.filipal.iconflex.viewmodels.AppsViewModel



@Composable
fun AppsScreen(appsViewModel: AppsViewModel = viewModel()) {
    var searchQuery by remember { mutableStateOf("") }

    // UI za unos pretrage
    TextField(
        value = searchQuery,
        onValueChange = { query ->
            searchQuery = query
            appsViewModel.onSearchQueryChanged(query)
        },
        label = { Text("Pretraga") }
    )

    // Prikaz liste aplikacija
    AppsListScreen(appsViewModel)
}

@Composable
fun WidgetsScreen() {
    Text(text = "Ovo je ekran za widgete")
}

@Composable
fun BodyContent(paddingValues: PaddingValues, navController: NavHostController) {
    val appsViewModel: AppsViewModel = viewModel()
    Column(modifier = Modifier.padding(paddingValues)) {
        NavHost(navController = navController, startDestination = APPS_ROUTE) {
            composable(APPS_ROUTE) { AppsScreen(appsViewModel) }
            // Ostale rute...
        }
    }
}

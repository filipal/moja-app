package hr.filipal.iconflex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.filipal.iconflex.screens.*

class MainActivity : ComponentActivity() {
    // Instanciranje ViewModel-a
    // private val appsViewModel by viewModels<AppsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLauncherTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun CustomLauncherTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(), // Možete koristiti darkColorScheme() za tamnu temu
        typography = Typography(),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Custom Launcher") }) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "apps",
            modifier = Modifier.padding(innerPadding) // Dodajte ovu liniju
        ) {
            composable("apps") { AppsListScreen() }
            composable("widgets") { WidgetsScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        label = { Text("Search") },
        singleLine = true,
        keyboardActions = KeyboardActions(onSearch = { onSearch(searchText) })
    )
}

@Composable
fun AppsListScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column {
        SearchBar(onSearch = { query -> searchQuery = query })
        // Ovdje implementirajte logiku za prikaz filtriranih aplikacija na temelju searchQuery
    }
}




// Ostale komponente i funkcije...



/* @Composable
fun MainScreen(appsViewModel: AppsViewModel) {
    val navController = rememberNavController()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = { SearchBar(query = searchQuery, onQueryChanged = { searchQuery = it }) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        BodyContent(innerPadding, navController, searchQuery, appsViewModel)
    }
}
@Composable
fun CustomLauncherTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(), // Možete koristiti darkColorScheme() za tamnu temu
        typography = Typography(),
        content = content
    )
}
@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = query,
                onValueChange = onQueryChanged,
                placeholder = { Text("Search apps") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.navigation_apps)) },
            selected = currentRoute == APPS_ROUTE,
            onClick = {
                if (currentRoute != APPS_ROUTE) {
                    navController.navigate(APPS_ROUTE)
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Widgets, contentDescription = null) },
            label = { Text(stringResource(R.string.navigation_widgets)) },
            selected = currentRoute == WIDGETS_ROUTE,
            onClick = {
                if (currentRoute != WIDGETS_ROUTE) {
                    navController.navigate(WIDGETS_ROUTE)
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
            label = { Text(stringResource(R.string.navigation_settings)) },
            selected = currentRoute == SETTINGS_ROUTE,
            onClick = {
                if (currentRoute != SETTINGS_ROUTE) {
                    navController.navigate(SETTINGS_ROUTE)
                }
            }
        )
    }
}

@Composable
fun BodyContent(paddingValues: PaddingValues, navController: NavHostController, searchQuery: String, appsViewModel: AppsViewModel) {
    Column(modifier = Modifier.padding(paddingValues)) {
        NavHost(navController = navController, startDestination = APPS_ROUTE) {
            composable(APPS_ROUTE) { AppsListScreen(appsViewModel) }
            composable(WIDGETS_ROUTE) { WidgetsScreen() }
            composable(SETTINGS_ROUTE) { SettingsScreen() }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IconFlexTheme {
        // Kreirajte mock instancu AppsViewModel ili koristite neki drugi mehanizam za pružanje podataka
        MainScreen(mockAppsViewModel)
    }
}

@Composable
fun SomeComposableFunction() {
    // Pretpostavljamo da imate instancu AppsViewModel
    val appsViewModel = remember { AppsViewModel() }
    AppsScreen(appsViewModel)
} */
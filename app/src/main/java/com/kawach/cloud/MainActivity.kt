package com.kawach.cloud

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kawach.cloud.ui.theme.KawachCloudTheme

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Home : Screen("home")
    data object Files : Screen("files")
    data object Search : Screen("search")
    data object Settings : Screen("settings")
    data object About : Screen("about")
}

@Composable
fun KawachCloudApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    KawachCloudTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Files.route) },
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Create new file")
                }
            },
            bottomBar = {
                NavigationBar {
                    listOf(
                        Screen.Home to Icons.Default.Home,
                        Screen.Files to Icons.Default.Lock,
                        Screen.Search to Icons.Default.Search,
                        Screen.Settings to Icons.Default.Settings
                    ).forEach { (screen, icon) ->
                        NavigationBarItem(
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(Screen.Home.route) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(icon, contentDescription = screen.route) },
                            label = { Text(screen.route.replaceFirstChar { it.uppercase() }) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Welcome.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Welcome.route) {
                    WelcomeScreen(
                        onContinue = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Welcome.route) { inclusive = true }
                            }
                        }
                    )
                }
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.Files.route) { FilesScreen() }
                composable(Screen.Search.route) { SearchScreen() }
                composable(Screen.Settings.route) {
                    SettingsScreen(
                        onAboutClick = { navController.navigate(Screen.About.route) }
                    )
                }
                composable(Screen.About.route) { AboutScreen() }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onContinue: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = "Kawach_Cloud",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "Private cloud storage powered by your Telegram account.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )

            Text(text = "Features:", style = MaterialTheme.typography.titleLarge)
            listOf("Private", "Encrypted", "Open source").forEach { feature ->
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                ) {
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                        Text(feature)
                    }
                }
            }

            Button(
                onClick = onContinue,
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 16.dp)
            ) {
                Text("Continue with Telegram")
            }

            Text(
                text = "Privacy information and open-source information are available in-app.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text("Kawach_Cloud", style = MaterialTheme.typography.headlineMedium)
        Text("Account information", style = MaterialTheme.typography.titleMedium)

        Card(
            modifier = Modifier.fillMaxSize(0.95f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
        ) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Quick actions")
                listOf("Upload", "New Folder", "Photos", "Videos", "Documents").forEach { action ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f)),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Box(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                            Text(action)
                        }
                    }
                }
            }
        }

        Text("Recent Files", style = MaterialTheme.typography.titleMedium)
        listOf(
            "encrypted-photo.enc" to "Photo • 3.2 MB",
            "notes.txt" to "Document • 120 KB",
            "archive.zip" to "Archive • 8.1 MB"
        ).forEach { (name, details) ->
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(name)
                    Text(details, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                }
            }
        }
    }
}

@Composable
fun FilesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Files", style = MaterialTheme.typography.headlineMedium)
        Text("Grid view • List view • Search • Sorting", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
        repeat(4) { item ->
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text("Folder / File item ${item + 1}")
                }
            }
        }
    }
}

@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("Search", style = MaterialTheme.typography.headlineMedium)
        Text("Local application search", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Text("Search files, folders, and metadata")
            }
        }
    }
}

@Composable
fun SettingsScreen(onAboutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        listOf("Account", "Appearance", "Security", "Storage", "Privacy", "About", "Logout").forEach { item ->
            Card(
                onClick = if (item == "About") onAboutClick else { {} },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
            ) {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
                    Text(item)
                }
            }
        }
    }
}

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Kawach_Cloud", style = MaterialTheme.typography.headlineMedium)
        Text("Version:\n1.0.0", style = MaterialTheme.typography.bodyLarge)
        Text("Developer:\nAravind(guru)", style = MaterialTheme.typography.bodyLarge)
        Text("Email:\ndarkwebaccess404@gmail.com", style = MaterialTheme.typography.bodyLarge)
        Text("Open Source", style = MaterialTheme.typography.titleMedium)
        Text("Privacy-focused cloud storage")
        Text("Telegram / TDLib attribution")
        Text("Third-party libraries")
        Text("Licenses")
        Text("Privacy Policy")
        Text("GitHub repository link placeholder")
    }
}

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KawachCloudApp()
        }
    }
}

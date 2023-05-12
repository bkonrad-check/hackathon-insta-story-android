package de.check24.hackathon.instagramstory

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.check24.hackathon.instagramstory.pages.home.HomeScreen
import de.check24.hackathon.instagramstory.pages.player.PlayerScreen
import de.check24.hackathon.instagramstory.pages.story.StoryScreen
import de.check24.hackathon.instagramstory.ui.theme.InstagramStoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramStoryTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                onNavigateToStory = { navController.navigate("story") },
                                onNavigateToPlayer = { navController.navigate("player") },
                            )
                        }
                        composable("story") { StoryScreen() }
                        composable("player") { PlayerScreen() }
                    }
                }
            }
        }
    }
}

package de.check24.hackathon.instagramstory

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.navigation.StoryParamType
import de.check24.hackathon.instagramstory.pages.home.HomeScreen
import de.check24.hackathon.instagramstory.pages.story.StoryScreen
import de.check24.hackathon.instagramstory.ui.theme.InstagramStoryTheme

@UnstableApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramStoryTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                onNavigateToStory = {
                                    val json = Uri.encode(Gson().toJson(it))
                                    navController.navigate("story$json") }
                            )
                        }
                        composable(
                            "story/{story}",
                            arguments = listOf(
                                navArgument("story") {
                                    type = StoryParamType()
                                }
                            )
                        ) {
                            val story = it.arguments?.getParcelable<Story>("story")!!
                            StoryScreen(story = story)
                        }

                    }
                }
            }
        }
    }
}

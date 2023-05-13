package de.check24.hackathon.instagramstory

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.gson.Gson
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.navigation.StoryParamType
import de.check24.hackathon.instagramstory.pages.home.HomeScreen
import de.check24.hackathon.instagramstory.pages.story.StoryScreen
import de.check24.hackathon.instagramstory.ui.theme.InstagramStoryTheme

@UnstableApi
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramStoryTheme {
                val navController = rememberAnimatedNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedNavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                onNavigateToStory = {
                                    val json = Uri.encode(Gson().toJson(it))
                                    navController.navigate("story/$json")
                                }
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
                            StoryScreen(story = story, onNavigateToStory = {
                                val json = Uri.encode(Gson().toJson(it))
                                navController.navigate("story/$json")
                            }, onBackPressed = {
                                navController.popBackStack(
                                    navController.graph.startDestinationId,
                                    inclusive = false
                                )
                            })
                        }

                    }
                }
            }
        }
    }
}

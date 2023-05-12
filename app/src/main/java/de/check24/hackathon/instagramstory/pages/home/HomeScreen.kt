package de.check24.hackathon.instagramstory.pages.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.pages.home.ui.SubscriptionView

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), onNavigateToStory: () -> Unit) {
    Column {
        SubscriptionView()
        Button(onClick = onNavigateToStory) {
            Text(text = "See stories")

        }
    }

}
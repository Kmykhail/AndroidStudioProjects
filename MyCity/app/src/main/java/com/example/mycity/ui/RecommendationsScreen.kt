package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.model.Place
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mycity.data.CategoriesDataProvider
import com.example.mycity.model.CategoryType
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun RecommendationsScreen(
    uiState: MyCityUiState,
    viewModel: MyCityViewModel,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {
        RecommendationList(
            recommendations = uiState.currentRecommendationList,
            onClick = {place ->
                viewModel.updateCurrentPlace(place)
            },
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        )
        PlaceScreen(
            place = uiState.currentPlace,
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding()
            ),
            modifier = Modifier
                .weight(3f)
        )
    }
}

@Composable
fun RecommendationList(
    recommendations: List<Place>,
    onClick: (Place) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(recommendations) {item ->
            RecommendationItem(
                place = item,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecommendationItem(
    place: Place,
    onItemClick: (Place) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        onClick = { onItemClick(place) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(64.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
            Box(
                modifier = Modifier.size(64.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillWidth
                )
            }
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 2.dp,
                        horizontal = 16.dp
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(place.name),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        append("Rating: ${place.rating}\n")
                        append("Open: ${if (place.isOpen) "Yes" else "No"}\n")
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}
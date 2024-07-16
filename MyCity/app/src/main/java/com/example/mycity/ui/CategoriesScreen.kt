package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.model.CategoryType

@Composable
fun CategoriesScreen(
    uiState: MyCityUiState,
    viewModel: MyCityViewModel,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CategoryList(
            categories = uiState.categoryList,
            onClick = {categoryType ->
                viewModel.prepareRecommendationByCategory(categoryType)
            },
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        )
        RecommendationList(
            recommendations = uiState.currentRecommendationList,
            onClick = {place ->
                viewModel.updateCurrentPlace(place)
                viewModel.updateCurrentScreen(MyCityScreen.Recommendations)
            },
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(3f)
        )
    }
}

@Composable
fun CategoryList(
    categories: List<CategoryType>,
    onClick: (CategoryType) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(categories) {item ->
            CategoryItem(
                categoryType = item,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun CategoryItem(
    categoryType: CategoryType,
    onItemClick: (CategoryType) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        onClick = { onItemClick(categoryType) },
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(categoryType.title))
        }
    }
}
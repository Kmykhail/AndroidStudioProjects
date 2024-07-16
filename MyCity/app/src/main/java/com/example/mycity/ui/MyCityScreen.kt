package com.example.mycity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.utils.ContentType

enum class MyCityScreen(@StringRes val title: Int) {
    Categories(title = R.string.choose_category),
    Recommendations(title = R.string.choose_recommendation),
    Place(title = R.string.recommended_place)
}
@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack by viewModel.canNavigateBack.collectAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name
    )
    val title = when (currentScreen) {
        MyCityScreen.Place -> stringResource(R.string.recommended_place, stringResource(uiState.currentPlace.name))
        else -> stringResource(currentScreen.title)
    }

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> ContentType.ListOnly
        WindowWidthSizeClass.Expanded -> ContentType.ListAndDetail
        else -> ContentType.ListOnly
    }

    Scaffold(
        topBar = {
            MyCityAppBar(
                titleName = title,
                canNavigateBack = if (contentType == ContentType.ListOnly) navController.previousBackStackEntry != null else canNavigateBack,
                navigateUp = {
                    if (contentType ==ContentType.ListOnly) {
                        navController.navigateUp()
                    } else {
                        when (uiState.currentScreen) {
                            MyCityScreen.Recommendations -> {
                                viewModel.updateCurrentScreen(MyCityScreen.Categories)
                            }
                            MyCityScreen.Place -> {
                                viewModel.updateCurrentScreen(MyCityScreen.Recommendations)
                            }
                            else -> Unit
                        }
                    }
                },
                isShowingListPage = uiState.isShowingListPage
            )
        }
    ) {innerPadding ->
        if (contentType == ContentType.ListOnly) {
            NavHost(
                navController = navController,
                startDestination = if (uiState.currentScreen !== MyCityScreen.Categories) uiState.currentScreen.name else MyCityScreen.Categories.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable(route = MyCityScreen.Categories.name) {
                    CategoryList(
                        categories = uiState.categoryList,
                        onClick = {categoryType ->
                            viewModel.prepareRecommendationByCategory(categoryType)
                            navController.navigate(MyCityScreen.Recommendations.name)
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
                composable(route = MyCityScreen.Recommendations.name) {
                    RecommendationList(
                        recommendations = uiState.currentRecommendationList,
                        onClick ={place ->
                            viewModel.updateCurrentPlace(place)
                            navController.navigate(MyCityScreen.Place.name)
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
                composable(route = MyCityScreen.Place.name) {
                    PlaceScreen(
                        place = uiState.currentPlace,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        } else {
            when(uiState.currentScreen) {
                MyCityScreen.Categories -> {
                    viewModel.updateCanNavigateBack(false)
                    CategoriesScreen(
                        uiState = uiState,
                        viewModel = viewModel,
                        contentPadding = innerPadding
                    )
                }
                MyCityScreen.Recommendations -> {
                    viewModel.updateCanNavigateBack(true)
                    RecommendationsScreen(
                        uiState = uiState,
                        viewModel = viewModel,
                        contentPadding = innerPadding
                    )
                }
                MyCityScreen.Place -> {
                    viewModel.updateCanNavigateBack(true)
                    PlaceScreen(
                        place = uiState.currentPlace,
                        contentPadding = innerPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    titleName: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleName,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}

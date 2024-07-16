package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.CategoriesDataProvider
import com.example.mycity.model.CategoryType
import com.example.mycity.model.Dummy
import com.example.mycity.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        MyCityUiState(
            categoryList = CategoriesDataProvider.getCategoryTypeList(),
            currentCategory = CategoriesDataProvider.getCategoryTypeList().firstOrNull(),
            currentRecommendationList = CategoriesDataProvider.getRecommendationListByCategory(CategoryType.COFFEE_HOUSE)
        )
    )
    private val _canNavigateBack = MutableStateFlow(false)

    val uiState: StateFlow<MyCityUiState> = _uiState
    val canNavigateBack: StateFlow<Boolean> = _canNavigateBack

    fun updateCanNavigateBack(canNavigateBack: Boolean) {
        _canNavigateBack.value = canNavigateBack
    }
    fun prepareRecommendationByCategory(selectedCategory: CategoryType) {
        _uiState.update {
            it.copy(
                currentRecommendationList = CategoriesDataProvider.getRecommendationListByCategory(selectedCategory) ?: emptyList(),
            )
        }
        if (_uiState.value.currentRecommendationList.isNotEmpty()) {
            updateCurrentPlace(_uiState.value.currentRecommendationList[0])
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }

    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun updateCurrentScreen(currentScreen: MyCityScreen) {
        _uiState.update {
            it.copy(currentScreen = currentScreen)
        }
    }
}

data class MyCityUiState(
    val categoryList: List<CategoryType> = emptyList(),
    val currentCategory: CategoryType? = null,
    val currentRecommendationList: List<Place> = emptyList(),
    val currentPlace: Place = Dummy(),
    val isShowingListPage: Boolean = true,
    val currentScreen: MyCityScreen = MyCityScreen.Categories
)
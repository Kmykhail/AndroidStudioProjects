package com.example.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.data.CategoriesDataProvider
import com.example.mycity.model.CategoryType
import com.example.mycity.model.Dummy
import com.example.mycity.model.Place
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun PlaceScreen(
    place: Place,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }

    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current

    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Box {
                    Image(
                        painter = painterResource(place.imageRes),
                        contentDescription = stringResource(place.name),
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ClickableText(
                    text = buildAnnotatedString {
                        append("Address: ")
                        pushStringAnnotation(
                            tag = "URL",
                            annotation = place.googleMapLink ?: ""
                        )
                        withStyle(
                            SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline,
                            )
                        ) {
                            append("${stringResource(place.name)}")
                        }
                    },
                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                    onClick = {
                        println("Clicked in URL")
                        // add switch to google maps
                    }
                )
                Text(
                    text = buildAnnotatedString {
                        append("Rating: ${place.rating}\n")
                        append("Open: ${if (place.isOpen) "Yes" else "No"}\n")
                    },
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = stringResource(place.shortInfo),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

    }
}

@Preview
@Composable
fun PlaceScreenPreview() {
    MyCityTheme {
        PlaceScreen(
            place = CategoriesDataProvider.getRecommendationListByCategory(CategoryType.COFFEE_HOUSE)?.get(0) ?: Dummy()
        )
    }
}
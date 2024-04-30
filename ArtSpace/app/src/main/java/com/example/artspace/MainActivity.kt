package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

data class ArtworkInfo(val paintId: Int,
                       val paintingTitle: Int,
                       val ownerOfPainting: Int,
                       val paintingReleaseData: Int)

@Composable
fun rememberArtworkInfoList(): List<ArtworkInfo> {
    val artworkInfoList = remember {
        val mutableList = mutableListOf<ArtworkInfo>()
        mutableList.add(ArtworkInfo(R.drawable.the_cafe_terrace_at_night_1888_1140x1420, R.string.painting_title_2, R.string.owner_of_painting_2, R.string.release_data_for_painting_2))
        mutableList.add(ArtworkInfo(R.drawable.the_bedroom_1888, R.string.painting_title_3, R.string.owner_of_painting_3, R.string.release_data_for_painting_3))
        mutableList.add(ArtworkInfo(R.drawable.the_night_cafe_1888, R.string.painting_title_4, R.string.owner_of_painting_4, R.string.release_data_for_painting_4))
        mutableList.add(ArtworkInfo(R.drawable.starry_night_over_the_rhone_1888, R.string.painting_title_5, R.string.owner_of_painting_5, R.string.release_data_for_painting_5))
        mutableList.add(ArtworkInfo(R.drawable.sunflowers_series_1888_1140x1438, R.string.painting_title_1, R.string.owner_of_painting_1, R.string.release_data_for_painting_1))
        mutableList
    }
    return artworkInfoList
}
@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    val artWorkInfoList = rememberArtworkInfoList()
    var artData by remember { mutableIntStateOf(0) }

    when {
        artData >= artWorkInfoList.size -> {
            artData = 0
        }
        artData < 0 -> {
            artData = artWorkInfoList.size - 1
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(all = 15.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .padding(bottom = 50.dp)
                .height(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(all = 10.dp)
            ) {
                Image(
                    painter = painterResource(artWorkInfoList[artData].paintId),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(bottom = 25.dp)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Light, fontSize = 24.sp)
                    ) {
                        append(stringResource(artWorkInfoList[artData].paintingTitle))
                    }
                    appendLine()

                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    ) {
                        append(stringResource(artWorkInfoList[artData].ownerOfPainting))
                    }

                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Light, fontSize = 16.sp)
                    ) {
                        append(" (" + stringResource(artWorkInfoList[artData].paintingReleaseData) + ")")
                    }
                }
            )
        }

        Row(
            modifier = Modifier.padding(top = 5.dp),
        ) {
            Button(
                onClick = { artData++ },
                modifier = Modifier.size(width = 120.dp, height = 40.dp)
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { artData-- },
                modifier = Modifier.size(width = 120.dp, height = 40.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}
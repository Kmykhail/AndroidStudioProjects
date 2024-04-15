package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeCyclePreview()
            }
        }
    }
}

@Composable
fun LemonadeCycle(modifier: Modifier = Modifier) {
    var step by remember { mutableIntStateOf(1) }
    var squeezeNum by remember { mutableIntStateOf(0) }

    when (step) {
        1 -> {
            LemonTextAndImage(
                stringResource(R.string.LemonTree),
                painterResource(R.drawable.lemon_tree),
                onClickCalled = {
                    step = 2
                    squeezeNum = (2..4).random()
                }
            )
        }
        2 -> {
            LemonTextAndImage(
                stringResource(R.string.Lemon),
                painterResource(R.drawable.lemon_squeeze),
                onClickCalled = {
                    println("squeezeNum is $squeezeNum")
                    if (--squeezeNum == 0) {
                        step = 3
                    }
                }
            )
        }
        3 -> {
            LemonTextAndImage(
                stringResource(R.string.GalssOfLemonade),
                painterResource(R.drawable.lemon_drink),
                onClickCalled = {
                    step = 4
                }
            )
        }
        else -> {
            LemonTextAndImage(
                stringResource(R.string.EmptyGlass),
                painterResource(R.drawable.lemon_restart),
                onClickCalled = {
                    step = 1
                }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    btnText: String,
    image: Painter,
    onClickCalled: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onClickCalled,
            shape = RoundedCornerShape(38.dp),
            colors = ButtonDefaults.textButtonColors(),
            modifier = Modifier
                .background(color = Color(red = 195, green = 236, blue = 180), shape = RoundedCornerShape(38.dp))
        ) {
            Image(
                painter = image,
                contentDescription = btnText,
                modifier = Modifier
            )
        }
        Text(
            text = btnText,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeCyclePreview() {
    LemonadeCycle(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}
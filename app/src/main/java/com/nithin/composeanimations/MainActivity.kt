package com.nithin.composeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nithin.composeanimations.animations.basicanimations.modifier.AnimateBothAxesScreen
import com.nithin.composeanimations.animations.basicanimations.modifier.AnimateYOffsetScreen
import com.nithin.composeanimations.animations.basicanimations.modifier.CardFlipAnimation
import com.nithin.composeanimations.animations.basicanimations.modifier.OverlappingScreen1
import com.nithin.composeanimations.animations.basicanimations.modifier.PeekAndPopEffectScreen
import com.nithin.composeanimations.animations.basicanimations.modifier.SlidingCardScreen
import com.nithin.composeanimations.animations.basicanimations.modifier.SlidingDrawerScreen
import com.nithin.composeanimations.ui.theme.ComposeAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAnimationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CardFlipAnimation(innerPadding)
//                    Box(
//                        modifier = Modifier.
//                        fillMaxSize().
//                        padding(innerPadding)
//                            .background(color = Color.Black)
//                    ) {
//                        SlidingCardScreen()
//                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeAnimationsTheme {
        Greeting("Android")
    }
}
package com.nithin.composeanimations.animations.basicanimations.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun BasicsModifiers(
    paddingValues: PaddingValues = PaddingValues()
){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Shift the position first
        // Then set the size
        // Finally, apply the background
        Box(
            modifier = Modifier
                .offset(
                    x = 50.dp,
                    y = 50.dp
                )
                .size(50.dp)
                .background(
                    color = Color.Cyan
                )
        )

        // Displaying a simple text with a slight shift to adjust its position
        Text(
            text = "Hello, Compose!",
            modifier = Modifier
                .offset(x = 4.dp, y = (-2).dp) // Slight shift to fine-tune positioning
                .background(Color.LightGray)   // Light gray background for emphasis
                .padding(8.dp)                 // Padding for extra spacing around the text
        )
    }

}

@Preview(showBackground = true)
@Composable
fun OverlappingScreen() {
    Box {
        // Blue Box that is shifted slightly to overlap with the Red Box
        Box(
            modifier = Modifier
                .offset(x = (-10).dp, y = (-10).dp) // Shifting the blue box left and up
                .size(80.dp)                       // Setting the size of the blue box
                .background(Color.Blue)            // Applying a blue background
        )
        // Red Box positioned as the main element
        Box(
            modifier = Modifier
                .size(100.dp)                      // Setting the size of the red box
                .background(Color.Red)             // Applying a red background
        )
    }
}
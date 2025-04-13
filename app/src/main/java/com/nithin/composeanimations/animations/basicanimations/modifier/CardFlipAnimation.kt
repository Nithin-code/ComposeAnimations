package com.nithin.composeanimations.animations.basicanimations.modifier

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview(showBackground = true)

fun CardFlipAnimation(
    paddingValues: PaddingValues = PaddingValues()
){
    var isCardFlipped by remember {
        mutableStateOf(false)
    }

    var rotationAngle = animateFloatAsState(
        targetValue = if (isCardFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )
    var animateColor = animateColorAsState(
        targetValue = if (isCardFlipped) Color.Blue else Color.Red,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .width(300.dp)
                .height(500.dp)
                .graphicsLayer{
                    rotationY = rotationAngle.value
                    cameraDistance = 8 * density
                }
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ).background(
                    color = animateColor.value
                ).clickable{
                    isCardFlipped = !isCardFlipped
                }
        )

    }
}
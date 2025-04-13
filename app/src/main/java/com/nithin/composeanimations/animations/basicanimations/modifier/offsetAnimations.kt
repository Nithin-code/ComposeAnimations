package com.nithin.composeanimations.animations.basicanimations.modifier

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
@Preview(showBackground = true)
fun AnimateXOffset(
    paddingValues: PaddingValues = PaddingValues()
){
    Column(
        modifier = Modifier.padding(paddingValues)
            .background(
                color = Color.Black
            ).fillMaxSize()
    ) {

        var expanded by remember {
            mutableStateOf(false)
        }

        val xOffset = animateDpAsState(
            targetValue = if (expanded) 100.dp else 0.dp,
            animationSpec = tween(
                durationMillis = 1000 // time taken to move from 100.fp to 0.dp (target value)
            )
        )

        Box(
            modifier = Modifier.offset(
                x = xOffset.value
            )
                .size(50.dp)
                .background(
                    color = Color.Cyan
                ).clickable{
                    expanded = !expanded
                }
        )

    }
}

@Composable
fun AnimateYOffsetScreen() {
    var floating by remember { mutableStateOf(false) }
    // Animating the vertical offset based on the floating state
    val offsetY = animateDpAsState(targetValue = if (floating) (-20).dp else 0.dp)

    Box(
        modifier = Modifier
            .offset(y = offsetY.value)           // Applying the animated vertical offset
            .size(60.dp)                         // Setting the size of the box
            .background(Color.Cyan)              // Applying a cyan background
            .clickable { floating = !floating }  // Toggle state on click to animate
    )
}


@Composable
fun AnimateBothAxesScreen() {
    var moved by remember { mutableStateOf(false) }
    // Animating the offset on both X and Y axes based on the moved state
    val offsetX = animateDpAsState(targetValue = if (moved) 50.dp else 0.dp)
    val offsetY = animateDpAsState(targetValue = if (moved) 30.dp else 0.dp)

    Box(
        modifier = Modifier
            .offset(x = offsetX.value, y = offsetY.value) // Applying the animated offsets
            .size(70.dp)                                 // Setting the size of the box
            .background(Color.Magenta)                   // Applying a magenta background
            .clickable { moved = !moved }                // Toggle state on click to animate
    )
}

@Composable
fun SlidingDrawerScreen() {
    var isDrawerOpen by remember { mutableStateOf(true) }
    // Animating the horizontal offset to slide the drawer in and out
    val drawerOffsetX = animateDpAsState(targetValue = if (isDrawerOpen) 0.dp else (-300).dp)

    Box(
        modifier = Modifier
            .offset(x = drawerOffsetX.value)  // Applying the animated offset
            .size(300.dp, 600.dp)             // Setting the size of the drawer
            .background(Color.Red)           // Applying a gray background
            .clickable { isDrawerOpen = !isDrawerOpen } // Toggle state to open/close drawer
    )
}

@Composable
fun PeekAndPopEffectScreen() {
    var isHovered by remember { mutableStateOf(false) }
    // Animating the vertical offset to create a pop effect
    val popOffset = animateDpAsState(targetValue = if (isHovered) 4.dp else 0.dp)

    Box(
        modifier = Modifier
            .offset(y = popOffset.value)      // Applying the animated pop offset
            .size(100.dp)                     // Setting the size of the box
            .background(Color.LightGray)      // Applying a light gray background
            .clickable { isHovered = !isHovered } // Toggle state to animate the pop effect
    )
}

@Composable
fun OverlappingScreen1() {
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

@Composable
fun SlidingCardScreen(){

    var leftCardVisibility by remember {
        mutableStateOf(false)
    }
    var rightCardVisibility by remember {
        mutableStateOf(false)
    }

    var leftCardScale by remember {
        mutableStateOf(1f)
    }
    var rightCardScale by remember {
        mutableStateOf(1f)
    }

    val leftCardRotation = animateFloatAsState(
        targetValue = if (leftCardVisibility) 0f else 30f, // Rotate left card to 30 degrees
        animationSpec = tween(durationMillis = 1500)
    )
    val rightCardRotation = animateFloatAsState(
        targetValue = if (rightCardVisibility) 0f else -30f, // Rotate right card to -30 degrees
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(true) {
        delay(5000)
        leftCardVisibility = true
        rightCardVisibility = true
    }

    val leftCardOffsetX = animateDpAsState(
        targetValue = if (leftCardVisibility) 0.dp else (-1000).dp,
        animationSpec = tween(durationMillis = 1000)
    )

    val rightCardOffsetX = animateDpAsState(
        targetValue = if(rightCardVisibility) 0.dp else 1000.dp,
        animationSpec = tween(durationMillis = 1000)
    )



    Box(
        modifier = Modifier
            .offset(
                x = leftCardOffsetX.value,
                y = (-10).dp
            )
            .fillMaxWidth()
            .height(50.dp)
            .graphicsLayer{
                rotationZ = leftCardRotation.value
                scaleX = leftCardScale
                scaleY = leftCardScale
            }
            .background(Color.Blue)
            .clickable{
                leftCardScale = if (leftCardScale == 1f) 1.2f else 1f
            }
    ) {

    }

    Box(
        modifier = Modifier
            .offset(
                x = rightCardOffsetX.value,
                y = 100.dp
            ).
            height(50.dp).
            fillMaxWidth()
            .graphicsLayer{
                rotationZ = rightCardRotation.value
                scaleX = rightCardScale
                scaleY = rightCardScale
            }
            .background(Color.Red)
            .clickable{
               rightCardScale = if (rightCardScale == 1f) 1.2f else 1f
            }
    ) {

    }

}


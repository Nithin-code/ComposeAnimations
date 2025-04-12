package com.nithin.composeanimations.animations.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp


const val animationDurationMillis = 1000
val intOffTweenSpec : TweenSpec<IntOffset> = tween(durationMillis = animationDurationMillis)

@Composable
@Preview(showBackground = true)
fun BottomSheetScreen(innerPadding: PaddingValues = PaddingValues()) {

    var isButtonClicked by remember { mutableStateOf(false) }

    val colorAnimationSpec : AnimationSpec<Color> = tween(
        durationMillis = animationDurationMillis
    )

    val rotationAnimeSpec : AnimationSpec<Float> = tween(
        durationMillis = animationDurationMillis
    )

    val bgColorAnimation = animateColorAsState(
        targetValue = if (isButtonClicked) Color.White else Color.Red,
        animationSpec = colorAnimationSpec
    )

    val iconColorAnimation = animateColorAsState(
        targetValue = if (isButtonClicked) Color.Black else Color.White,
        animationSpec = colorAnimationSpec
    )

    val rotationAnimation = animateFloatAsState(
        targetValue = if (isButtonClicked) 135f else 0f,
        animationSpec = rotationAnimeSpec
    )



    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding),
        contentAlignment = Alignment.BottomCenter
    ){

        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(color = bgColorAnimation.value)
                .size(45.dp).clickable{
                    isButtonClicked = !isButtonClicked
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = iconColorAnimation.value,
                modifier = Modifier.rotate(rotationAnimation.value)
            )
        }

        BottomSheetDialog(isButtonClicked)

    }



}


@Composable
@Preview()
fun BottomSheetDialog(isButtonClicked : Boolean = false) {

    val colors = listOf(
        Color.White,
        Color.Transparent
    )

    val density = LocalDensity.current

    val config = LocalConfiguration.current

    val screenHeightDp = config.screenHeightDp



    AnimatedVisibility(
        visible = isButtonClicked,
        modifier = Modifier,
        enter = slideInVertically(
            initialOffsetY = {
                screenHeightDp
            },
            animationSpec = intOffTweenSpec
        ),
        exit = slideOutVertically(
            targetOffsetY = {
                with(density) { 250.dp.roundToPx() }
            },
            animationSpec = intOffTweenSpec
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier.fillMaxWidth().height(200.dp)
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ).background(
                    brush = Brush.verticalGradient(
                        colors
                    )
                )
        ) {

            BottomSheetItem(
                icon = Icons.Default.Add,
                text = "ADD PLACE"
            )

            BottomSheetItem(
                icon = Icons.Default.List,
                text = "List"
            )

            BottomSheetItem(
                icon = Icons.Default.Face,
                text = "ADD Friend"
            )

        }
    }


}

@Composable
fun BottomSheetItem(
    icon: ImageVector,
    text: String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.size(50.dp)
                .clip(
                    shape = CircleShape
                )
                .background(color = Color.Black),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = icon,
                contentDescription = "Bottom Sheet Icon",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = text)
    }
}



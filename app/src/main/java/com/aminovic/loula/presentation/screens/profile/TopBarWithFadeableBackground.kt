package com.aminovic.loula.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun TopBarWithFadeableBackground(
    text: String,
    backgroundAlpha: Float,
    navigationIcon: ImageVector = Icons.Default.ArrowBack,
    navigationIconTint: Color = Color.White,
    onNavigationIconClicked: () -> Unit
) {
    Column {
        Spacer(
            Modifier
                .background(MaterialTheme.colors.primarySurface.copy(alpha = backgroundAlpha))
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )
        ConstraintLayout(
            modifier = Modifier
                .height(barHeight)
                .fillMaxWidth()
        ) {
            val (backgroundRef, arrowRef, title) = createRefs()
            Surface(
                color = MaterialTheme.colors.primarySurface,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = backgroundAlpha
                    }
                    .constrainAs(backgroundRef) {
                        linkTo(
                            start = parent.start,
                            top = parent.top,
                            end = parent.end,
                            bottom = parent.bottom
                        )
                    }
            ) {}
            IconButton(
                onClick = { onNavigationIconClicked() },
                modifier = Modifier
                    .padding(start = navigationIconStartPadding)
                    .constrainAs(arrowRef) {
                        top.linkTo(backgroundRef.top)
                        bottom.linkTo(backgroundRef.bottom)
                        start.linkTo(parent.start)
                    }
            ) {
                Icon(
                    imageVector = navigationIcon,
                    tint = navigationIconTint,
                    contentDescription = "Navigation back arrow"
                )
            }

            Text(
                text = text,
                modifier = Modifier
                    .constrainAs(title) {
                        centerVerticallyTo(arrowRef)
                        start.linkTo(arrowRef.end, 16.dp)
                    }
                    .alpha(backgroundAlpha),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

private val barHeight = 56.dp
private val navigationIconStartPadding = 4.dp
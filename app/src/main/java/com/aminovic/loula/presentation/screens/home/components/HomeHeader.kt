package com.aminovic.loula.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.aminovic.loula.R
import com.aminovic.loula.presentation.ui.theme.LocalSpacing


@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onSearchClick: () -> Unit,
    onProfileClicked: () -> Unit
) {
    val spacing = LocalSpacing.current
    TopAppBar(
        title = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = stringResource(
                        id = R.string.home_logo
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
            }
        },
        backgroundColor = backgroundColor,
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = { onSearchClick() },
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_button)
                    )
                }
                IconButton(
                    onClick = { onProfileClicked() },
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(id = R.string.profil_button)
                    )
                }
            }
        }
    )
}
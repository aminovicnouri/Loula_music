package com.aminovic.loula.domain.utils

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.aminovic.loula.R
import com.aminovic.loula.domain.utils.Icon.DrawableResourceIcon
import com.aminovic.loula.domain.utils.Icon.ImageVectorIcon

object AppIcons {
    val Settings = ImageVectorIcon(Icons.Rounded.Settings)
    val ArrowBack = ImageVectorIcon(Icons.Rounded.ArrowBack)
    val Clear = ImageVectorIcon(Icons.Rounded.Clear)
    val Music = DrawableResourceIcon(R.drawable.ic_music)
    val Repeat = DrawableResourceIcon(R.drawable.ic_repeat)
    val RepeatOne = DrawableResourceIcon(R.drawable.ic_repeat_one)
    val Shuffle = DrawableResourceIcon(R.drawable.ic_shuffle)
    val SkipPrevious = DrawableResourceIcon(R.drawable.ic_skip_previous)
    val Play = DrawableResourceIcon(R.drawable.ic_play)
    val Pause = DrawableResourceIcon(R.drawable.ic_pause)
    val SkipNext = DrawableResourceIcon(R.drawable.ic_skip_next)
    val Info = ImageVectorIcon(Icons.Rounded.Info)
}

sealed interface Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon
    data class DrawableResourceIcon(@DrawableRes val resourceId: Int) : Icon
}

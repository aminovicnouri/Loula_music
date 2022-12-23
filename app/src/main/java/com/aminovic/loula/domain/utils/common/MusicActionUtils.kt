package com.aminovic.loula.domain.utils.common

import android.content.Context
import androidx.core.graphics.drawable.IconCompat
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaSession

internal fun MusicAction.asNotificationAction(
    context: Context,
    mediaSession: MediaSession,
    actionFactory: MediaNotification.ActionFactory
) = actionFactory.createMediaAction(
    mediaSession,
    IconCompat.createWithResource(context, iconResource),
    context.getString(titleResource),
    command
)

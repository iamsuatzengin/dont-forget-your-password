package com.suatzengin.forgotpassword.common

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.domain.model.Platform


fun ImageView.setPlatformIcon(platform: Platform) {
    when (platform) {
        Platform.NETFLIX -> {
            setImageResource(R.drawable.netflix_icon)
        }

        Platform.YOUTUBE -> {
            setImageResource(R.drawable.youtube_icon)
        }

        Platform.TWITTER -> {
            setImageResource(R.drawable.twitter_icon)
        }

        Platform.INSTAGRAM -> {
            setImageResource(R.drawable.instagram_icon)
        }

        Platform.SPOTIFY -> {
            setImageResource(R.drawable.spotify_icon)
        }
    }
}

fun String.copyToClipboard(context: Context) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val copiedText = this
    val clipData: ClipData = ClipData.newPlainText("Copied", copiedText)
    clipboard.setPrimaryClip(clipData)
}

fun TextInputLayout.checkIsEmpty(textInputEditText: TextInputEditText): String {
    val input = this.editText?.text.toString()

    textInputEditText.addTextChangedListener {
        if (!it.isNullOrEmpty()) {
            this.error = null
        }
    }

    if (input.isEmpty()) {
        this.error = "It cannot be empty!"
    }
    return input
}
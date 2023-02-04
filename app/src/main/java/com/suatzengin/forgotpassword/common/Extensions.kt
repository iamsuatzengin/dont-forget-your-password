package com.suatzengin.forgotpassword.common

import android.widget.ImageView
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.domain.model.Platform


fun ImageView.setPlatformIcon(platform: Platform){
    when(platform){
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
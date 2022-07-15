package com.example.djmayfielditunesapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MusicResponse(
    val results: List<MusicInfo>
)

@Parcelize
data class MusicInfo(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Double,
    val previewUrl: String
) : Parcelable

package de.tuchemnitz.se.exercise.persist.datatool

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureData(
    @SerialName("pic") val picturePath: String,
    val data: List<EyeTrackingData>,
)

@Serializable
data class EyeTrackingData(
    @SerialName("time") val time: Double,
    @SerialName("x_pos") val xPos: Double,
    @SerialName("y_pos") val yPos: Double,
)

@Serializable
data class DataFile(
    @SerialName("bubbleView") val bubbleViewConfigs: List<PictureData>,
    @SerialName("zoomMaps") val zoomMapsConfigs: List<PictureData>,
    @SerialName("codeCharts") val codeChartsConfigs: List<PictureData>,
    @SerialName("eyeTracking") val eyeTrackingConfigs: List<PictureData>,
)

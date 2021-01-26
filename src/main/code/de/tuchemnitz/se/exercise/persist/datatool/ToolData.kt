package de.tuchemnitz.se.exercise.persist.datatool

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToolData(
    @SerialName("pic") val picturePath: String,
    val data: List<ExperimentData>,
)

@Serializable
data class ExperimentData(
    @SerialName("time") val time: Double,
    @SerialName("x_pos") val xPos: Double,
    @SerialName("y_pos") val yPos: Double,
)

@Serializable
data class DataFile(
    @SerialName("bubbleView") val bubbleViewConfig: List<ToolData?>,
    @SerialName("zoomMaps") val zoomMapsConfig: List<ToolData?>,
    @SerialName("codeCharts") val codeChartsConfig: List<ToolData?>,
    @SerialName("eyeTracking") val eyeTrackingConfig: List<ToolData?>,
)

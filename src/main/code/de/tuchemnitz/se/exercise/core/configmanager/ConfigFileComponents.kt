package de.tuchemnitz.se.exercise.core.configmanager

import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class General(
    var selectionMenuEnabled: Boolean,
    var activatedTool: Int?,
    @SerialName("path") var configPath: String
)

@Serializable
data class BubbleViewConfig(
    val filter: Set<Float>
)

@Serializable
data class DataClientConfig(
    val colorSampleBoard: Set<ColorSampleBoard>,
    val exportPath: String
)

@Serializable
data class DatabaseConfig(
    val dataBaseName: String,
    val dataBasePath: String,
    val username: String
)

data class ToolConfigs(
    val bubbleViewConfig: BubbleViewConfig,
    val zoomMapsConfig: ZoomMapsConfig?,
    val codeChartsConfig: CodeChartsConfig?,
    val eyeTrackingConfig: EyeTrackingConfig?
)

@Serializable
data class ConfigFile(
    val general: General,
    @SerialName("bubbleView") val bubbleViewConfig: BubbleViewConfig,
    @SerialName("zoomMaps") val zoomMapsConfig: ZoomMapsConfig?,
    @SerialName("codeCharts") val codeChartsConfig: CodeChartsConfig?,
    @SerialName("eyeTracking") val eyeTrackingConfig: EyeTrackingConfig?,
    @SerialName("dataClient") val dataClientConfig: DataClientConfig,
    val database: DatabaseConfig
)

@Serializable
data class ColorSampleBoard(
    val red: Int,
    val green: Int,
    val blue: Int
)

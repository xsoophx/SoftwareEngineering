package de.tuchemnitz.se.exercise.core.configmanager

import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class has no logic; it's for storing all the data for the configfile.
 */
@Serializable
data class General(
    var selectionMenuEnabled: Boolean,
    var activatedTool: Int?, // TODO: abstract Tool
    @SerialName("path") var configPath: String
)

@Serializable
data class BubbleViewConfig(
    @SerialName("pictures") val filter: Set<FilterInformation>
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

data class ToolConfigs1(
    val bubbleViewConfig: BubbleViewConfig,
    val zoomMapsConfig: List<ZoomMapsConfig?>,
    val codeChartsConfig: List<CodeChartsConfig?>,
    val eyeTrackingConfig: EyeTrackingConfig?
)

@Serializable
data class ConfigFile1(
    val general: General,
    @SerialName("bubbleView") val bubbleViewConfig: BubbleViewConfig,
    @SerialName("zoomMaps") val zoomMapsConfig: List<ZoomMapsConfig?>,
    @SerialName("codeCharts") val codeChartsConfig: List<CodeChartsConfig?>,
    @SerialName("eyeTracking") val eyeTrackingConfig: EyeTrackingConfig?,
    @SerialName("dataClient") val dataClientConfig: DataClientConfig,
    val database: DatabaseConfig
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

@Serializable
data class FilterInformation(
    val path: String,
    val filter: Filter
)

@Serializable
data class Filter(
    val gradient: Int,
    val type: String
)

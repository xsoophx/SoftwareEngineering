package de.tuchemnitz.se.exercise.core.configmanager

import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import javafx.scene.input.KeyCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class has no logic; it's for storing all the data for the configfile.
 */
@Serializable
data class General(
    var fullscreen: Boolean,
    var width: Int,
    var height: Int,
    var selectionMenuEnabled: Boolean,
    var activatedTool: Int?, // TODO: abstract Tool
    var masterPath: String,
    var exportPath: String,
    var imagePath: String
)

@Serializable
data class BubbleViewConfig(
    @SerialName("pictures") val filter: Set<FilterInformation>
)

@Serializable
data class ConfigFileZoomMaps(
    val keyBindings: KeyBindings,
    @SerialName("pictures") val filter: Set<ZoomInformation>
)

@Serializable
data class ZoomInformation(
    val name: String,
    val zoomSpeed: Double
)

@Serializable
data class KeyBindings(
    val up: KeyCode,
    val down: KeyCode,
    val left: KeyCode,
    val right: KeyCode,
    @SerialName("in") val inKey: KeyCode,
    val out: KeyCode
)

@Serializable
data class DataClientConfig(
    val colorSampleBoard: Set<ColorSampleBoard>
)

@Serializable
data class DatabaseConfig(
    val dataBasePath: String
)

data class ToolConfigs(
    val bubbleViewConfig: BubbleViewConfig,
    val zoomMapsConfig: ConfigFileZoomMaps?,
    val codeChartsConfig: CodeChartsConfig?,
    val eyeTrackingConfig: EyeTrackingConfig?
)

@Serializable
data class ConfigFile(
    val general: General,
    @SerialName("bubbleView") val bubbleViewConfig: BubbleViewConfig,
    @SerialName("zoomMaps") val zoomMapsConfig: ConfigFileZoomMaps?,
    @SerialName("codeCharts") val codeChartsConfig: CodeChartsConfig?,
    @SerialName("eyeTracking") val eyeTrackingConfig: EyeTrackingConfig?,
    @SerialName("dataClient") val dataClientConfig: DataClientConfig,
    val database: DatabaseConfig
)

@Serializable
data class ColorSampleBoard(
    @SerialName("r") val red: Int,
    @SerialName("g") val green: Int,
    @SerialName("b") val blue: Int
)

@Serializable
data class FilterInformation(
    val name: String,
    val filter: Filter
)

@Serializable
data class Filter(
    val gradient: Int,
    val type: String
)

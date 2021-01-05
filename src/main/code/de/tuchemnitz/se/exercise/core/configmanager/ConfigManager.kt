package de.tuchemnitz.se.exercise.core.configmanager

import com.mongodb.client.MongoDatabase
import de.tuchemnitz.se.exercise.codecharts.CodeChartsTool
import de.tuchemnitz.se.exercise.core.AbstractTool
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.IConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.EyeTrackingConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.EyeTrackingData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.EyeTrackingDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.BsonDocument
import org.litote.kmongo.descending
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tornadofx.Controller
import tornadofx.ScopedInstance
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

class ConfigManager(var configFilePath: String = "", database: MongoDatabase) : Controller(), ScopedInstance {

    data class ConfigCollections(
        val codeChartsConfigCollection: CodeChartsConfigCollection,
        val zoomMapsConfigCollection: ZoomMapsConfigCollection,
        val eyeTrackingConfigCollection: EyeTrackingConfigCollection
    )

    data class DataCollections(
        val codeChartsDataCollection: CodeChartsDataCollection,
        val zoomMapsDataCollection: ZoomMapsDataCollection,
        val eyeTrackingDataCollection: EyeTrackingDataCollection
    )

    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()
    private val zoomMapsConfigCollection: ZoomMapsConfigCollection by inject()
    private val eyeTrackingConfigCollection: EyeTrackingConfigCollection by inject()

    private val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()
    private val eyeTrackingDataCollection: EyeTrackingDataCollection by inject()

    private val configCollections = ConfigCollections(
        codeChartsConfigCollection,
        zoomMapsConfigCollection,
        eyeTrackingConfigCollection
    )

    private val dataCollections = DataCollections(
        codeChartsDataCollection,
        zoomMapsDataCollection,
        eyeTrackingDataCollection
    )

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConfigManager::class.java)

        @Serializable
        val generalSettings = General(selectionMenuEnabled = true, activatedTool = null, configPath = "")

        val dataClientConfig = DataClientConfig(
            colorSampleBoard = setOf(ColorSampleBoard(red = 1, green = 2, blue = 3)),
            exportPath = "exportPath"
        )

        // TODO
        val databaseConfig =
            DatabaseConfig(dataBaseName = "test", dataBasePath = "databasePath", username = "root")
    }

    fun checkDBSimilarity(): Boolean {
        return true
    }

    fun writeFile(path: Path = Path.of(configFilePath)) {
        try {
            Files.writeString(path, configFile(), StandardCharsets.UTF_8)
        } catch (e: IOException) {
            logger.error("Error found: File does not exist")
        }
    }

    fun readFile(path: Path = Path.of(configFilePath)): String? {
        return try {
            Files.readString(path, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            logger.error("Could not read config file.", e)
            null
        }
    }

    fun getConfig(tool: AbstractTool) {
        when (tool) {
            is CodeChartsTool -> decodeConfig()?.codeChartsConfig
            else -> logger.error("Couldn't fetch this Config type.")
        }
    }

    fun saveConfig(config: IPersist) {
        when (config) {
            is CodeChartsConfig -> configCollections.codeChartsConfigCollection.saveOne(config)
            is ZoomMapsConfig -> configCollections.zoomMapsConfigCollection.saveOne(config)
            is EyeTrackingConfig -> configCollections.eyeTrackingConfigCollection.saveOne(config)
            is CodeChartsData -> dataCollections.codeChartsDataCollection.saveOne(config)
            is ZoomMapsData -> dataCollections.zoomMapsDataCollection.saveOne(config)
            is EyeTrackingData -> dataCollections.eyeTrackingDataCollection.saveOne(config)
            else -> logger.error("Couldn't fetch this Config type.")
        }
    }

    fun setGeneralSettings(selectionMenuEnabled: Boolean, activatedTool: Int?, configPath: Path) {
        generalSettings.activatedTool = activatedTool
        generalSettings.selectionMenuEnabled = selectionMenuEnabled
        generalSettings.configPath = configPath.toString()
    }

    private fun configFile(): String {
        val tools = assembleAllConfigurations()
        return Json { prettyPrint = true }.encodeToString(
            ConfigFile.serializer(),
            ConfigFile(
                general = generalSettings,
                bubbleViewConfig = tools.bubbleViewConfig,
                zoomMapsConfig = tools.zoomMapsConfig,
                codeChartsConfig = tools.codeChartsConfig,
                eyeTrackingConfig = tools.eyeTrackingConfig,
                dataClientConfig = dataClientConfig,
                database = databaseConfig
            )
        )
    }

    fun assembleAllConfigurations(): ToolConfigs {
        return ToolConfigs(
            codeChartsConfig = configCollections.codeChartsConfigCollection.findMostRecent(),
            zoomMapsConfig = configCollections.zoomMapsConfigCollection.findMostRecent(),
            // TODO
            eyeTrackingConfig = EyeTrackingConfig(dummyVal = ""),
            // TODO
            bubbleViewConfig = BubbleViewConfig(
                filter = setOf(
                    FilterInformation(
                        path = "",
                        Filter(gradient = 1, type = "gaussianBlur")
                    )
                )
            )
        )
    }

    private fun <T : IConfig> AbstractCollection<T>.findMostRecent(): T? =
        find(BsonDocument()).sort(descending(IConfig::savedAt))
            .firstOrNull()

    fun decodeConfig() = readFile(Path.of(configFilePath))?.let { Json.decodeFromString(ConfigFile.serializer(), it) }
}

package de.tuchemnitz.se.exercise.core.configmanager

import com.mongodb.client.MongoDatabase
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.IConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.EyeTrackingConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.litote.kmongo.descending
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ConfigManager(var configFilePath: String = "", database: MongoDatabase) {

    data class ConfigCollections(
        val codeChartsConfigCollection: CodeChartsConfigCollection,
        val zoomMapsConfigCollection: ZoomMapsConfigCollection,
        val eyeTrackingConfigCollection: EyeTrackingConfigCollection
    )

    private val configCollections = ConfigCollections(
        CodeChartsConfigCollection(database),
        ZoomMapsConfigCollection(database),
        EyeTrackingConfigCollection(database)
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

    fun writeFile(path: Path, content: String = "") {
        try {
            Files.writeString(path, content, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            logger.error("Error found: File does not exist")
        }
    }

    fun readFile(path: Path): String? {
        return try {
            Files.readString(path, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            logger.error("Could not read config file.", e)
            null
        }
    }

    fun getConfig(id: Int, filter: Bson) {
    }

    fun saveConfig(config: IConfig) {
        when (config) {
            is CodeChartsConfig -> configCollections.codeChartsConfigCollection.saveOne(config)
            is ZoomMapsConfig -> configCollections.zoomMapsConfigCollection.saveOne(config)
            is EyeTrackingConfig -> configCollections.eyeTrackingConfigCollection.saveOne(config)
            else -> logger.error("Couldn't fetch this Config type.")
        }
    }

    fun setGeneralSettings(selectionMenuEnabled: Boolean, activatedTool: Int?, configPath: Path) {
        generalSettings.activatedTool = activatedTool
        generalSettings.selectionMenuEnabled = selectionMenuEnabled
        generalSettings.configPath = configPath.toString()
    }

    fun configFile(): String {
        val tools = assembleAllConfigurations()
        return Json { prettyPrint = true }.encodeToString(
            ConfigFile.serializer(),
            ConfigFile(
                general = ConfigManager.generalSettings,
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
            bubbleViewConfig = BubbleViewConfig(filter = setOf(0F))
        )
    }

    private fun <T : IConfig> AbstractCollection<T>.findMostRecent(): T? =
        find(BsonDocument()).sort(descending(IConfig::savedAt))
            .firstOrNull()

    val decodedConfig = readFile(Paths.get(configFilePath))?.let { Json.decodeFromString(ConfigFile.serializer(), it) }
}

package de.tuchemnitz.se.exercise.core.configmanager

import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.IConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.EyeTrackingConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.litote.kmongo.KMongo
import org.litote.kmongo.descending
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ConfigManager(var configFilePath: String = "") {
    private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
    private val database = client.getDatabase("test") // normal java driver usage
    private val configCollections: List<AbstractCollection<out IConfig>> = listOf(
        ::CodeChartsConfigCollection,
        ::ZoomMapsConfigCollection,
        ::EyeTrackingConfigCollection
    ).map { it(database) }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConfigManager::class.java)

        val generalSettings = General(selectionMenuEnabled = true, activatedTool = null, configPath = Paths.get(""))
    }

    fun checkDBSimilarity(): Boolean {
        return true
    }

    fun writeFile(path: Path, content: String) {
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

    fun assembleAllConfigurations(): Set<IConfig> {
        return configCollections.mapNotNull {
            it.find(BsonDocument()).sort(descending(IConfig::savedAt)).firstOrNull()
        }.toSet()
    }

    fun getConfig(id: Int, filter: Bson) {
        configCollections[id].find(filter)
    }

    fun saveConfig(config: IConfig) {
        when (config) {
            is CodeChartsConfig -> (configCollections[0] as CodeChartsConfigCollection).saveOne(config)
            is ZoomMapsConfig -> (configCollections[1] as ZoomMapsConfigCollection).saveOne(config)
            is EyeTrackingConfig -> (configCollections[2] as EyeTrackingConfigCollection).saveOne(config)
        }
    }

    @OptIn(UnstableDefault::class)
    private fun createConfigFile(configs: Set<IConfig>) {
        val general = Json.stringify(General.serializer(), generalSettings)
        val bubbleView = Json.stringify(BubbleView.serializer(), BubbleView(filter = setOf(0F)))


    }

    fun setGeneralSettings(selectionMenuEnabled: Boolean, activatedTool: Int?, configPath: Path) {
        generalSettings.activatedTool = activatedTool
        generalSettings.selectionMenuEnabled = selectionMenuEnabled
        generalSettings.configPath = configPath
    }
}

package de.tuchemnitz.se.exercise.core.configmanager

import com.sun.javafx.tk.Toolkit
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
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.EyeTrackingDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import javafx.scene.input.KeyCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.BsonDocument
import org.litote.kmongo.descending
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tornadofx.Controller
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.security.Key

/**
 * This class covers all the logic for reading and writing the config file, as well as handling
 * settings for all tools. It can save and create setting, but also store them into the database
 * or retrieve data from the database
 *
 * @property configFilePath is the path to the config file. It's default is the relative path to this application.
 */
class ConfigManager(var configFilePath: String = "cfg.json") : Controller() {

    data class ConfigCollections(
        val codeChartsConfigCollection: CodeChartsConfigCollection,
        val zoomMapsConfigCollection: ZoomMapsConfigCollection,
        val eyeTrackingConfigCollection: EyeTrackingConfigCollection
    )

    data class DataCollections(
        val codeChartsDataCollection: CodeChartsDataCollection,
        val zoomMapsDataCollection: ZoomMapsDataCollection,
        val eyeTrackingDataCollection: EyeTrackingDataCollection,
        val userDataCollection: UserDataCollection
    )

    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()
    private val zoomMapsConfigCollection: ZoomMapsConfigCollection by inject()
    private val eyeTrackingConfigCollection: EyeTrackingConfigCollection by inject()

    private val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()
    private val eyeTrackingDataCollection: EyeTrackingDataCollection by inject()
    private val userDataCollection: UserDataCollection by inject()

    private val configCollections = ConfigCollections(
        codeChartsConfigCollection,
        zoomMapsConfigCollection,
        eyeTrackingConfigCollection
    )

    private val dataCollections = DataCollections(
        codeChartsDataCollection,
        zoomMapsDataCollection,
        eyeTrackingDataCollection,
        userDataCollection
    )

    var currentUser = UserData()

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConfigManager::class.java)

        @Serializable
        val generalSettings =
            General(
                fullscreen = true,
                width = java.awt.Toolkit.getDefaultToolkit().screenSize.getWidth().toInt(),
                height = java.awt.Toolkit.getDefaultToolkit().screenSize.getHeight().toInt(),
                selectionMenuEnabled = true,
                activatedTool = null,
                masterPath = "",
                exportPath = "",
                imagePath = ""
            )

        val dataClientConfig = DataClientConfig(
            colorSampleBoard = setOf(ColorSampleBoard(red = 1, green = 2, blue = 3)),
        )

        // TODO
        val databaseConfig =
            DatabaseConfig(dataBasePath = "databasePath")
    }

    /**
     * Compares the most recent database entry with the config file
     */
    fun checkDBSimilarity(): Boolean {
        return true
    }

    /**
     * Writes the config file to a certain path with.
     * @param path specifies the path where the file is written, default is the configFilePath
     * @throws IOException when writing of file goes wrong.
     */
    fun writeFile(path: Path = Path.of(configFilePath)) {
        try {
            Files.writeString(path, configFile(), StandardCharsets.UTF_8)
        } catch (e: IOException) {
            logger.error("Could not write file.", e)
        }
    }

    /**
     * Writes the config file to a certain path with.
     * @param path specifies the path where the file is written, default is the configFilePath
     * @throws IOException when reading of file goes wrong.
     */
    fun readFile(path: Path = Path.of(configFilePath)): String? {
        return try {
            Files.readString(path, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            logger.error("Could not read config file.", e)
            null
        }
    }

    /**
     * Depending on which tool is requiring a config, this function is returning it.
     * In this case a ZoomMapsConfig.
     */
    fun getZoomMapsConfig(): ZoomMapsConfig {
        return ZoomMapsConfig(zoomSpeed = 1.0, zoomKey = KeyCode.C)
        // return decodeConfig()?.zoomMapsConfig
    }

    /**
     * Saves the Configs, created  in the Tools, into the database
     * @param config specifies the config which is currently being saved.
     */
    fun savePersistable(config: IPersist) {
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

    /**
     * This function sets general settings for the config file.
     * @param selectionMenuEnabled specifies whether the selection menu of the config file is enabled or not
     * @param activatedTool specifies the tool which is currently activated
     * @param configPath specifies the path where the config file is saved
     */
    fun setGeneralSettings(
        fullscreen: Boolean,
        width: Int,
        height: Int,
        selectionMenuEnabled: Boolean,
        activatedTool: Int?,
        masterPath: Path,
        exportPath: Path,
        imagePath: Path
    ) {
        generalSettings.activatedTool = activatedTool
        generalSettings.selectionMenuEnabled = selectionMenuEnabled
        generalSettings.fullscreen = fullscreen
        generalSettings.width = width
        generalSettings.height = height
        generalSettings.masterPath = masterPath.toString()
        generalSettings.exportPath = exportPath.toString()
        generalSettings.imagePath = imagePath.toString()
    }

    /**
     * This function creates the config file out of all data.
     */
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

    /**
     * This function finds the most recent configs out of the database
     * and puts them together to be saved in the config file
     */
    fun assembleAllConfigurations(): ToolConfigs {
        val recentZoomConfig = dataCollections.zoomMapsDataCollection.findMostRecent()
        val keyCode = recentZoomConfig?.zoomKey ?: KeyCode.C
        val zoomImage = recentZoomConfig?.image ?: ""
        val zoomSpeed = recentZoomConfig?.zoomSpeed ?: 1.0
        return ToolConfigs(
            codeChartsConfig = configCollections.codeChartsConfigCollection.findMostRecent(),
            zoomMapsConfig = ConfigFileZoomMaps(
                keyBindings = KeyBindings(
                    up = keyCode,
                    down = keyCode,
                    left = keyCode,
                    right = keyCode,
                    inKey = keyCode,
                    out = keyCode
                ),
                filter = setOf(ZoomInformation(name = zoomImage, zoomSpeed = zoomSpeed))
            ),

            // TODO
            eyeTrackingConfig = EyeTrackingConfig(
                pictures = listOf(Pair("", 1))
            ),
            bubbleViewConfig = BubbleViewConfig(
                filter = setOf(
                    FilterInformation(
                        name = "",
                        Filter(gradient = 1, type = "gaussianBlur")
                    )
                )
            )
        )
    }

    /**
     * Finds the most recent config of a specified type of config.
     * @param T of type IConfig, the config type which is being searched for
     */
    private fun <T : IPersist> AbstractCollection<T>.findMostRecent(): T? =
        find(BsonDocument()).sort(descending(IPersist::savedAt))
            .firstOrNull()

    /**
     * Decodes the config file to data classes.
     */
    fun decodeConfig() = readFile(Path.of(configFilePath))?.let { Json.decodeFromString(ConfigFile.serializer(), it) }
}

package de.tuchemnitz.se.exercise.persist.datatool

import de.tuchemnitz.se.exercise.core.configmanager.BubbleViewConfig
import de.tuchemnitz.se.exercise.core.configmanager.ConfigFile
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.configmanager.Filter
import de.tuchemnitz.se.exercise.core.configmanager.FilterInformation
import de.tuchemnitz.se.exercise.core.configmanager.ToolConfigs
import de.tuchemnitz.se.exercise.dataanalyzer.CodeChartsDataFilter
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.IConfig
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.IData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.EyeTrackingDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import kotlinx.serialization.json.Json
import org.bson.BsonDocument
import org.litote.kmongo.and
import org.litote.kmongo.descending
import org.litote.kmongo.div
import org.litote.kmongo.eq
import tornadofx.Controller
import java.io.File
import java.nio.file.Path


class DataTool : Controller() {
    private val configManager: ConfigManager by inject()
    val data = dataFile()
    val dataFileJson = File("data.json").writeText(data)

    data class DataCollections(
        val codeChartsDataCollection: CodeChartsDataCollection,
        val zoomMapsDataCollection: ZoomMapsDataCollection,
        val eyeTrackingDataCollection: EyeTrackingDataCollection
    )

    private val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()
    private val eyeTrackingDataCollection: EyeTrackingDataCollection by inject()

    private val dataCollections = ConfigManager.DataCollections(
        codeChartsDataCollection,
        zoomMapsDataCollection,
        eyeTrackingDataCollection
    )

    fun configFileToDB(path: String) {
        val file = File(path)
        val jsonText = file.readText()
        jsonText?.let { Json.decodeFromString(ConfigFile.serializer(), it) }
    }

    fun dataFile(): String {
        return Json { prettyPrint = true }.encodeToString(
            DataFile.serializer(),
            assembleAllData(2)
        )
    }

    fun assembleAllData(n: Int): DataFile {
        return DataFile(
            codeChartsConfig = dataCollections.codeChartsDataCollection.findMostRecents("Chameleon.jpg", 1).toDataFile<CodeChartsData>(),
            zoomMapsConfig = dataCollections.zoomMapsDataCollection.findMostRecents("Chameleon.jpg", n).toDataFile<ZoomMapsData>(),
            // TODO
            eyeTrackingConfig = listOf(ToolData("", listOf(ExperimentData(time= 0.0, xPos = 1.0, yPos=2.0)))),
            // TODO
            bubbleViewConfig = listOf(ToolData("", listOf(ExperimentData(time= 0.0, xPos = 1.0, yPos=2.0)))),
        )
    }

    private fun <T : IData> AbstractCollection<T>.findMostRecents(imagePath: String, n: Int): List<T?> =
        find(
            and(
                (CodeChartsConfig::pictures / PictureData::imagePath eq imagePath)
            )).sort(descending(IConfig::savedAt)).take(n)

    private fun <T : IData> List<IData?>.toDataFile(): List<ToolData?> {
        return this.mapNotNull {
            when (it) {
                is CodeChartsData -> codeChartsToolData(it)
                is ZoomMapsData -> zoomMapsToolData(it)
                else -> null
            }
        }
    }

    private fun codeChartsToolData(codeChartsData: CodeChartsData): ToolData {
        return ToolData(
            picturePath = codeChartsData.codeChartsConfig.pictures.first().imagePath,
            listOf(
                ExperimentData(
                    time = 0.0,
                    xPos = (codeChartsData.stringPosition.xMax - codeChartsData.stringPosition.xMin) / 2,
                    yPos = (codeChartsData.stringPosition.yMax - codeChartsData.stringPosition.yMin) / 2
                )
            )
        )
    }

    private fun zoomMapsToolData(zoomMapsData: ZoomMapsData): ToolData {
        return ToolData(
            picturePath = "Chameleon.jpg",
            listOf(
                ExperimentData(
                    time = 0.0,
                    xPos = zoomMapsData.zoomPosition.x,
                    yPos = zoomMapsData.zoomPosition.y,
                )
            )
        )
    }
}

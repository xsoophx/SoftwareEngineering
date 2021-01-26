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
    val configs = configManager.configFile()
    val configFileJson = File("data.json").writeText(configs)

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
        val tools = assembleAllData(2)
        return Json { prettyPrint = true }.encodeToString(
            ToolData.serializer(),
            ToolData(
                time = 0.0,
                xPos =,
                yPos =,
            )
        )
    }

    fun assembleAllData(n: Int): DataFile {
        return DataFile(
            codeChartsConfig = dataCollections.codeChartsDataCollection.findMostRecents(n).toDataFile(),

            zoomMapsConfig = dataCollections.zoomMapsDataCollection.findMostRecents(n).toDataFile(),
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

    private fun <T : IData> AbstractCollection<T>.findMostRecents(n: Int): List<T?> =
        find(BsonDocument()).sort(descending(IConfig::savedAt)).take(n)

    private fun <T : IData> List<IData?>.toDataFile(): List<ToolData?> {
        return this.forEach {
            when(it) {
                is CodeChartsData -> codeChartsToolData(it)
            }
        }
    }
    private fun codeChartsToolData(codeChartsData: CodeChartsData): List<ToolData?> {
        return this.map { it?.codeChartsConfig?.pictures?.first()?.imagePath?.let { it1 -> ToolData(picturePath = it1, data = listOf<ExperimentData>()) } }
    }

    private fun queryCodeChartsData(filter: CodeChartsDataFilter): List<CodeChartsConfig> {
        return codeChartsConfigCollection.find(
            and(
                (CodeChartsConfig::pictures / PictureData::pictureViewTime eq filter.pictureViewTime.value).takeIf { filter.pictureViewTime.taken },
                (CodeChartsConfig::pictures / PictureData::matrixViewTime eq filter.matrixViewTime.value).takeIf { filter.matrixViewTime.taken }
            )
        ).toList()
    }

}

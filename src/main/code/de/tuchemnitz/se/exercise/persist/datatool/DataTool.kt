package de.tuchemnitz.se.exercise.persist.datatool

import de.tuchemnitz.se.exercise.core.configmanager.ConfigFile
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.IConfig
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.IData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.EyeTrackingDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import kotlinx.serialization.json.Json
import org.litote.kmongo.and
import org.litote.kmongo.descending
import org.litote.kmongo.div
import org.litote.kmongo.eq
import tornadofx.Controller
import java.io.File

class DataTool : Controller() {
    fun writeDataExportFile() = File("data.json").writeText(dataFile())

    data class DataCollections(
        val codeChartsDataCollection: CodeChartsDataCollection,
        val zoomMapsDataCollection: ZoomMapsDataCollection,
        val eyeTrackingDataCollection: EyeTrackingDataCollection
    )

    private val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()
    private val eyeTrackingDataCollection: EyeTrackingDataCollection by inject()

    private val dataCollections = DataCollections(
        codeChartsDataCollection,
        zoomMapsDataCollection,
        eyeTrackingDataCollection
    )

    fun configFileToDB(path: String) {
        val file = File(path)
        val jsonText = file.readText()
        jsonText.let { Json.decodeFromString(ConfigFile.serializer(), it) }
    }

    fun dataFile(): String {
        return Json { prettyPrint = true }.encodeToString(
            DataFile.serializer(),
            assembleAllData(2)
        )
    }

    private fun assembleAllData(n: Int): DataFile {
        return DataFile(
            codeChartsConfigs = dataCollections.codeChartsDataCollection.findMostRecents("Chameleon.jpg", 1)
                .toDataFile(),
            zoomMapsConfigs = dataCollections.zoomMapsDataCollection.findMostRecents("Chameleon.jpg", n)
                .toDataFile(),
            // TODO
            eyeTrackingConfigs = listOf(PictureData("", listOf(EyeTrackingData(time = 0.0, xPos = 1.0, yPos = 2.0)))),
            // TODO
            bubbleViewConfigs = listOf(PictureData("", listOf(EyeTrackingData(time = 0.0, xPos = 1.0, yPos = 2.0)))),
        )
    }

    private fun <T : IData> AbstractCollection<T>.findMostRecents(imagePath: String, n: Int): List<T> =
        find(
            and(
                (CodeChartsConfig::pictures / PictureData::imagePath eq imagePath)
            )
        ).sort(descending(IConfig::savedAt)).take(n)

    private fun List<IData>.toDataFile(): List<de.tuchemnitz.se.exercise.persist.datatool.PictureData> {
        return mapNotNull {
            when (it) {
                is CodeChartsData -> codeChartsToolData(it)
                is ZoomMapsData -> zoomMapsToolData(it)
                else -> null
            }
        }
    }

    private fun codeChartsToolData(codeChartsData: CodeChartsData): de.tuchemnitz.se.exercise.persist.datatool.PictureData {
        return PictureData(
            picturePath = codeChartsData.codeChartsConfig.pictures.first().imagePath,
            listOf(
                EyeTrackingData(
                    time = 0.0,
                    xPos = (codeChartsData.stringPosition.xMax - codeChartsData.stringPosition.xMin) / 2,
                    yPos = (codeChartsData.stringPosition.yMax - codeChartsData.stringPosition.yMin) / 2
                )
            )
        )
    }

    private fun zoomMapsToolData(zoomMapsData: ZoomMapsData): de.tuchemnitz.se.exercise.persist.datatool.PictureData {
        return PictureData(
            picturePath = "Chameleon.jpg",
            listOf(
                EyeTrackingData(
                    time = 0.0,
                    xPos = zoomMapsData.zoomPosition.x,
                    yPos = zoomMapsData.zoomPosition.y,
                )
            )
        )
    }
}

package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import tornadofx.Controller

class CodeChartsConfigMapper : Controller() {
    private val configManager: ConfigManager by inject()

    fun saveCodeChartsDatabaseConfig(codeChartsValues: CodeChartsValues) {
        configManager.savePersistable(
            CodeChartsConfig(
                minViewsToSubdivide = 0,
                stringCharacters = codeChartsValues.allowedChars,
                pictures = listOf(
                    PictureData(
                        imagePath = codeChartsValues.imagePath,
                        grid = Grid(codeChartsValues.gridDimension.x.toInt(), codeChartsValues.gridDimension.y.toInt()),
                        pictureViewTime = codeChartsValues.pictureViewTime.toInt(),
                        relative = codeChartsValues.relative,
                        maxRecursionDepth = codeChartsValues.recursionDepth
                    )
                ),
                matrixViewTime = codeChartsValues.matrixViewTime.toInt(),
                ordered = codeChartsValues.sorted
            ).also {
                configManager.savePersistable(
                    CodeChartsData(
                        codeChartsConfig = it,
                        originalImageSize = codeChartsValues.originalImageSize,
                        scaledImageSize = codeChartsValues.scaledImageSize,
                        screenSize = codeChartsValues.screenSize,
                        stringPosition = codeChartsValues.eyePos,
                        currentUser = configManager.currentUser
                    )
                )
            }
        )
    }
}
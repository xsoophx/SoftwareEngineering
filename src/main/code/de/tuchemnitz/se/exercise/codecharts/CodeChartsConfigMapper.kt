package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.Grid
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import tornadofx.Controller

class CodeChartsConfigMapper(private val codeChartsValues: CodeChartsValues) : Controller() {
    private val configManager: ConfigManager by inject()

    fun saveCodeChartsDatabaseConfig() {
        configManager.saveConfig(
            CodeChartsConfig(
                matrixViewTime = codeChartsValues.matrixViewTime.toInt(),
                minViewsToSubdivide = 0,
                stringCharacters = codeChartsValues.allowedChars,
                pictures = listOf(PictureData(
                    imagePath = codeChartsValues.imagePath,
                    grid = Grid(codeChartsValues.gridDimension.x.toInt(), codeChartsValues.gridDimension.y.toInt()),
                    pictureViewTime = codeChartsValues.pictureViewTime.toInt(),
                    ordered = codeChartsValues.sorted,
                    relative = codeChartsValues.relative,
                    maxRecursionDepth = codeChartsValues.recursionDepth
                ))
            )
        )
    }
}
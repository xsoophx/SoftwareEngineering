package de.tuchemnitz.se.exercise.codecharts

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import tornadofx.Controller

class CodeChartsConfigMapper(private val codeChartsValues: CodeChartsValues) : Controller() {
    private val configManager: ConfigManager by inject()

    fun saveCodeChartsDatabaseConfig() {
        configManager.saveConfig(
            CodeChartsConfig(
                grid = Pair(codeChartsValues.gridDimension.x.toInt(), codeChartsValues.gridDimension.y.toInt()),
                pictureViewTime = codeChartsValues.pictureViewTime.toInt(),
                matrixViewTime = codeChartsValues.matrixViewTime.toInt()
            )
        )
    }
}
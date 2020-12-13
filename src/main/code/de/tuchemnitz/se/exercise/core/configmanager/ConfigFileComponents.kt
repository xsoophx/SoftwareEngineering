package de.tuchemnitz.se.exercise.core.configmanager

import kotlinx.serialization.Serializable
import java.nio.file.Path

@Serializable
data class General(
    var selectionMenuEnabled: Boolean,
    var activatedTool: Int?,
    var configPath: Path
)

@Serializable
data class BubbleView(
    val filter: Set<Float>
)

@Serializable
data class DataClient(
    val colorSampleBoard: Set<Triple<Int, Int, Int>>,
    val exportPath: String
)

@Serializable
data class Database(
    val dataBaseName: String,
    val databasePath: Path,
    val username: String
)


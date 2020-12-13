package de.tuchemnitz.se.exercise

import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import org.junit.jupiter.params.provider.Arguments
import org.litote.kmongo.KMongo
import java.util.stream.Stream

object DummyData {
    val manager = ConfigManager()
    private val client = KMongo.createClient() // get com.mongodb.MongoClient new instance
    private val database = client.getDatabase("test")
    val codeChartsConfigCollection = CodeChartsConfigCollection(database)
    val zoomMapsConfigCollection = ZoomMapsConfigCollection(database)

    @JvmStatic
    @Suppress("unused")
    fun codeChartsConfigs() = Stream.of(
        Arguments.of(
            CodeChartsConfig(
                grid = Pair(100, 200),
                pictureViewTime = 1,
                matrixViewTime = 2
            )
        ),
        Arguments.of(
            CodeChartsConfig(
                grid = Pair(0, 0),
                pictureViewTime = 0,
                matrixViewTime = 0
            )
        ),
        Arguments.of(
            CodeChartsConfig(
                grid = Pair(400, 400),
                pictureViewTime = 4,
                matrixViewTime = 4
            )
        )

    )

    val zoomMapsConfigs = setOf(
        ZoomMapsConfig(
            zoomSpeed = 1F,
            keyBindings = setOf("a", "b", "c", "d")
        ),
        ZoomMapsConfig(
            zoomSpeed = 2F,
            keyBindings = setOf("d", "e", "f", "g")
        ),
        ZoomMapsConfig(
            zoomSpeed = 10F,
            keyBindings = setOf("v", "w", "x", "y")
        ),
    )
}

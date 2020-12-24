package de.tuchemnitz.se.exercise.persist

import assertk.assertThat
import assertk.assertions.isInstanceOf
import de.tuchemnitz.se.exercise.DATABASE
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.EyeTrackingConfigCollection
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.EyeTrackingData
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.EyeTrackingDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import tornadofx.Controller

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Tag(DATABASE)
class InstantiationTest : Controller() {
    private val eyeTrackingConfigCollection: EyeTrackingConfigCollection by inject()
    private val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val eyeTrackingDataCollection: EyeTrackingDataCollection by inject()
    private val userDataCollection: UserDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()

    private val collections = listOf<AbstractCollection<out IPersist>>(
        eyeTrackingConfigCollection,
        codeChartsDataCollection,
        eyeTrackingDataCollection,
        userDataCollection,
        zoomMapsDataCollection
    )

    @Suppress("SpellCheckingInspection")
    private val savables = listOf(
        EyeTrackingConfig(dummyVal = "eye"),
        CodeChartsData(dummyData = "dummy"),
        EyeTrackingData(dummyData = "dummy"),
        UserData(firstName = "test", surName = "user", age = 25),
        ZoomMapsData(dummyData = "dummy")
    )

    @Test
    fun `test creation  of all configs `() {
        savables.forEach {
            assertThat(it).isInstanceOf(IPersist::class.java)
        }

        collections.forEach {
            assertThat(it).isInstanceOf(AbstractCollection::class.java)
        }
    }
}

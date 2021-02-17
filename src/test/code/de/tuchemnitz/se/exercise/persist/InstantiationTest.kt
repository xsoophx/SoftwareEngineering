package de.tuchemnitz.se.exercise.persist

import assertk.assertThat
import assertk.assertions.isInstanceOf
import de.tuchemnitz.se.exercise.DATABASE
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.EyeTrackingConfigCollection
import de.tuchemnitz.se.exercise.persist.data.EyeTrackingData
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.VisionImpaired
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.EyeTrackingDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import javafx.geometry.Point2D
import javafx.scene.input.KeyCode
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
        EyeTrackingConfig(pictures = emptyList()),
        DummyData.codeChartsData.first(),
        EyeTrackingData(dummyData = "dummy", currentUser = DummyData.userData.last()),
        UserData(
            firstName = "test",
            lastName = "user",
            age = 25,
            gender = Gender.Female,
            visionImpaired = VisionImpaired.No
        ),
        ZoomMapsData(
            zoomSpeed = 1.0,
            zoomKey = KeyCode.K,
            zoomPosition = Point2D(1.0, 1.0),
            currentUser = DummyData.userData.first(),
            image = "aPath"
        )
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

package de.tuchemnitz.se.exercise.persist.data

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import de.tuchemnitz.se.exercise.persist.Point2DSerializer
import de.tuchemnitz.se.exercise.persist.now
import javafx.geometry.Point2D
import javafx.scene.input.KeyCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.Instant

@Serializable
data class ZoomMapsData(
    @Transient override val _id: Id<ZoomMapsData> = newId(),
    @Transient override val savedAt: Instant = now(),
    val zoomSpeed: Double,
    val zoomKey: KeyCode,
    @JsonDeserialize(using = Point2DSerializer::class)
    @Serializable(with = Point2DSerializer::class)
    val zoomPosition: Point2D,
    val imagePath: String,
    @Transient val currentUser: UserData = UserData(default = true)
) : IData
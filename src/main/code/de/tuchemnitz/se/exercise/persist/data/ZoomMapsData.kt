package de.tuchemnitz.se.exercise.persist.data

import javafx.geometry.Point2D
import javafx.scene.input.KeyCode
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class ZoomMapsData(
    @Transient override val _id: Id<ZoomMapsData> = newId(),
    val zoomSpeed: Double,
    val zoomKey: KeyCode,
    val zoomPosition: Point2D
) : IData

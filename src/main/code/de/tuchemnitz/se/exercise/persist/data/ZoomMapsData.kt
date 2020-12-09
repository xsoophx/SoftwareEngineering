package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class ZoomMapsData(
    override val _id: Id<ZoomMapsData> = newId(),
    val dummyData: String
) : IData

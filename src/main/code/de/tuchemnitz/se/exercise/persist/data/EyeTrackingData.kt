package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class EyeTrackingData(
    override val _id: Id<EyeTrackingData> = newId(),
    val dummyData: String
) : IData

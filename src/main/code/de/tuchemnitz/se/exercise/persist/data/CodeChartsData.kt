package de.tuchemnitz.se.exercise.persist.data

import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class CodeChartsData(
    override val _id: Id<CodeChartsData> = newId(),
    val dummyData: String
) : IData

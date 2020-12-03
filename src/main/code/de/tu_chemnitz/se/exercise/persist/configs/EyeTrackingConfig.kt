package de.tu_chemnitz.se.exercise.persist.configs

import org.litote.kmongo.Id

//TODO:
data class EyeTrackingConfig(
 override val _id: Id<out IConfig>,
 val dummyVal: String
) : IConfig
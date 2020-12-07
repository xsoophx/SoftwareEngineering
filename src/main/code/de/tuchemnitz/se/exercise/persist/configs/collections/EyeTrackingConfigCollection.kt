package de.tuchemnitz.se.exercise.persist.configs.collections

import com.mongodb.client.MongoDatabase
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.configs.EyeTrackingConfig
import org.litote.kmongo.getCollection

class EyeTrackingConfigCollection(
    private val db: MongoDatabase
) : AbstractCollection<EyeTrackingConfig>(collection = db.getCollection())

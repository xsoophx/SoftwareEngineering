package de.tu_chemnitz.se.exercise.persist.data.collections

import com.mongodb.client.MongoDatabase
import de.tu_chemnitz.se.exercise.persist.AbstractCollection
import de.tu_chemnitz.se.exercise.persist.data.EyeTrackingData
import org.litote.kmongo.getCollection

class EyeTrackingDataCollection(
  private val db: MongoDatabase
) : AbstractCollection<EyeTrackingData>(collection = db.getCollection())
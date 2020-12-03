package de.tu_chemnitz.se.exercise.persist.data.collections

import com.mongodb.client.MongoDatabase
import de.tu_chemnitz.se.exercise.persist.AbstractCollection
import de.tu_chemnitz.se.exercise.persist.data.ZoomMapsData
import org.litote.kmongo.getCollection

class ZoomMapsDataCollection(
  private val db: MongoDatabase
) : AbstractCollection<ZoomMapsData>(collection = db.getCollection())
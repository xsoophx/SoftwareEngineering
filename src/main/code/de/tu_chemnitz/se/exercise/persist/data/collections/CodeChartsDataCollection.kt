package de.tu_chemnitz.se.exercise.persist.data.collections

import com.mongodb.client.MongoDatabase
import de.tu_chemnitz.se.exercise.persist.AbstractCollection
import de.tu_chemnitz.se.exercise.persist.data.CodeChartsData
import org.litote.kmongo.getCollection

class CodeChartsDataCollection(
  private val db: MongoDatabase
) : AbstractCollection<CodeChartsData>(collection = db.getCollection())
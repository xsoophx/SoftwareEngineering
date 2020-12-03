package de.tu_chemnitz.se.exercise.persist.configs.collections

import com.mongodb.client.MongoDatabase
import de.tu_chemnitz.se.exercise.persist.AbstractCollection
import de.tu_chemnitz.se.exercise.persist.data.UserData
import org.litote.kmongo.getCollection

class UserDataCollection (
  private val db: MongoDatabase
) : AbstractCollection<UserData>(collection = db.getCollection())
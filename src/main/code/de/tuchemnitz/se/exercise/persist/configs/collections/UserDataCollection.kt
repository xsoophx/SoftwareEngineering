package de.tuchemnitz.se.exercise.persist.configs.collections

import com.mongodb.client.MongoDatabase
import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.data.UserData
import org.litote.kmongo.getCollection

class UserDataCollection(
    db: MongoDatabase
) : AbstractCollection<UserData>(collection = db.getCollection())

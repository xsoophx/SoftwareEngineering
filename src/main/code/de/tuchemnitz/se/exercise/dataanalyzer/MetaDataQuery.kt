package de.tuchemnitz.se.exercise.dataanalyzer

import com.mongodb.client.model.Filters.eq
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.litote.kmongo.MongoOperator
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.gte
import org.litote.kmongo.lte
import tornadofx.Controller

class MetaDataQuery : Controller() {

    val userDataCollection: UserDataCollection by inject()
    private val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()

    fun queryMetadataTotal(): DataClientMetadataTotal {

        val ageGroup0 = userDataCollection.find(and(UserData::age lte 10)).count()
        val ageGroup1 = userDataCollection.find(and((UserData::age gte 10), (UserData::age lte 20))).count()
        val ageGroup2 = userDataCollection.find(and((UserData::age gte 20), (UserData::age lte 40))).count()
        val ageGroup3 = userDataCollection.find(and((UserData::age gte 40), (UserData::age lte 60))).count()
        val ageGroup4 = userDataCollection.find(and((UserData::age gte 60))).count()

        val male = userDataCollection.find(and((UserData::gender eq Gender.Male))).count()
        val female = userDataCollection.find(and((UserData::gender eq Gender.Female))).count()
        val other = userDataCollection.find(and((UserData::gender eq Gender.Other))).count()

        val totalUsers = userDataCollection.find().count()
        val totalDatasets = codeChartsDataCollection.find().count() + zoomMapsDataCollection.find().count()

        return DataClientMetadataTotal(ageGroup0, ageGroup1, ageGroup2, ageGroup3, ageGroup4, male, female, other, totalUsers, totalDatasets)
    }

    fun queryMetadataToolUse(): DataClientMetadataToolUse {
        val cc = codeChartsDataCollection.find().count()
        val zoom = zoomMapsDataCollection.find().count()
        return DataClientMetadataToolUse(cc, zoom)
    }

    fun queryPictureDistributionCC(): List<DataClientPictureDistribution> {
        val dataList = mutableListOf<DataClientPictureDistribution>()

        val chameleon = codeChartsDataCollection.find(
            eq("CodeChartsConfig.pictures[0].imagePath", "Chameleon.jpg")
        ).count()
        dataList.add(DataClientPictureDistribution("Chameleon.jpg", chameleon))
        val pinguin = codeChartsDataCollection.find(
            eq("CodeChartsConfig.pictures[0].imagePath", "Pinguin.jpg")
        ).count()
        dataList.add(DataClientPictureDistribution("Pinguin.jpg", pinguin))
        val kitten  = codeChartsDataCollection.find(
            eq("CodeChartsConfig.pictures[0].imagePath", "kitten.jpg")
        ).count()
        dataList.add(DataClientPictureDistribution("kitten.jpg", kitten))

        return dataList
    }

    // TODO
    // At the moment Code Charts and Zoom Maps data don't contain reference to the user, so it is not possible to 
    // show user metadata by tool
    /*
    fun queryMetadataCodeCharts() : DataClientMetadataCodeCharts {

    }
    
     */
}

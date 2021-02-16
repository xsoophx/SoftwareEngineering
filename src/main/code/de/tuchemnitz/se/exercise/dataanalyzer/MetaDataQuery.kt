package de.tuchemnitz.se.exercise.dataanalyzer

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.gte
import com.mongodb.client.model.Filters.lte
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.litote.kmongo.and
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.gte
import org.litote.kmongo.lte
import tornadofx.Controller

class MetaDataQuery : Controller() {

    /**
     * get reference to data collections
     */
    private val userDataCollection: UserDataCollection by inject()
    private val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()

    /**
     * query database for metadata: total number of registered users, their age and gender distribution
     */
    fun queryMetadataTotal(): DataClientMetadataTotal {

        val ageGroup0 = userDataCollection.find(and(UserData::age lte 10)).count()
        val ageGroup1 = userDataCollection.find(and((UserData::age gte 10), (UserData::age lte 20))).count()
        val ageGroup2 = userDataCollection.find(and((UserData::age gte 20), (UserData::age lte 40))).count()
        val ageGroup3 = userDataCollection.find(and((UserData::age gte 40), (UserData::age lte 60))).count()
        val ageGroup4 = userDataCollection.find(and((UserData::age gte 60))).count()

        val male = userDataCollection.find(and((UserData::gender eq Gender.Male))).count()
        val female = userDataCollection.find(and((UserData::gender eq Gender.Female))).count()
        val other = userDataCollection.find(and((UserData::gender eq Gender.Diverse))).count()

        val totalUsers = userDataCollection.find().count()
        val totalDatasets = codeChartsDataCollection.find().count() + zoomMapsDataCollection.find().count()

        return DataClientMetadataTotal(ageGroup0, ageGroup1, ageGroup2, ageGroup3, ageGroup4, male, female, other, totalUsers, totalDatasets)
    }

    /**
     * query database for distribution of tool use
     */
    fun queryMetadataToolUse(): DataClientMetadataToolUse {
        val cc = codeChartsDataCollection.find().count()
        val zoom = zoomMapsDataCollection.find().count()
        return DataClientMetadataToolUse(cc, zoom)
    }

    /**
     * query database for metadata specific to CODE CHARTS tool
     */
    fun queryPictureDistributionCC(): List<DataClientPictureDistribution> {
        val dataList = mutableListOf<DataClientPictureDistribution>()

        val chameleon = codeChartsDataCollection.find(
            CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                eq "/Chameleon.jpg"
        ).count()
        dataList.add(DataClientPictureDistribution("Chameleon", chameleon))

        val pinguin = codeChartsDataCollection.find(
            CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                eq "/Pinguin.jpg"
        ).count()
        dataList.add(DataClientPictureDistribution("Pinguin", pinguin))

        val kitten = codeChartsDataCollection.find(
            CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                eq "/kitten.jpg"
        ).count()
        dataList.add(DataClientPictureDistribution("kitten", kitten))

        return dataList
    }


    fun queryMetadataCodeCharts(): DataClientMetadataCodeCharts {
        val total = codeChartsDataCollection.find().count()
        val ccAge0 = codeChartsDataCollection.find(lte("currentUser.age", 10)).count()
        val ccAge1 = codeChartsDataCollection.find(
            and(gte("currentUser.age", 10), lte("currentUser.age", 20))
        ).count()
        val ccAge2 = codeChartsDataCollection.find(
            and(gte("currentUser.age", 20), lte("currentUser.age", 40))
        ).count()
        val ccAge3 = codeChartsDataCollection.find(
            and(gte("currentUser.age", 40), lte("currentUser.age", 60))
        ).count()
        val ccAge4 = codeChartsDataCollection.find(gte("currentUser.age", 60)).count()

        val ccMale = codeChartsDataCollection.find(eq("currentUser.gender", Gender.Male)).count()
        val ccFemle = codeChartsDataCollection.find(eq("currentUser.gender", Gender.Female)).count()
        val ccOther = codeChartsDataCollection.find(eq("currentUser.gender", Gender.Diverse)).count()

        return DataClientMetadataCodeCharts(ccAge0, ccAge1, ccAge2, ccAge3, ccAge4, ccMale, ccFemle, ccOther, total)
    }

    /**
     * Query database for metadata specific to ZOOM MAPS Tool
     */

    fun queryPictureDistributionZoom(): List<DataClientPictureDistribution> {
        val dataList = mutableListOf<DataClientPictureDistribution>()

        val chameleon = zoomMapsDataCollection.find(
            eq("imagePath", "/Chameleon.jpg")
        ).count()
        dataList.add(DataClientPictureDistribution("Chameleon", chameleon))
        val pinguin = zoomMapsDataCollection.find(
            eq("imagePath", "/Pinguin.jpg")
        ).count()
        dataList.add(DataClientPictureDistribution("Pinguin", pinguin))
        val kitten = zoomMapsDataCollection.find(
            eq("imagePath", "/kitten.jpg")
        ).count()
        dataList.add(DataClientPictureDistribution("kitten", kitten))

        return dataList
    }

    fun queryMetadataZoomMaps(): DataClientMetadataZoomMaps {
        val total = zoomMapsDataCollection.find().count()
        val zoomAge0 = zoomMapsDataCollection.find(lte("currentUser.age", 10)).count()
        val zoomAge1 = zoomMapsDataCollection.find(
            and(gte("currentUser.age", 10), lte("currentUser.age", 20))
        ).count()
        val zoomAge2 = zoomMapsDataCollection.find(
            and(gte("currentUser.age", 20), lte("currentUser.age", 40))
        ).count()
        val zoomAge3 = zoomMapsDataCollection.find(
            and(gte("currentUser.age", 40), lte("currentUser.age", 60))
        ).count()
        val zoomAge4 = zoomMapsDataCollection.find(gte("currentUser.age", 60)).count()

        val zoomMale = zoomMapsDataCollection.find(eq("currentUser.gender", Gender.Male)).count()
        val zoomFemle = zoomMapsDataCollection.find(eq("currentUser.gender", Gender.Female)).count()
        val zoomOther = zoomMapsDataCollection.find(eq("currentUser.gender", Gender.Diverse)).count()

        return DataClientMetadataZoomMaps(zoomAge0, zoomAge1, zoomAge2, zoomAge3, zoomAge4, zoomMale, zoomFemle, zoomOther, total)
    }
}

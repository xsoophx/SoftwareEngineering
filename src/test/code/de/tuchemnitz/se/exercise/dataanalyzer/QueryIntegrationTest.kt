package de.tuchemnitz.se.exercise.dataanalyzer

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import de.tuchemnitz.se.exercise.DummyData
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.Gender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.litote.kmongo.div
import org.litote.kmongo.eq
import tornadofx.Controller

class QueryIntegrationTest : Controller() {

    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()

    companion object {
        val query = Query()
        private val userDataFilter =
            UserDataFilter(
                firstName = Filter(taken = true, value = "Klaus"),
                lastName = Filter(taken = false, value = ""),
                age = Filter(taken = false, value = Age(minimumAge = 20, maximumAge = 50)),
                gender = Filter(
                    taken = false, value = Gender.Male
                )
            )
        private val codeChartsDataFilter = CodeChartsDataFilter(
            pictureViewTime = Filter(taken = false, value = 0),
            matrixViewTime = Filter(taken = false, value = 0),
            imagePath = Filter(taken = true, value = "abc")
        )

        private val filter = QueryFilter(
            userDataFilter = Filter(taken = true, value = userDataFilter),
            codeChartsDataFilter = Filter(
                taken = true,
                value = codeChartsDataFilter
            ),
            zoomMapsFilter = null
        )
        private val onlyUserDataFilter = QueryFilter(
            userDataFilter = Filter(taken = true, value = userDataFilter),
            codeChartsDataFilter = null,
            zoomMapsFilter = null
        )
        private val onlyCodeChartsDataFilter = QueryFilter(
            userDataFilter = null,
            codeChartsDataFilter = Filter(
                taken = true,
                value = codeChartsDataFilter
            ),
            zoomMapsFilter = null
        )
    }

    @BeforeEach
    fun setup() {
        query.codeChartsDataCollection.saveMany(DummyData.codeChartsData)
        query.userDataCollection.saveMany(DummyData.userData)
    }

    @AfterEach
    fun close() {
        query.codeChartsDataCollection.deleteMany()
        query.userDataCollection.deleteMany()
    }

    @Test
    fun `querying userData Filter should work`() {
        val queryResult = query.queryAllElementsSeparately(onlyUserDataFilter)
        val databaseIterable = query.userDataCollection.find(DummyData.userData.first()::firstName eq "Klaus")
        databaseIterable.forEach {
            assertThat(queryResult).contains(it)
        }
        assertThat(queryResult.size).isEqualTo(databaseIterable.count())
    }

    @Test
    fun `querying with codeCharts Filter should work`() {
        val queryResult = query.queryAllElementsSeparately(onlyCodeChartsDataFilter)
        val databaseIterable =
            query.codeChartsDataCollection.find(
                CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                    eq "abc"
            )

        databaseIterable.forEach {
            assertThat(queryResult).contains(it)
        }
        assertThat(queryResult.size).isEqualTo(databaseIterable.count())
    }

    @Test
    fun `querying with codeCharts and userData Filter should work`() {
        val queryResult = query.queryAllElementsSeparately(filter)
        val databaseIterable =
            query.codeChartsDataCollection.find(
                CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                    eq "abc"
            )

        databaseIterable.forEach {
            assertThat(queryResult).contains(it)
        }
        assertThat(queryResult.size).isEqualTo(databaseIterable.count())
    }
}

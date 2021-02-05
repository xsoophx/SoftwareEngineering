package de.tuchemnitz.se.exercise.dataanalyzer

class DataClientMetadataTotal(
    totalAgeGroup0: Int,
    totalAgeGroup1: Int,
    totalAgeGroup2: Int,
    totalAgeGroup3: Int,
    totalAgeGroup4: Int,
    totalGenderMale: Int,
    totalGenderFemale: Int,
    totalGenderOther: Int,
    totalNumberOfUsers: Int,
    totalNumberOfDatasets: Int
) {
    val totalAgeGroup0: Int = totalAgeGroup0
    val totalAgeGroup1: Int = totalAgeGroup1
    val totalAgeGroup2: Int = totalAgeGroup2
    val totalAgeGroup3: Int = totalAgeGroup3
    val totalAgeGroup4: Int = totalAgeGroup4
    val totalGenderMale: Int = totalGenderMale
    val totalGenderFemale: Int = totalGenderFemale
    val totalGenderOther: Int = totalGenderOther
    val totalNumberOfUsers: Int = totalNumberOfUsers
    val totalNumberOfDatasets: Int = totalNumberOfDatasets
}

class DataClientMetadataCodeCharts(
    ccAgeGroup0: Int = 0,
    ccAgeGroup1: Int = 0,
    ccAgeGroup2: Int = 0,
    ccAgeGroup3: Int = 0,
    ccAgeGroup4: Int = 0,
    ccGenderMale: Int = 0,
    ccGenderFemale: Int = 0,
    ccGenderOther: Int = 0,
    ccTotalNumberOfDatasets: Int = 0
) {
    val ccAgeGroup0: Int = ccAgeGroup0
    val ccAgeGroup1: Int = ccAgeGroup1
    val ccAgeGroup2: Int = ccAgeGroup2
    val ccAgeGroup3: Int = ccAgeGroup3
    val ccAgeGroup4: Int = ccAgeGroup4
    val ccGenderMale: Int = ccGenderMale
    val ccGenderFemale: Int = ccGenderFemale
    val ccGenderOther: Int = ccGenderOther
    val ccTotalNumberOfDatasets: Int = ccTotalNumberOfDatasets
}

class DataClientMetadataZoomMaps(
    zoomAgeGroup0: Int = 0,
    zoomAgeGroup1: Int = 0,
    zoomAgeGroup2: Int = 0,
    zoomAgeGroup3: Int = 0,
    zoomAgeGroup4: Int = 0,
    zoomGenderMale: Int = 0,
    zoomGenderFemale: Int = 0,
    zoomGenderOther: Int = 0,
    zoomTotalNumberOfDatasets: Int = 0
) {
    val zoomAgeGroup0: Int = zoomAgeGroup0
    val zoomAgeGroup1: Int = zoomAgeGroup1
    val zoomAgeGroup2: Int = zoomAgeGroup2
    val zoomAgeGroup3: Int = zoomAgeGroup3
    val zoomAgeGroup4: Int = zoomAgeGroup4
    val zoomGenderMale: Int = zoomGenderMale
    val zoomGenderFemale: Int = zoomGenderFemale
    val zoomGenderOther: Int = zoomGenderOther
    val zoomTotalNumberOfDatasets: Int = zoomTotalNumberOfDatasets
}

class DataClientMetadataToolUse(
    codeChartsTool: Int,
    zoomMapsTool: Int
) {
    val codeChartsTool = codeChartsTool
    val zoomMapsTool = zoomMapsTool
}

class DataClientPictureDistribution(
    imagePath: String,
    count: Int
) {
    val imagePath = imagePath
    val count = count
}

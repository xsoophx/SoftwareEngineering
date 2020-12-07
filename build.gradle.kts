import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.10"
}

group = "de.tu_chemnitz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

object Version {
    const val JUNIT = "5.7.0"
    const val KOTEST = "4.3.0"
    const val KOTLINX_COROUTINES = "1.3.8"
    const val KOTLINX_SERIALIZATION = "0.20.0"
    const val LOGBACK = "1.2.3"
    const val MOCKK = "1.10.2"
    const val SLF4J = "1.7.30"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test-junit5"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Version.JUNIT}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Version.JUNIT}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Version.JUNIT}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.KOTLINX_COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Version.KOTLINX_COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:${Version.KOTLINX_COROUTINES}")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Version.KOTLINX_SERIALIZATION}")

    implementation("org.slf4j:slf4j-api:${Version.SLF4J}")
    runtimeOnly("ch.qos.logback:logback-classic:${Version.LOGBACK}")

    testImplementation("io.mockk:mockk:${Version.MOCKK}")

    testImplementation("io.kotest:kotest-assertions-core-jvm:${Version.KOTEST}")
    testImplementation("io.kotest:kotest-property-jvm:${Version.KOTEST}")

    implementation("org.litote.kmongo:kmongo:4.2.2")

    implementation("com.willowtreeapps.assertk:assertk:0.23")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("no.tornado:tornadofx:1.7.19")

    testImplementation("org.testfx:testfx-core:4.0.15-alpha")
    testImplementation("org.testfx:testfx-junit:4.0.15-alpha")
}

project.sourceSets {
    getByName("main") {
        java.srcDirs("src/main/code")
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDirs("src/main/code")
        }
    }

    getByName("test") {
        java.srcDirs("src/test/code")
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDirs("src/test/code")
        }
    }
}

tasks {
    "wrapper"(Wrapper::class) {
        gradleVersion = "6.7"
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf(
                "-Xjvm-default=enable"
            )
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

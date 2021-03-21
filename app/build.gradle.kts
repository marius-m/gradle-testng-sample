import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    kotlin("jvm")
}

group = "lt.markmerkk"
version = "0.0.1"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Language
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.21")

    // Other
    implementation("commons-io:commons-io:2.8.0")

    // Test
    testImplementation("org.testng:testng:7.4.0")
    testImplementation("org.testng:reportng:1.2.2")
    testImplementation("org.mockito:mockito-core:2.23.0")
    testImplementation("org.assertj:assertj-core:3.8.0")
    testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
}

tasks.withType(Test::class) {
    ignoreFailures = true
    useTestNG {
        // systemProperties.put("org.uncommons.reportng.stylesheet", "${projectDir}/resources/hudsonesque.css")
        // systemProperties.put("org.uncommons.reportng.escape-output", false)
        // systemProperties.put("org.uncommons.reportng.frames", true)
        outputDirectory = File(project.projectDir, "/test-reports1")
        useDefaultListeners = true
        // options {
        //     listeners.add("org.uncommons.reportng.HTMLReporter")
        //     listeners.add("org.uncommons.reportng.JUnitXMLReporter")
        // }
    }
    testLogging.showStandardStreams = true
    testLogging.exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
}

/**
 * Example usage
 * ./gradlew -PtestGroup=test-pdf-template :app:testTemplates
 * Groups are in [integration.utils.TestConsts]
 */
tasks.register("testTemplates", Test::class) {
    var testGroup = ""
    doFirst {
        val argKeyGroup = "testGroup"
        testGroup = if (project.hasProperty(argKeyGroup)) {
            project.properties.getValue(argKeyGroup).toString()
        } else {
            throw IllegalArgumentException("No 'testGroup' specified")
        }
        println("Using groups: ${testGroup}")
        useTestNG {
            testLogging.showStandardStreams = true
            includeGroups = setOf(testGroup)
            outputDirectory = File(project.projectDir, "/test-reports1")
            useDefaultListeners = true
            includeGroups = setOf(testGroup)
        }
    }

    group = "verification"
    ignoreFailures = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}

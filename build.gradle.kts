plugins {
    base
    kotlin("jvm") version("1.4.21")
}

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    }
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

val koinVersion : String by project

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    implementation(ktorLibs.serialization.kotlinx.json)
    implementation(ktorLibs.server.callLogging)
    implementation(ktorLibs.server.contentNegotiation)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation("io.ktor:ktor-server-status-pages:3.5.2")
    implementation(libs.logback.classic)

    //koin di
    implementation("io.insert-koin:koin-ktor:$koinVersion") //<----------
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
    implementation("io.ktor:ktor-server-default-headers:3.5.2") //<-------

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
    testImplementation("io.insert-koin:koin-logger-slf4j:${koinVersion}")
}

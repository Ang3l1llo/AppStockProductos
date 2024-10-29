plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.jpa") version "1.7.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.hibernate:hibernate-core:6.6.0.Final")
    implementation("com.mysql:mysql-connector-j:9.0.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
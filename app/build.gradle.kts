plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-cli:commons-cli:1.9.0")
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "sogott.beep.Main"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

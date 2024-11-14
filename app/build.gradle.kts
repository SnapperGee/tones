plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-cli:commons-cli:1.9.0")
    testImplementation(libs.junit.jupiter)
    testImplementation("org.hamcrest:hamcrest:3.0")
    testImplementation("org.mockito:mockito-core:5.14.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "sogott.tones.Main"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    jvmArgs("-Xshare:off")
}

tasks.named<Javadoc>("javadoc") {
    title = "tones"
    options.memberLevel = JavadocMemberLevel.PACKAGE
}

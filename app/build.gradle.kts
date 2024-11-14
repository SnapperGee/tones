plugins {
    application
}

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    implementation("commons-cli:commons-cli:1.9.0")
    testImplementation(libs.junit.jupiter)
    testImplementation("org.hamcrest:hamcrest:3.0")
    testImplementation("org.mockito:mockito-core:5.14.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    mockitoAgent("org.mockito:mockito-core:5.14.2") { isTransitive = false }
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
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
}

tasks.named<Javadoc>("javadoc") {
    title = "tones"
    options.memberLevel = JavadocMemberLevel.PACKAGE
}

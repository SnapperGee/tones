plugins {
    application
}

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

val mockitoMavenPackageStr = "org.mockito:mockito-core:5.14.2"

dependencies {
    implementation("commons-cli:commons-cli:1.9.0")
    testImplementation(libs.junit.jupiter)
    testImplementation("org.hamcrest:hamcrest:3.0")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.11.3")
    testImplementation(mockitoMavenPackageStr)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    mockitoAgent(mockitoMavenPackageStr) { isTransitive = false }
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
    options {
        memberLevel = JavadocMemberLevel.PACKAGE
    }
}

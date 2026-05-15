plugins {
    application
}

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

val mockitoMavenPackageStr = "org.mockito:mockito-core:5.17.0"

dependencies {
    implementation("commons-cli:commons-cli:1.11.0")
    testImplementation(libs.junit.jupiter)
    testImplementation("org.hamcrest:hamcrest:3.0")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.12.2")
    testImplementation(mockitoMavenPackageStr)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    mockitoAgent(mockitoMavenPackageStr) { isTransitive = false }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = "sogott.tones.Main"
}

tasks {
    named<Test>("test") {
        useJUnitPlatform()
        jvmArgs("-javaagent:${mockitoAgent.asPath}")
    }

    named<Javadoc>("javadoc") {
        title = "tones"

        options {
            memberLevel = JavadocMemberLevel.PACKAGE
        }

        doLast {
            // Print path to generated javadoc index.html
            println(destinationDir?.toPath()?.resolve("index.html")?.toUri())
        }
    }
}

distributions {
    main {
        distributionBaseName = rootProject.name
    }
}

import org.jetbrains.kotlin.js.translate.context.Namer.kotlin
import java.io.FileInputStream
import java.util.*

plugins {
//    id("org.jetbrains.kotlin.jvm") version "1.9.21" apply false
    id("org.jetbrains.dokka") version "1.8.10" apply false
    id("maven-publish")
    id("signing")
    kotlin("jvm") version "1.9.21"
}

allprojects {
    group = "money.rave"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}

val prop = Properties().apply {
    load(FileInputStream(File(rootDir, "properties/application.properties")))
}

subprojects {
    println(project.name)

    apply {
        plugin("org.jetbrains.dokka")
        plugin("maven-publish")
        plugin("signing")
        plugin("java")
        plugin("kotlin")
    }

    val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)
    val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
        from(dokkaHtml.outputDirectory)
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))

        testImplementation(kotlin("test"))
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = "17"
                freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
                freeCompilerArgs = freeCompilerArgs + "-Xskip-prerelease-check"
            }
        }
        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "17"
                freeCompilerArgs = freeCompilerArgs + "-Xskip-prerelease-check"
            }
        }
        test {
            useJUnitPlatform()
        }
    }

    publishing {
        publications.withType<MavenPublication> {
            artifact(javadocJar)
            pom {
                name.set(project.name)
                description.set("Useful Libraries")
                url.set("https://github.com/rave-money/useful-library")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }
                developers {
                    developer {
                        id.set("dawn.lee")
                        name.set("Dawn Lee")
                        email.set("dawn@rave.money")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/rave-money/useful-library.git")
                    developerConnection.set("scm:git:git://github.com/rave-money/usefule-library.git")
                    url.set("https://github.com/rave-money/useful-library")
                }
            }
        }

        repositories {
            if (version.toString().endsWith("-SNAPSHOT", true)) {
                maven {
                    name = "mavenCentralSnapshot"
                    setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                    credentials {
                        username = prop.getProperty("ossrh.username")
                        password = prop.getProperty("ossrh.password")
                    }
                }
            } else {
                maven {
                    name = "mavenCentral"
                    setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username = prop.getProperty("ossrh.username")
                        password = prop.getProperty("ossrh.password")
                    }
                }
            }

            maven {
                name = "rave"
                isAllowInsecureProtocol = true
                setUrl("http://rave.money:8081/repository/maven-releases")
                credentials {
                    username = prop.getProperty("ossrh.username")
                    password = prop.getProperty("ossrh.password")
                }
            }
            maven {
                name = "local"
                isAllowInsecureProtocol = true
                setUrl("http://localhost:8081/repository/maven-releases")
                credentials {
                    username = prop.getProperty("maven.credentials.username")
                    password = prop.getProperty("maven.credentials.password")
                }
            }
        }
    }

    signing {
        sign(publishing.publications)
    }
}
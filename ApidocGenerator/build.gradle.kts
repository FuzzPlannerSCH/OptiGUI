plugins {
    base
}

repositories {
    mavenCentral()
}

val download by configurations.creating

dependencies {
    download("org.jetbrains.dokka", "dokka-cli", "1.8.10")
    download("org.jetbrains.dokka", "dokka-base", "1.8.10")
    download("org.jetbrains.dokka", "dokka-analysis", "1.8.10")
    download("org.jetbrains.dokka", "kotlin-analysis-compiler", "1.8.10")
    download("org.jetbrains.dokka", "kotlin-analysis-intellij", "1.8.10")
    download("org.jetbrains.kotlinx", "kotlinx-html-jvm", "0.8.1")
    download("org.freemarker", "freemarker", "2.3.32")
    download("org.jetbrains.dokka", "kotlin-as-java-plugin", "1.8.10")
    download("org.jetbrains.kotlin", "kotlin-stdlib", System.getProperty("kotlin_version"))
    download("org.ini4j", "ini4j", "0.5.4")
}

val downloadDokka by tasks.registering(Copy::class) {
    from(download)
    into("bin/dokka")
}

val generateKdoc by tasks.registering(Exec::class) {
    dependsOn(downloadDokka)
    workingDir(projectDir)
    commandLine("java", "-jar", "bin/dokka/dokka-cli-1.8.10.jar", "dokka.json")
}

val generateJavadoc by tasks.registering(Exec::class) {
    dependsOn(downloadDokka)
    workingDir(projectDir)
    commandLine("java", "-jar", "bin/dokka/dokka-cli-1.8.10.jar", "dokka-java.json")
}

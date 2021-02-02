plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

tasks.register<JavaExec>("problem1") {
    description = "Printing threads names"
    main = "task1.Task1"
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register<JavaExec>("problem2") {
    description = "Readers writers problem"
    main = "task2.Task2"
    classpath = sourceSets["main"].runtimeClasspath
}
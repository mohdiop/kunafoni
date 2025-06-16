plugins {
    id("java")
}

group = "com.mohdiop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("jakarta.mail:jakarta.mail-api:2.1.3")
    implementation("org.eclipse.angus:angus-mail:2.0.3")
    implementation("jakarta.activation:jakarta.activation-api:2.1.2")
}

tasks.test {
    useJUnitPlatform()
}
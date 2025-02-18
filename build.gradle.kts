plugins {
    id("java")
}

group = "br.com.listafacil"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.4.2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.2")
    implementation("org.postgresql:postgresql:42.7.5")
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
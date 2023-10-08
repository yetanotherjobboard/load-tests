import de.undercouch.gradle.tasks.download.Download
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    id("org.openapi.generator") version "6.5.0"
    id("io.freefair.lombok") version "8.0.1"
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("io.gatling.gradle") version "3.9.3.1"

    //workaround for https://github.com/OpenAPITools/openapi-generator/issues/8255
    id("de.undercouch.download") version "5.4.0"
}

val codeGenOutputDir = "$buildDir/generated/sources/clients"
val openApiSpecVersion = "1.4"
val requiredApis = listOf("config", "account", "search")

fun openApiFileLocation(apiName: String) = "$buildDir/openapi-specs/$openApiSpecVersion/$apiName.yml"

//workaround for https://github.com/OpenAPITools/openapi-generator/issues/8255
fun registerOpenApiDownloadTask(apiName: String): TaskProvider<Download> {
    return tasks.register<Download>("download_${apiName}_spec") {
        group = "codegen"
        src("https://yetanotherjobboard.github.io/openapi/$openApiSpecVersion/$apiName.yml")
        dest(openApiFileLocation(apiName))
    }
}

val downloadTasks = requiredApis.map { api -> registerOpenApiDownloadTask(api) }
val downloadOpenApiSpecs by tasks.registering {
    group = "codegen"
    dependsOn(downloadTasks)
}

fun registerRestClientCodegenTask(apiName: String): TaskProvider<GenerateTask> {
    val specFileLocation = openApiFileLocation(apiName)
    return tasks.register<GenerateTask>("generate_${apiName}_rest_client") {
        //workaround for https://github.com/OpenAPITools/openapi-generator/issues/8255
        dependsOn("download_${apiName}_spec")
        doFirst {
            println("Generating $apiName client from $specFileLocation")
        }
        group = "codegen"
        generatorName.set("java")
        inputSpec.set(specFileLocation)
        outputDir.set(codeGenOutputDir)
        apiPackage.set("yajb.com.client.$apiName")
        modelPackage.set("yajb.com.client.$apiName.model")
        invokerPackage.set("yajb.com.client.$apiName.invoker")
        generateApiDocumentation.set(false)
        generateModelDocumentation.set(false)
        generateApiTests.set(false)
        generateModelTests.set(false)
        additionalProperties.put("library", "native")
        additionalProperties.put("useJakartaEe", "true")
        additionalProperties.put("openApiNullable", "false")
    }
}

val codeGenTasks = requiredApis.map { api -> registerRestClientCodegenTask(api) }
val generateSources by tasks.registering {
    group = "codegen"
    dependsOn(codeGenTasks)
}

sourceSets {
    main {
        java {
            srcDir("$codeGenOutputDir/src/main/java")
        }
    }
}

tasks.compileJava {
    options.release.set(17)
    options.compilerArgs.add("-parameters")
    //dependsOn(generateSources) //commented out so that downlaod&generate does not happen on each :bootrun
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-json")

    //needed to compile generated sources
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly("io.swagger.core.v3:swagger-annotations:2.2.7")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

gatling {

}
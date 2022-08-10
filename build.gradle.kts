import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.7.0"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.serialization") version kotlinVersion
	id("org.jetbrains.compose") version "1.2.0-alpha01-dev755"
	id("com.google.devtools.ksp") version "1.7.0-1.0.6"
}

group = "bin"
version = "1.0.0"
description = "test"

repositories {
	google()
	maven("https://maven.aliyun.com/repository/central")
	mavenCentral()
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	maven("https://maven.google.com")
}

dependencies {
	implementation(compose.desktop.currentOs)
	implementation(compose.foundation)
	implementation(compose.ui)
	implementation(compose.uiTooling)
	implementation(compose.runtime)
	implementation(compose.preview)
	// sqlite
	implementation("org.xerial:sqlite-jdbc:3.36.0.3")
	implementation("org.ktorm:ktorm-core:3.5.0")
	implementation("org.ktorm:ktorm-support-sqlite:3.5.0")
	// ktorm-ksp
	implementation("org.ktorm:ktorm-ksp-api:1.0.0-RC2")
	ksp("org.ktorm:ktorm-ksp-compiler:1.0.0-RC2")
}
// ksp 加入编译
kotlin {
	sourceSets {
		main {
			kotlin.srcDir("build/generated/ksp/main/kotlin")
		}
		test {
			kotlin.srcDir("build/generated/ksp/test/kotlin")
		}
		all {
			languageSettings.optIn("kotlin.RequiresOptIn")
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "11"
	}
}

compose.desktop {
	application {
		mainClass = "todolist.MainKt"
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			// modules("java")
			packageName = "ComposeTest"
			packageVersion = "1.0.0"
			version = "1.0"
			description = "Compose Example App"
			copyright = "© 2020 My Name. All rights reserved."
			vendor = "Example vendor"
			licenseFile.set(project.file("LICENSE"))
			windows {
				iconFile.set(project.file("src/main/resources/idea-logo.svg"))
			}
		}
	}
}

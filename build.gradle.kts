import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.10"
	id("org.jetbrains.compose") version "1.1.0"
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "bin"
version = "1.0"

repositories {
	google()
	mavenCentral()
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
	implementation(compose.desktop.currentOs)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "11"
		freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
	}
}

compose.desktop {
	application {
		mainClass = "MainKt"
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			// modules("java")
			packageName = "ComposeTest"
			packageVersion = "1.0.0"
			version = "1.0"
			description = "Compose Example App"
			copyright = "© 2020 My Name. All rights reserved."
            vendor = "Example vendor"
		}
	}
}

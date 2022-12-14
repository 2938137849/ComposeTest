package learn// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.xml.sax.InputSource
import tray.mytray
import util.exitFun

fun main() {
	application {
		val icon = painterResource("sample.png")
		mytray()
		if (true) return@application
		Window(
			onCloseRequest = exitFun,
			icon = icon,
			title = "my Window"
		) {
			mainUI()
		}
	}
}

@Preview
@Composable
private fun mainUI() {
	Text("Hello World!")
	val density = LocalDensity.current // to calculate the intrinsic size of vector images (SVG, XML)

	val ideaLogo = remember {
		useResource("idea-logo.svg") { loadSvgPainter(it, density) }
	}
	val composeLogo = rememberVectorPainter(remember {
		useResource("compose-logo.xml") { loadXmlImageVector(InputSource(it), density) }
	})

	Canvas(
		modifier = Modifier.fillMaxSize()
	) {
		drawIntoCanvas { canvas ->
			canvas.withSave {
				with(ideaLogo) {
					draw(ideaLogo.intrinsicSize)
				}
				canvas.translate(ideaLogo.intrinsicSize.width, 0f)
				with(composeLogo) {
					draw(Size(100f, 100f))
				}
			}
		}
	}
}

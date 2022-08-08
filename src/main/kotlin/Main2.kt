// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
	val icon = painterResource("sample.png")
	Tray(icon = icon, menu = {
		Item("Quit app", onClick = ::exitApplication)
	})
	Window(
		onCloseRequest = ::exitApplication, icon = icon, title = "my Window"
	) {
		var count by remember { mutableStateOf(0) }
		Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
			var text by remember { mutableStateOf("Click magenta box!") }
			Column {
				@OptIn(ExperimentalFoundationApi::class)
				Box(
					modifier = Modifier
						.background(Color.Magenta)
						.fillMaxWidth(0.7f)
						.fillMaxHeight(0.2f)
						.combinedClickable(
							onClick = {
								text = "Click! ${count++}"
							},
							onDoubleClick = {
								text = "Double click! ${count++}"
							},
							onLongClick = {
								text = "Long click! ${count++}"
							}
						)
				)
				Text(text = text, fontSize = 40.sp)
			}
		}
	}
}

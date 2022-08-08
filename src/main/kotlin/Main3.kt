// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import tray.MyTrayIcon

fun main() = application {
	var count by remember { mutableStateOf(0) }

	val trayState = rememberTrayState()
	val notification = rememberNotification("Notification", "Message from MyApp!")

	Tray(
		state = trayState,
		icon = MyTrayIcon
	) {
		Item(
			"Increment value: ${count}",
			onClick = {
				count++
			}
		)
		Item(
			"Send notification",
			onClick = {
				trayState.sendNotification(notification)
			}
		)
		Item(
			"Exit",
			onClick = ::exitApplication
		)
	}
}


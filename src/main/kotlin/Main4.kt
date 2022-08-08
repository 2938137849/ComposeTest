// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.application
import java.awt.*

fun main() = application {
	var isOpen by remember { mutableStateOf(true) }

	if (isOpen) {
		FileDialog(
			onCloseRequest = {
				isOpen = false
			}
		)
	}
}

@Preview
@Composable
private fun FileDialog(
	parent: Frame? = null,
	onCloseRequest: (result: String?) -> Unit,
) = AwtWindow(
	create = {
		object : FileDialog(parent, "Choose a file", LOAD) {
			init {
				isMultipleMode = true
			}

			override fun setVisible(value: Boolean) {
				super.setVisible(value)
				if (value) {
					onCloseRequest(file)
					println("Result $file")
					println("Result ${files.joinToString()}")
				}
			}
		}
	},
	dispose = FileDialog::dispose
)

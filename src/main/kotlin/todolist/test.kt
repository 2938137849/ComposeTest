package todolist

import androidx.compose.desktop.ui.tooling.preview.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.*

@Preview
@Composable
fun test() {
	Row(
		Modifier.fillMaxWidth().height(IntrinsicSize.Min)
	) {
		var text1 = if (LocalInspectionMode.current) "test" else text
		val isBlank = text1.isBlank()
		TextField(
			value = text1,
			onValueChange = {
				text1 = it
			},
			modifier = Modifier.fillMaxWidth(),
			isError = isBlank,
			trailingIcon = {
				Button(
					onClick = {
						textList.add(text1)
					},
					modifier = Modifier
						.clip(RoundedCornerShape(10))
						.padding(5.dp),
					enabled = !isBlank
				) {
					Text("I18n.add")
				}
			},
			singleLine = false,
		)
	}
}

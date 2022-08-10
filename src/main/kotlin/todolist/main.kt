package todolist

import androidx.compose.desktop.ui.tooling.preview.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import tray.MyTrayIcon
import util.I18n
import util.exitFun


var text by mutableStateOf("1")
val textList = mutableStateListOf("UI学习")

fun main() {
	application {
		MaterialTheme() {
			Window(
				onCloseRequest = exitFun,
				state = WindowState(
					position = WindowPosition.Aligned(Alignment.Center),
					size = DpSize(400.dp, 600.dp)
				),
				icon = MyTrayIcon,
				title = I18n.title
			) {
				TODOList()
			}
		}
	}
}

@Preview
@Composable
@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
fun TODOList() {
	Column(
		Modifier.fillMaxSize()
	) {
		test()
		var selected by remember { mutableStateOf(-1) }
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
				.padding(5.dp)
				.border(1.dp, Color.Gray)
				.padding(4.dp),
			verticalArrangement = Arrangement.spacedBy(10.dp),
		) {
			itemsIndexed(textList, { index, _ ->
				index
			}) { index, item ->
				var active by remember { mutableStateOf(false) }
				var modifier = Modifier
					.fillMaxWidth()
					.height(IntrinsicSize.Min)
					.onPointerEvent(PointerEventType.Enter) { active = true }
					.onPointerEvent(PointerEventType.Exit) { active = false }
					.combinedClickable { selected = index }
				if (selected == index) modifier = modifier.background(Color.Gray)
				if (active) modifier = modifier.background(color = Color.LightGray)
				Row(modifier = modifier) {
					TooltipArea(
						tooltip = {
							Surface(
								modifier = Modifier.shadow(4.dp),
								shape = RoundedCornerShape(4.dp)
							) {
								Text(
									text = item
								)
							}
						},
						modifier = Modifier.fillMaxHeight().weight(1f)
					) {
						Text(
							text = item,
							modifier = Modifier.padding(2.dp).fillMaxSize(),
							textAlign = TextAlign.Left,
							maxLines = 3,
							overflow = TextOverflow.Ellipsis
						)
					}
					Button({
						textList.removeAt(index)
					}, Modifier.padding(5.dp)) {
						Text(I18n.delete)
					}
				}
			}
		}
	}
}

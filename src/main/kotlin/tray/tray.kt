@file:JvmName("tray")

package tray

import androidx.compose.runtime.*
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Tray

var setting by mutableStateOf(true)
var settingItem by mutableStateOf(0)

@Composable
fun ApplicationScope.mytray() {
	Tray(MyTrayIcon, tooltip = "我的托盘菜单") {
		CheckboxItem("设置复选框", setting) { setting = it }
		if (setting) {
			Separator()
			CheckboxItem("单选框 0", settingItem == 0) { settingItem = 0 }
			CheckboxItem("单选框 1", settingItem == 1) { settingItem = 1 }
		}
		Separator()
		Item("退出(Quit)") {
			exitApplication()
		}
	}
}

package calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import calculator.calculator.Calculator
import java.util.LinkedList

var calcText by mutableStateOf("0")

fun main() {
	application {
		Window(
			onCloseRequest = {
				exitApplication()
			},
			title = "计算器",
			resizable = true
		) {
			Box(
				modifier = Modifier.fillMaxSize()
					.background(Color(40, 44, 52))
			) {
				Column(Modifier.fillMaxSize()) {
					var isError = !cheakbrackets(calcText)
					val result = if (isError) "表达式错误: 括号不匹配"
					else try {
						Calculator.invoke(calcText).v.toString()
					}
					catch (e: IllegalArgumentException) {
						isError = true
						"表达式错误: ${e.message}"
					}
					TextField(
						value = calcText,
						onValueChange = {
							calcText = it
						},
						modifier = Modifier.fillMaxWidth(),
						textStyle = TextStyle.Default.copy(),
						label = {
							Text("计算表达式:", color = Color(202, 211, 223))
						},
						placeholder = {
							Text("请输入表达式", color = Color(202, 211, 223))
						},
						leadingIcon = {
							Text("开头", color = Color(202, 211, 223))
						},
						trailingIcon = {
							Text("结尾", color = Color(202, 211, 223))
						},
						isError = isError,
						colors = TextFieldDefaults.outlinedTextFieldColors(
							textColor = Color(255, 198, 109)
						)
					)
					Text(
						result,
						Modifier.fillMaxWidth()
							.padding(40.dp),
						color = Color(143, 172, 88),
						maxLines = 1
					)
				}
			}
		}
	}
}

const val brackets = "()[]{}"
val bracketsSet = brackets.toSet()

fun cheakbrackets(string: String): Boolean {
	val list = LinkedList<Char>()
	for (c in string) {
		if (c !in bracketsSet) continue
		val i = brackets.indexOf(c)
		if ((i and 1) == 0) list.add(brackets[i + 1])
		else if (list.pollLast() != c) return false
	}
	return true
}

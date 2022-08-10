package calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import calculator.calculator.Calculator
import util.exitFun
import java.util.*

var calcText by mutableStateOf("0")

fun main() {
	application {
		Window(
			onCloseRequest = exitFun,
			title = "计算器",
			resizable = true
		) {
			Column(
				Modifier.fillMaxSize()
					.background(Color(40, 44, 52))
					.verticalScroll(rememberScrollState())
			) {
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
					modifier = Modifier.fillMaxWidth().weight(1f),
					textStyle = TextStyle.Default.copy(fontSize = 2.em),
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
					Modifier.fillMaxWidth().height(IntrinsicSize.Min),
					color = Color(143, 172, 88),
					fontSize = 3.em,
					maxLines = 1
				)
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

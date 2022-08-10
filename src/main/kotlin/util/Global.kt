@file:JvmName("Global")

package util

import androidx.compose.ui.window.*
import java.util.*

inline val ApplicationScope.exitFun: () -> Unit get() = ::exitApplication

val resourceBundle: ResourceBundle = ResourceBundle.getBundle(
	"i18n.app",
	Locale.getDefault(),
	I18n::class.java.classLoader
)

object I18n {
	val title = get("title")
	val add = get("add")
	val delete = get("delete")

	operator fun get(key: String): String = get(key, key)

	operator fun get(key: String, default: String): String = resourceBundle.getString(key) ?: default

}


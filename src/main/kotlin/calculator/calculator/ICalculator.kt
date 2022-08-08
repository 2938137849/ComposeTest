package calculator.calculator

internal open class NumberNode(override val v: Number) : Node, IValue {
	override fun toString(): String = v.toString()
}

internal class DataNode(v: Number, val list: List<Any>) : NumberNode(v), Node, IValue

internal class StringNode(val s: List<String>) : Node

interface Node

interface IValue {
	val v: Number
}

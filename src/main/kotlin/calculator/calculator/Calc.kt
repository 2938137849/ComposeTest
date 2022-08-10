package calculator.calculator

import java.util.LinkedList

class Calc : IValue {
	/**计算结果*/
	override val v: Number get() = deque.peekFirst()?.v ?: 0
	private val deque = LinkedList<IValue>()

	/**计算中产生的其他数据*/
	val list = mutableListOf<Any>()

	@Throws(IllegalArgumentException::class)
	internal operator fun plusAssign(it: Operator) = when (it) {
		is Operator2 -> {
			val pop = deque.removeLast()
			if (deque.isEmpty()) throw IllegalArgumentException("意外的运算符: 「${it.op}」")
			plusAssign(it(deque.removeLast(), pop))
		}
		is Operator1 -> plusAssign(it(deque.removeLast()))
		else -> {
			throw IllegalArgumentException("未受支持的运算符种类: 「${it.op}」")
		}
	}

	operator fun plusAssign(it: IValue) {
		deque.addLast(it)
		when (it) {
			is Calc -> list.addAll(it.list)
			is DataNode -> list.addAll(it.list)
		}
	}

	internal fun toData() = DataNode(v, list)
}

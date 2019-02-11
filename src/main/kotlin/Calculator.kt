fun eval(expr: String): Float {
    var index = 0 // current index
    val skipWhile = { cond: (Char) -> Boolean -> while (index < expr.length && cond(expr[index])) index++ }
    val tryRead = { c: Char -> (index < expr.length && expr[index] == c).also { if (it) index++ } }
    val skipWhitespaces = { skipWhile { it.isWhitespace() } }
    val tryReadOp = { op: Char -> skipWhitespaces().run { tryRead(op) }.also { if (it) skipWhitespaces() } }
    var rootOp: () -> Float = { 0.0f }

    val num = {
        if (tryReadOp('(')) {
            rootOp().also {
                tryReadOp(')').also { if (!it) throw IllegalExpressionException(index, "Missing )") }
            }
        } else {
            val start = index
            tryRead('-') or tryRead('+')
            skipWhile { it.isDigit() || it == '.' }
            try {
                expr.substring(start, index).toFloat()
            } catch (e: NumberFormatException) {
                throw IllegalExpressionException(start, "Invalid number", cause = e)
            }
        }
    }

    fun binary(left: () -> Float, op: Char): List<Float> = mutableListOf(left()).apply {
        while (tryReadOp(op)) addAll(binary(left, op))
    }

    val div = { binary(num, '/').reduce { a, b -> a / b } }
    val mul = { binary(div, '*').reduce { a, b -> a * b } }
    val sub = { binary(mul, '-').reduce { a, b -> a - b } }
    val add = { binary(sub, '+').reduce { a, b -> a + b } }

    rootOp = add
    return rootOp().also { if (index < expr.length) throw IllegalExpressionException(index, "Invalid expression") }
}

class IllegalExpressionException(val index: Int, message: String? = null, cause: Throwable? = null) :
    IllegalArgumentException("$message at:$index", cause)
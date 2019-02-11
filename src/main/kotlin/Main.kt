fun main() {
    readLine()?.let {
        try {
            println(eval(it))
        } catch (e: IllegalExpressionException) {
            println(
                "Error '${e.message} | ${it.substring(0, e.index)}${
                if (e.index < it.length)
                    " >> ${it.substring(e.index, e.index + 1)} << ${it.substring(e.index)}"
                else
                    "??"
                }"
            )
        }
    }
}
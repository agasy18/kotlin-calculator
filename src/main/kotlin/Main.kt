fun main() {
    readLine()?.let {
        try {
            println(eval(it))
        } catch (e: IllegalExpressionException) {
            println("Error '${e.message} | ${it.substring(0, e.index)} >>${it[e.index]}<<< ${it.substring(e.index)}")
        }
    }
}
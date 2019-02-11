# Simple Kotlin Calculator with 40 lines

[Code](src/main/kotlin/Calculator.kt)

### Usage

```kotlin
val str = "5 + 5"
print(eval(str)) //5
```

With exception handling
```kotlin
try {
    println(eval(str))
} catch (e: IllegalExpressionException) {
    println("Error '${e.message} | ${it.substring(0, e.index)} >>${it[e.index]}<<< ${it.substring(e.index)}")
}
```

### Features

* `+` addition

  `5 + 5` = 10
* `-` substration

  `5 - 5` = 0
* `*` multiplication

  `5 * 5` = 25
* `*` division

  `5 / 5` = 1
* `*` sign support

  `+5 - -5` = 10
* floating number support

  `10.5 - 0.5` = 10
* priority support

  `5 + 5 * 5` = 30
* priority bracket support

  `5 * (5 + 5)` = 50
* spacing support
  
  `  5 *    (  5+5)  ` = 50
* wrong expression handled
  
  `5+++5` -> `IllegalExpressionException(index=1, "Invalid number")`
* Calculation with single string iteration

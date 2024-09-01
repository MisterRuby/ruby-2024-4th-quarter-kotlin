package step04

import step02.Person

fun main() {
    val a = 10
    val b = 11

    val max = if (a > b) {
        println("a")
        a       // a 를 반환
    } else {
        println("b")
        b       // b 를 반환
    }

    when (a) {
        11 -> println("b")
        10 -> println("b")
        else -> println("?????")
    }

    when (a) {
        in 1..10 -> println("a")
        else -> println("?????")
    }

    val result = when (a) {
        is Int -> "a"
        else -> false
    }
    println(result)

    val score = 85
    val bonus = 90
    var grade = ""
    when {
        score >= 90 -> grade = "A"          // when 문에 인자가 없는 경우 조건식 별로 만족할 때 실행되는 코드를 작성할 수 있다.
        score in 80..89 -> grade = "B"
        score < 80 -> grade = "C"
        bonus >= 90 -> grade = "S"
    }

    println(grade)
}

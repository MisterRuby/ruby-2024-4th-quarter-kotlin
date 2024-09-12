package step07

class Point(val x: Int, val y: Int) {
    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }

    operator fun invoke(value: String) {
        println("invoke $value")
    }

    operator fun compareTo(point: Point): Int {
        return (x - point.x) + (y - point.y)
    }
}

fun main() {
    val point1 = Point(1, 2)
    val point2 = Point(5, 10)

    val result = point1 + point2
    println("${result.x} , ${result.y}")

    // result.invoke("test") 와 같다.
    result("test")

    val sum = { x: Int, y: Int -> x + y}
    sum.invoke(3, 11)
    sum(3, 11)

    val printFunc = { x: Int, y: Int -> println(x + y) }
    printFunc.invoke(11, 1)
    printFunc(11, 1)
}

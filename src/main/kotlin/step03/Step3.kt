package step03

/**
 * 함수 매개변수의 기본값을 정할 수 있으며 함수 호출시
 */
fun call(name: String, email: String = "default@gmail.com") {

}

fun test() {
    call("ruby")
}

fun highFunc(sum: (Int, Int) -> Int, a: Int, b: Int): Int {
    return sum(a, b)
}

fun highFunc2(): (Int, Int) -> Int {
    return { x, y -> x + y }
}

fun highFuncCall() {
    print(highFunc({x, y -> x + y}, 10, 20))

    val testFunc: (Int, Int) -> Int = highFunc2()
    testFunc(10, 20)
    testFunc(10, 30)

    var printStr: () -> String = {"test print!"}

    var printName = { name: String ->  "name is $name"}
    printName = { "name is $it"}

    var infoPrint: (String, String) -> String = { _, age -> "age : $age"}
}

fun sum(a: Int, b: Int): Int = a + b

fun Int.multi(num: Int): Int {
    return this * num
}

infix fun Int.randomMulti(num: Int): Int {
    return this * (1..num).random()
}

fun main() {
    val num = 3
    print(num.multi(4))

    print(10 randomMulti 4)

    fun localFunc(param: String) {
        println(param)
    }

    localFunc("ruby")
}



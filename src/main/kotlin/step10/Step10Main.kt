package step10

import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun inc(x: Int) : Int {
    return x + 1
}

fun high(body: (Int) -> Int): Int {
    val x = 0
    return body(x)
}

fun outerFunc(): (Int, Int) -> Int {
    var result = 0
    val innerFunc = {x: Int, y: Int ->
        result += (x + y)
        result
    }
    println(result)
    return innerFunc
}

fun testLet() {
    val num = (1..45).random()

//    val result = num.let({ x: Int -> if (x % 2 == 0) "even" else "odd" })
    val result = num.let { if (it % 2 == 0) "even" else "odd" }
    println(result)
}

fun testAlso() {
    val num = (1..45).random()
//    val result = num.also { param -> if (param % 2 == 0) println("even") else println("odd") }
    val result = num.also { if (it % 2 == 0) println("even") else println("odd") }
    println(result)
    println(num === result)
}

class Person(val name: String, val age: Int)

fun testApply() {
    val person = Person("ruby", 37)

    // apply 내부에서 아래와 같은 형태로 확장함수로 추가되어 호출된다.
    val block: Person.() -> Unit = { println("${this.name} / ${this.age}") }
    person.block()

    val test = "test"
    val name = "test"
//    val result = person.apply { println("${this.name} / ${this.age}") }
    val result = person.apply { println("$name / $age / $test") }
    println(result)
    println(person === result)
}

fun testRun() {
    val person = Person("ruby", 37)
    val personAgePlus1 = run {
        person.age + 1
    }
    println(personAgePlus1)

    val personAgePlus2 = person.run {
        // 람다식을 통해 선언된 함수가 객체의 확장함수로 처리되기 때문에 객체의 멤버에 접근할 수 있다.
        age + 1     // this.age + 1
    }
    println(personAgePlus2)
}

fun testWith() {
    val person = Person("ruby", 37)
//    val personAgePlus = with(person, { person.age + 1 })
    val personAgePlus = with(person) {
        person.age + 1
    }
    println(personAgePlus)
}

fun testUse() {
    val printWriter = PrintWriter(FileOutputStream("src/main/kotlin/step10/test.txt"))

    // 객체를 사용한 후 오류 발생 여부와 상관없이 close() 호출을 보장함
    val result = printWriter.use {
        it.println("test!!???")
        "success"
    }
    println(result)
}

fun testTake() {
    val person = Person("ruby", 37)

    val resultIf = person.takeIf { it.age % 2 == 0 }
    val resultUnless = person.takeUnless { it.age % 2 == 0 }
    println(resultIf == null)
    println(resultUnless === person)
}

fun testFilesWrite() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testFilesWrite.txt"
    val text = "테스트입니다!"

    try {
        Files.write(Paths.get(path), text.toByteArray(), StandardOpenOption.CREATE)
    } catch (e: IOException) {}
}

fun testPrintWriter() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testPrintWriter.txt"
    val text = "테스트입니다!"
    File(path).printWriter().use { it.println(text) }
}

fun testBufferedWriter() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testBufferedWriter.txt"
    val text = "테스트입니다123!"
    File(path).bufferedWriter().use { it.write(text) }
}

fun testWriteText() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testWriteText.txt"
    val text = "테스트입니다123!"

    // 내부에서 FileOutputStream 을 통해 파일 처리
    // 내부에서 use 를 통해 close() 가 호출됨
    File(path).writeText(text)
}

fun testFileWriter() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testFileWriter.txt"
    val text = "테스트입니다123!"
    FileWriter(path, true).use { it.write(text) }
}

fun testFileReader() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testReadFile.txt"
    FileReader(path).use { println(it.readText()) }
}

fun testBufferReader() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testReadFile.txt"
    File(path).bufferedReader().use { println(it.readText()) }
}

fun testCopyTo() {
    val path = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testReadFile.txt"
    val copyPath = "/Users/ruby/Desktop/project/ruby_2024_4th_quarter/ruby-2024-4th-quarter-kotlin/files/testCopyFile.txt"
    File(path).copyTo(File(copyPath))       // path 경로에 있는 파일을 copyPath 경로에 복사
}

fun main() {
    val value = high(::inc)
    println(value)

    val innerFunc = outerFunc()         // outerFunc 함수 호출의 스택은 종료됨
    println(innerFunc(2, 3))            // innerFunc 내에서 클로저에 의해 외부 함수의 변수인 result 에 접근
    println(innerFunc(5, 7))            // innerFunc 내에서 클로저에 의해 외부 함수의 변수인 result 에 접근

    testLet()
    testAlso()
    testApply()
    testRun()
    testWith()
    testUse()
    testTake()

    testFilesWrite()
    testPrintWriter()
    testBufferedWriter()
    testWriteText()
    testFileWriter()

    testFileReader()
    testBufferReader()

    testCopyTo()
}


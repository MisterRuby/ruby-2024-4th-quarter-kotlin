package step08

class Box<T> (val value: T)

class MyClass<T> {
    val prop: T     // 오류. 제네릭 타입의 프로퍼티는 초기화되거나 추상 프로퍼티이어야 함
}

class GenericNull<T> {
    // 형식 매개변수 T 는 기본적으로 Null 을 허용함. String 과 String? 이 모두 가능

    fun testFunc(value : T) {
        println(value?.toString())
    }
}

class TestClass

class GenericNotNull<T: TestClass> {
    // 형식 매개변수 T 는 기본적으로 Null 을 허용함. String 과 String? 이 모두 가능

    fun testFunc(value : T) {
        println(value.toString())
    }
}

interface InterfaceA
interface InterfaceB

class HandlerA: InterfaceA, InterfaceB
class HandlerB: InterfaceB

class ClassA<T> where T: InterfaceA, T:InterfaceB

class Invariance<T>(val size: Int)
class Covariance<out T>(var size: Int)          // 공변성 선언
class Contravariance<in T>(val size: Int)      // 반공변성 선언

fun main() {
    val box1 = Box<Int>(null)
    val box2 = Box(1)
    val box3 = Box("ruby")

    val generic1 = GenericNull<String>()
    generic1.testFunc("test")

    val generic2 = GenericNull<String?>()
    generic2.testFunc(null)

    val genericNotNull = GenericNotNull<TestClass?>()

    val classA = ClassA<HandlerA>()
    val classB = ClassA<HandlerB>()

    val anys: Invariance<Any> = Invariance<Int>(10)               // 오류. 자료형 불일치
    val nothings: Invariance<Nothing> = Invariance<Int>(10)       // 오류. 자료형 불일치

    val anys1: Covariance<Any> = Covariance<Int>(10)               // 관계 성립으로 객체 생성 가능
    val nothings1: Covariance<Nothing> = Covariance<Int>(10)       // 오류. 자료형 불일치

    val anys2: Contravariance<Any> = Contravariance<Int>(10)               // 오류. 자료형 불일치
    val nothings2: Contravariance<Nothing> = Contravariance<Int>(10)       // 관계 성립으로 객체 생성 가능

    val numbers = arrayOf(1, 2, 3, 4, 5)
    val newNumbers = numbers.plus(6)
}

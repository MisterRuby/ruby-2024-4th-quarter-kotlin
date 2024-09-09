package step07

import java.io.FileNotFoundException
import kotlin.jvm.Throws

interface Pet {
    var category: String    // 추상 프로퍼티
    val msgTags: String
        get() = "I'm your Pet!"

    fun feeding()           // 추상 메서드

    // 일반 메서드: 구현부를 포함하면 일반적인 메서드가 됨. 자바의 default 메서드와 같음
    fun patting() {
        println("Pet patting")
    }
}

class Cat(override var category: String) : Pet {
    override fun feeding() {
        println("Cat feeding")
    }
}

class Dog(override var category: String) : Pet {
    override fun feeding() {
        println("Dog feeding")
    }
}

interface A {
    fun funA() {}
}
interface B {
    fun funB() {}
}
class C(a: A, b: B): A by a, B by b {
    fun funC() {
        funA()
        funB()
    }
}

data class User(var name: String, var email: String)

class OuterC(private val outerValue: String) {

    class NestedC(val nestedValue: String) {
        // 외부 클래스의 outerValue 에 접근 불가능
    }

    inner class InnerC(val innerValue: String) {
        // 외부 클래스의 outerValue 에 접근 가능. 외부 클래스의 private 멤버까지도 접근할 수 있다.
        fun print() {
            println("$outerValue / $innerValue")
        }
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Anno(val test: String)

@Anno("???") class Test(val value: String)

fun main() {
    val cat = Cat("Yumi")
    val dog = Dog("chichi")

    println(cat.msgTags)

    val user1 = User("ruby", "rubykim0723@gmail.com")
    val user2 = User("ruby", "rubykim0723@gmail.com")
    val user3 = User("ruby1", "rubykim0723@gmail.com")

    println(user1 == user2)
    println(user1 == user3)

    val (username, userEmail) = user1

    val nestedValue = OuterC.NestedC("test").nestedValue
    val innerC = OuterC("test").InnerC("test")

    class LocalC(val value: String)
    val localC = LocalC("test")
}


